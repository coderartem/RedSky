package com.redskyphonetracker.redsky.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.redskyphonetracker.redsky.dto.DeviceDto;
import com.redskyphonetracker.redsky.dto.DeviceLocationDto;
import com.redskyphonetracker.redsky.entity.Device;

@Mapper(componentModel="spring")
public interface DeviceMapper {

	Device dtoToDevice (DeviceDto dto);
	DeviceDto deviceToDto (Device device);
	
	List<DeviceDto> devicesToDtos (List<Device> devices);
}
