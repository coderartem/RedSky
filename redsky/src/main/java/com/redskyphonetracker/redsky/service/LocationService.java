package com.redskyphonetracker.redsky.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.redskyphonetracker.redsky.dto.DeviceLocationDto;
import com.redskyphonetracker.redsky.entity.Device;
import com.redskyphonetracker.redsky.entity.DevLocation;
import com.redskyphonetracker.redsky.mapper.LocationMapper;
import com.redskyphonetracker.redsky.pojo.CivicAddress;
import com.redskyphonetracker.redsky.pojo.GeoAddress;
import com.redskyphonetracker.redsky.repository.DeviceRepository;
import com.redskyphonetracker.redsky.repository.LocationRepository;

/**
 * 
 * @author Artem Kolin
 *
 */
@Service
public class LocationService {
	
	private LocationRepository locationRepository;
	private DeviceRepository deviceRepository;
	private LocationMapper locationMapper;

	public LocationService(LocationRepository locationRepository, DeviceRepository deviceRepository, LocationMapper locationMapper) {
		this.locationRepository = locationRepository;
		this.deviceRepository = deviceRepository;
		this.locationMapper = locationMapper;
	}

	/**
	 * This method get geo location based on civic address provided, create new Location input in database, and create connection between device and location
	 * @param civic
	 * @param phoneNumber
	 * @return boolean
	 */
	@Transactional
	public boolean setLocationByCivic(CivicAddress civic, Long phoneNumber) {
				
		Device device = deviceRepository.findByPhoneNumber(phoneNumber);
		if(device==null || device.isPrivateMode())  return false;
					
				GeoApiContext context = new GeoApiContext.Builder()
					    .apiKey("AIzaSyBHrjcu5OqJT7bSdEGhxh1hn67S57nzccg")
					    .build();
					GeocodingResult[] results;
					GeoAddress geoAddress=null;
					try {
						results = GeocodingApi.geocode(context, civic.getStreetAddress()+", "+civic.getCity()+", "+civic.getState()+" "+civic.getZip()).await();
						 geoAddress = new GeoAddress(results[0].geometry.location.lat, results[0].geometry.location.lng);
					} catch (ApiException | InterruptedException | IOException e) {
						e.printStackTrace();
					}
										
			DevLocation location = locationMapper.buildDeviceLocation(civic, geoAddress);
			
			location.setTimestamp(new Timestamp(System.currentTimeMillis()));
			locationRepository.saveAndFlush(location);
			
			device.getLocation().add(location);
			device.setLastLocation(location);
			deviceRepository.saveAndFlush(device);
			
		 return true;
	}

	/**
	 * This method get civic address based on geo location provided, create new Location input in database, and create connection between device and location
	 * @param geo
	 * @param phone
	 * @return
	 * @throws ApiException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Transactional
	public boolean setLocationByGeo(GeoAddress geo, Long phone) {
		
		Device device = deviceRepository.findByPhoneNumber(phone);
		if(device==null || device.isPrivateMode())  return false;
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyBHrjcu5OqJT7bSdEGhxh1hn67S57nzccg")
			    .build();
			GeocodingResult[] results;
			String geoFormattedAddress="";
			try {
				results = GeocodingApi.geocode(context,
				    geo.getLatitude()+","+geo.getLongitude()).await();
				geoFormattedAddress = results[0].formattedAddress;
			} catch (ApiException | InterruptedException | IOException e) {
				e.printStackTrace();
			}
			
		CivicAddress civic = buildCivicAddress(geoFormattedAddress);
			
		DevLocation location = locationMapper.buildDeviceLocation(civic, geo);
		location.setTimestamp(new Timestamp(System.currentTimeMillis()));
		locationRepository.saveAndFlush(location);
			
		device.getLocation().add(location);
		device.setLastLocation(location);
		deviceRepository.saveAndFlush(device);
			
		return true;
	}
	
	/**
	 * This method modify result from google reverse geocoding API and create new CivicAddress object
	 * @param geoFormattedAddress
	 * @return new CivicAddress object
	 */
	private CivicAddress buildCivicAddress(String geoFormattedAddress) {
		String [] temp = geoFormattedAddress.split(",");
		String [] temp2 = temp[2].split(" ");
		return new CivicAddress(temp[0],temp[1],temp2[1],temp2[2]);
	}

	
	/**
	 * 
	 * @param phoneNumber
	 * @return List of last locations for this number, return false if phone number was not registered
	 */
	public List<DeviceLocationDto> getLastLocations(Long phoneNumber) {
		Device device = deviceRepository.findByPhoneNumber(phoneNumber);
		return device!=null?locationMapper.LocationsToDtos(device.getLocation()):null;
	}

	/**
	 * 
	 * @param phoneNumber
	 * @return Last location for this phone number, return false if phone number was not registered
	 */
	public DeviceLocationDto getLastLocation(Long phoneNumber) {
		Device device = deviceRepository.findByPhoneNumber(phoneNumber);
		if(device!=null && device.getLocation().size()!=0) {
			return locationMapper.locationToDto(device.getLastLocation());
		}else return null;
	}

	
	/**
	 * 
	 * @param phoneNumber
	 * @return clear locations history for this phone number, return false if phone number was not registered
	 * Doesn't really delete locations from database, just break connection between them and phone number
	 */
	@Transactional
	public boolean clearHistory(Long phoneNumber) {
		Device device = deviceRepository.findByPhoneNumber(phoneNumber);
		if(device!=null) {
			device.getLocation().clear();
			return true;
		}else return false;
		
	}

	
	
}
