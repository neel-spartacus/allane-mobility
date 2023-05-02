package com.allane.leasingcontract.repository;

import com.allane.leasingcontract.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
