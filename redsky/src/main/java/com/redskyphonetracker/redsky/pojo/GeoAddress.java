package com.redskyphonetracker.redsky.pojo;

import javax.persistence.Embeddable;

@Embeddable
public class GeoAddress {
	
	private Double latitude;
	private Double longitude;
	
	
	public GeoAddress() {
		// TODO Auto-generated constructor stub
	}
	
	public GeoAddress(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	
}
