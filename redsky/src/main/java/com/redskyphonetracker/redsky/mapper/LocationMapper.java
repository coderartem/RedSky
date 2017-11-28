package com.redskyphonetracker.redsky.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.redskyphonetracker.redsky.dto.DeviceLocationDto;
import com.redskyphonetracker.redsky.entity.DevLocation;
import com.redskyphonetracker.redsky.pojo.CivicAddress;
import com.redskyphonetracker.redsky.pojo.GeoAddress;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	DeviceLocationDto locationToDto (DevLocation location);
	DevLocation dtoToLocation (DeviceLocationDto locationDto);
	
	List<DeviceLocationDto> LocationsToDtos(List<DevLocation> locations);
	
	DevLocation buildDeviceLocation (CivicAddress civicAddress, GeoAddress geoAddress);
}
