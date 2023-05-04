package com.allane.leasingcontract.service;

import com.allane.leasingcontract.dto.VehicleDto;
import com.allane.leasingcontract.model.Vehicle;
import com.allane.leasingcontract.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.allane.leasingcontract.utils.DtoEntityUtils.convertVehicleDtoToEntity;
import static com.allane.leasingcontract.utils.DtoEntityUtils.convertVehicleEntityToDto;

@Service
public class VehicleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleRepository repository;

    public void addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = convertVehicleDtoToEntity(vehicleDto, modelMapper);
        repository.save(vehicle);
    }

    public VehicleDto getVehicleById(long vehicleId) {
        Optional<Vehicle> vehicle = repository.findById(vehicleId);
        VehicleDto vehicleDto = null;
        if (vehicle.isPresent()) {
            vehicleDto = convertVehicleEntityToDto(vehicle.get(), modelMapper);
        }
        return vehicleDto;
    }


    public void deleteVehicle(long vehicleId) {
        repository.deleteById(vehicleId);
    }


    public VehicleDto updateVehicle(long vehicleId, VehicleDto vehicleDto) {
        Vehicle updatedVehicle = repository.findById(vehicleId)
                .map(existingVehicle -> {
                    existingVehicle.setModel(vehicleDto.getModel());
                    existingVehicle.setBrand(vehicleDto.getBrand());
                    existingVehicle.setVin(vehicleDto.getVin());
                    existingVehicle.setModelYear(vehicleDto.getModelYear());
                    existingVehicle.setPrice(vehicleDto.getPrice());
                    return repository.save(existingVehicle);
                })
                .orElseGet(() -> repository.save(convertVehicleDtoToEntity(vehicleDto,modelMapper)));
        return convertVehicleEntityToDto(updatedVehicle, modelMapper);
    }
}
