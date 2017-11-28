package com.redskyphonetracker.redsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redskyphonetracker.redsky.entity.DevLocation;
import com.redskyphonetracker.redsky.pojo.CivicAddress;

public interface LocationRepository extends JpaRepository<DevLocation, Long> {

	DevLocation findByCivicAddress(CivicAddress civic);

	
}
