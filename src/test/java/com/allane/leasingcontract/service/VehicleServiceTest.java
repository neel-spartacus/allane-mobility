package com.allane.leasingcontract.service;


import com.allane.leasingcontract.dto.VehicleDto;
import com.allane.leasingcontract.model.Vehicle;
import com.allane.leasingcontract.repository.VehicleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {


    @Mock
    private VehicleRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VehicleService service;


    @Test
    public void shouldAddVehicle() {
        //Given
        VehicleDto vehicleDto = VehicleDto.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).build();

        Vehicle vehicle = Vehicle.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).build();

        Mockito.when(modelMapper.map(any(VehicleDto.class), eq(Vehicle.class))).thenReturn(vehicle);

        //When
        service.addVehicle(vehicleDto);


        //Then
        Mockito.verify(repository, Mockito.times(1)).save(any(Vehicle.class));
    }


    @Test
    public void shouldReturnVehicle() {

        //Given
        long vehicleId = 1L;

        //Given
        VehicleDto vehicleDto = VehicleDto.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).build();

        Vehicle vehicle = Vehicle.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).build();

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(vehicle));
        Mockito.when(modelMapper.map(any(Vehicle.class), eq(VehicleDto.class))).thenReturn(vehicleDto);


        //When
        VehicleDto responseVehicleDto = service.getVehicleById(vehicleId);

        Assert.assertTrue(responseVehicleDto.getId() == 1L);
        Assert.assertTrue(responseVehicleDto.getBrand().equals("BMW"));

    }

    @Test
    public void shouldUpdateVehicle() {

        //Given
        long vehicleId = 1L;

        VehicleDto vehicleDto = VehicleDto.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).vin("X123456").build();

        Vehicle vehicle = Vehicle.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).build();

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(vehicle));
        Mockito.when(repository.save(any(Vehicle.class))).thenReturn(vehicle);
        Mockito.when(modelMapper.map(any(Vehicle.class), eq(VehicleDto.class))).thenReturn(vehicleDto);


        //When
        VehicleDto responseVehicleDto = service.updateVehicle(vehicleId, vehicleDto);

        Assert.assertTrue(responseVehicleDto.getId() == 1L);
        Assert.assertTrue(responseVehicleDto.getVin().equals("X123456"));

    }

    @Test
    public void shouldDeleteVehicle() {
        //Given
        long vehicleId = 1L;

        //When
        service.deleteVehicle(vehicleId);

        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(any(Long.class));
    }
}
