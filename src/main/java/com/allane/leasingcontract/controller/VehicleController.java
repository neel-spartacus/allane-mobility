package com.allane.leasingcontract.controller;

import com.allane.leasingcontract.dto.VehicleDto;
import com.allane.leasingcontract.model.Vehicle;
import com.allane.leasingcontract.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("vehicle")
    ResponseEntity addVehicle(@RequestBody @Valid VehicleDto vehicleDto) throws NoSuchAlgorithmException {

        vehicleService.addVehicle(vehicleDto);
        return new ResponseEntity<>("Vehicle added", HttpStatus.CREATED);
    }

    @GetMapping("vehicles/{id}")
    ResponseEntity getContract(@PathVariable(name = "id") long vehicleId) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
        return vehicleDto != null ? new ResponseEntity(vehicleDto, HttpStatus.OK) :
                new ResponseEntity<>("No vehicle found for the given id " + vehicleId, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("vehicles/{id}")
    ResponseEntity deleteVehicle(@PathVariable(name = "id") long vehicleId) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
        if (Objects.nonNull(vehicleDto)) {
            vehicleService.deleteVehicle(vehicleId);
            return new ResponseEntity<>("Vehicle deleted with id " + vehicleId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No vehicle found with id " + vehicleId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("vehicles/{id}")
    ResponseEntity updateVehicle(@PathVariable(name = "id") @NotNull long vehicleId, @RequestBody @Valid final Vehicle vehicle) {
        VehicleDto updatedContract = vehicleService.updateVehicle(vehicleId, vehicle);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }


}
