package com.redskyphonetracker.redsky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redskyphonetracker.redsky.entity.Device;

public interface DeviceRepository extends JpaRepository <Device, Integer> {

	Device findByPhoneNumber(Long phone);

	List<Device> findByLocationCivicAddressCityAndLocationCivicAddressState(String city, String state);

	
}
