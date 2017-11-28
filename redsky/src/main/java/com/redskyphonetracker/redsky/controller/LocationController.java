package com.redskyphonetracker.redsky.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redskyphonetracker.redsky.dto.DeviceLocationDto;
import com.redskyphonetracker.redsky.pojo.CivicAddress;
import com.redskyphonetracker.redsky.pojo.GeoAddress;
import com.redskyphonetracker.redsky.service.LocationService;
/**
 * 
 * @author Artem Kolin
 * Controller for Location endpoints, names of methods are pretty informative themselves
 *
 */
@RestController
@RequestMapping("/location")
public class LocationController {

	private LocationService locationService;

	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@PostMapping("set-by-civic-address/{phone}")
	public boolean setCivicLocation (@RequestBody CivicAddress civic, @RequestParam Long phone, HttpServletResponse response)  {
		boolean result = locationService.setLocationByCivic(civic, phone);
		response.setStatus(result?HttpServletResponse.SC_OK:HttpServletResponse.SC_BAD_REQUEST);
		return result;
	}
	
	@PostMapping("/set-by-geo-location/{phone}")
	public boolean setGeoLocationn(@RequestBody GeoAddress geo, @RequestParam Long phone, HttpServletResponse response) {
		boolean result = locationService.setLocationByGeo(geo, phone);
		response.setStatus(result?HttpServletResponse.SC_OK:HttpServletResponse.SC_BAD_REQUEST);
		return result;
	}
	
	@GetMapping("/all-locations/{phone}")
	public List<DeviceLocationDto> lastLocations(@RequestParam Long phone, HttpServletResponse response) {
		List<DeviceLocationDto> result = locationService.getLastLocations(phone);
		response.setStatus(result!=null?HttpServletResponse.SC_OK:HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
	
	@GetMapping("/last-location/{phone}")
	public DeviceLocationDto lastLocation(@RequestParam Long phone, HttpServletResponse response) {
		
		DeviceLocationDto result = locationService.getLastLocation(phone);
		response.setStatus(result!=null?HttpServletResponse.SC_OK:HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
	
	@GetMapping("/clear-history/{phone}")
	public boolean clearHistory(@RequestParam Long phone, HttpServletResponse response) {
		boolean result = locationService.clearHistory(phone);
		response.setStatus(result?HttpServletResponse.SC_OK:HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
		
	
}
