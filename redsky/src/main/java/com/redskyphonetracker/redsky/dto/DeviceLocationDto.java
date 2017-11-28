package com.redskyphonetracker.redsky.dto;

import java.sql.Timestamp;

import javax.persistence.Embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.redskyphonetracker.redsky.pojo.CivicAddress;
import com.redskyphonetracker.redsky.pojo.GeoAddress;

public class DeviceLocationDto {
	
	private Long id;
	
	@Embedded
	private CivicAddress civicAddress;
	
	@Embedded
	private GeoAddress geoAddress;
	
	@JsonFormat(pattern="MM-dd-yy HH:mm")
	private Timestamp timestamp;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public CivicAddress getCivicAddress() {
		return civicAddress;
	}

	public void setCivicAddress(CivicAddress civicAddress) {
		this.civicAddress = civicAddress;
	}

	public GeoAddress getGeoAddress() {
		return geoAddress;
	}

	public void setGeoAddress(GeoAddress geoAddress) {
		this.geoAddress = geoAddress;
	}
	
}
