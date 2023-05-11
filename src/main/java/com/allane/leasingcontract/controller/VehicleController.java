package com.allane.leasingcontract.controller;

import com.allane.leasingcontract.dto.VehicleDto;
import com.allane.leasingcontract.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("vehicle")
    ResponseEntity<VehicleDto> addVehicle(@RequestBody @Valid VehicleDto vehicleDto) {
        vehicleService.addVehicle(vehicleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDto);
    }

    @GetMapping("vehicles/{id}")
    ResponseEntity<?> getContract(@PathVariable(name = "id") long vehicleId) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
        return vehicleDto != null ? ResponseEntity.ok(vehicleDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("vehicles/{id}")
    ResponseEntity<Void> deleteVehicle(@PathVariable(name = "id") long vehicleId) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
        if (Objects.nonNull(vehicleDto)) {
            vehicleService.deleteVehicle(vehicleId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("vehicles/{id}")
    ResponseEntity<VehicleDto> updateVehicle(@PathVariable(name = "id") @NotNull long vehicleId, @RequestBody @Valid final VehicleDto vehicleDto) {
        VehicleDto updateVehicle = vehicleService.updateVehicle(vehicleId, vehicleDto);
        return ResponseEntity.ok(updateVehicle);
    }


}
