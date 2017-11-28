package com.redskyphonetracker.redsky.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.redskyphonetracker.redsky.pojo.CivicAddress;
import com.redskyphonetracker.redsky.pojo.GeoAddress;

@Entity
public class DevLocation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private CivicAddress civicAddress;
	
	@Embedded
	private GeoAddress geoAddress;

	@ManyToMany(mappedBy = "location")
	private List<Device> device;
	
	private Timestamp timestamp;

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	

	public List<Device> getDevice() {
		return device;
	}

	public void setDevice(List<Device> device) {
		this.device = device;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DevLocation other = (DevLocation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
