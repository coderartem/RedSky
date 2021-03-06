package com.redskyphonetracker.redsky.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redskyphonetracker.redsky.dto.DeviceDto;
import com.redskyphonetracker.redsky.entity.DevLocation;
import com.redskyphonetracker.redsky.entity.Device;
import com.redskyphonetracker.redsky.mapper.DeviceMapper;
import com.redskyphonetracker.redsky.repository.DeviceRepository;
import com.redskyphonetracker.redsky.repository.LocationRepository;
/**
 * 
 * @author Artem Kolin
 *
 */
@Service
public class DeviceService {
	
	private DeviceRepository deviceRepository;
	private LocationRepository locationRepository;
	private DeviceMapper deviceMapper;

	public DeviceService(DeviceRepository deviceRepository, LocationRepository locationRepository, DeviceMapper deviceMapper) {
		this.deviceRepository = deviceRepository;
		this.locationRepository = locationRepository;
		this.deviceMapper = deviceMapper;
	}

	/**
	 * Register new device
	 * @param newDevice
	 * @return 
	 */
	public boolean register(DeviceDto newDevice) {
		if(deviceRepository.findByPhoneNumber(newDevice.getPhoneNumber())!=null) return false;
		Device device = deviceMapper.dtoToDevice(newDevice);
		device.setLocation(new ArrayList<DevLocation>());
		device.setPrivateMode(false);
		deviceRepository.save(device);
		return true;
	}

	/**
	 * clear all records in device and location databases
	 * @return
	 */
	public boolean clearAll() {
		deviceRepository.deleteAll();
		locationRepository.deleteAll();
		return true;
	}

	/**
	 * Bonus endpoint
	 * 
	 * @param city
	 * @param state
	 * @return List of all devices in the same city
	 */
	public List<DeviceDto> getAllInTheCity(String city, String state) {
		List<DeviceDto> result = deviceMapper.devicesToDtos(deviceRepository.findByLastLocationCivicAddressCityAndLastLocationCivicAddressState(city, state)); 
		return result;
	}

	/**
	 * This method delete phoneNumber from Device database
	 * @param phone
	 * @return
	 */
	@Transactional
	public boolean unregister(Long phone) {
		Device device = deviceRepository.findByPhoneNumber(phone);
		if(device==null) return false;
		deviceRepository.delete(device);
		return true;
	}

	/**
	 * Set up private mode for device
	 * @param phoneNumber
	 * @param privateMode 
	 * @return boolean
	 */
	@Transactional
	public boolean setPrivate(Long phoneNumber, boolean privateMode) {
		Device device = deviceRepository.findByPhoneNumber(phoneNumber);
		if(device==null) return false;
		device.setPrivateMode(privateMode);
		return true;
	}

	
	
}
