package com.redskyphonetracker.redsky.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redskyphonetracker.redsky.dto.DeviceDto;
import com.redskyphonetracker.redsky.service.DeviceService;
/**
 * 
 * @author Artem Kolin
 * Controller for Device endpoints, names of methods are pretty informative themselves
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

	
	
	private DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	@PostMapping("/register")
	public boolean register (@RequestBody DeviceDto newDevice, HttpServletResponse response) {
		boolean result = deviceService.register(newDevice);
		response.setStatus(result?HttpServletResponse.SC_CREATED:HttpServletResponse.SC_BAD_REQUEST);
		return result;
	}
	
	@GetMapping("/unregister/{phone}")
	public boolean unregister (@RequestParam Long phone, HttpServletResponse response) {
		boolean result = deviceService.unregister(phone);
		response.setStatus(result?HttpServletResponse.SC_OK:HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
	
	@GetMapping("/clear-all")
	public boolean clearAll(HttpServletResponse response) {
		boolean result = deviceService.clearAll();
		response.setStatus(result?HttpServletResponse.SC_OK:HttpServletResponse.SC_BAD_REQUEST);
		return result;
	}
	
	@GetMapping("/get-all-in/{city}/{state}")
	public List<DeviceDto> getAllInTheCity(@RequestParam String city, @RequestParam String state, HttpServletResponse response){
		List<DeviceDto> result = deviceService.getAllInTheCity(city, state);
		response.setStatus(result!=null?HttpServletResponse.SC_OK:HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
	
	@GetMapping("/set-privateMode/{phone}/{privateMode}")
	public boolean setPrivate(@RequestParam Long phone, @RequestParam boolean privateMode, HttpServletResponse response) {
		boolean result = deviceService.setPrivate(phone,privateMode);
		response.setStatus(result?HttpServletResponse.SC_OK:HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
}
