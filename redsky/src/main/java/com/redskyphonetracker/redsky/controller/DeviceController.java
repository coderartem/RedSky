package com.redskyphonetracker.redsky.controller;

import java.util.List;

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
 *
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

	
	
	private DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	@PostMapping("/register")
	public void register (@RequestBody DeviceDto newDevice) {
		deviceService.register(newDevice);
	}
	
	@GetMapping("/unregister/{phone}")
	public void unregister (@RequestParam Long phone) {
		deviceService.unregister(phone);
	}
	
	@GetMapping("/clear-all")
	public boolean clearAll() {
		return deviceService.clearAll();
	}
	
	@GetMapping("/get-all-in/{city}/{state}")
	public List<DeviceDto> getAllInTheCity(@RequestParam String city, @RequestParam String state){
		return deviceService.getAllInTheCity(city, state);
	}
	
	@GetMapping("/set-private/{phone}/{privateMode}")
	public boolean setPrivate(@RequestParam Long phone, @RequestParam boolean privateMode) {
		deviceService.setPrivate(phone,privateMode);
		return true;
	}
}
