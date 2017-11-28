package com.redskyphonetracker.redsky.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.errors.ApiException;
import com.redskyphonetracker.redsky.dto.DeviceLocationDto;
import com.redskyphonetracker.redsky.pojo.CivicAddress;
import com.redskyphonetracker.redsky.pojo.GeoAddress;
import com.redskyphonetracker.redsky.service.LocationService;

@RestController("/location")
public class LocationController {

	private LocationService locationService;

	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@PostMapping("{phone}/set-by-civic-address")
	public boolean setCivicLocation (@RequestBody CivicAddress civic, @RequestParam Long phone) throws IOException, ApiException, InterruptedException {
		return locationService.setLocationByCivic(civic, phone);
	}
	
	@PostMapping("{phone}/set-by-geo-address")
	public boolean setGeoLocationn(@RequestBody GeoAddress geo, @RequestParam Long phone ) throws ApiException, InterruptedException, IOException {
		return locationService.setLocationByGeo(geo, phone);
	}
	
	@GetMapping("/{phone}/last-locations")
	public List<DeviceLocationDto> lastLocations(@RequestParam Long phone) {
		return locationService.getLastLocations(phone);
	}
	
	@GetMapping("/{phone}/last-location")
	public DeviceLocationDto lastLocation(@RequestParam Long phone) {
		
		return locationService.getLastLocation(phone);
	}
	
	@GetMapping("/{phone}/clear-history")
	public boolean clearHistory(@RequestParam Long phone) {
		return locationService.clearHistory(phone);
	}
		
	
}
