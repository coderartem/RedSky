package com.redskyphonetracker.redsky.pojo;

import javax.persistence.Embeddable;

@Embeddable
public class CivicAddress {

	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	
	public CivicAddress() {
		// TODO Auto-generated constructor stub
	}
	
	public CivicAddress(String street, String city,String state, String zip) {
		this.streetAddress = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
