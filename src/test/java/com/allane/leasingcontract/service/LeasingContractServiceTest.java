package com.allane.leasingcontract.service;


import com.allane.leasingcontract.dto.ContractDto;
import com.allane.leasingcontract.dto.CustomerDto;
import com.allane.leasingcontract.dto.VehicleDto;
import com.allane.leasingcontract.model.Contract;
import com.allane.leasingcontract.model.Customer;
import com.allane.leasingcontract.model.Vehicle;
import com.allane.leasingcontract.repository.ContractRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class LeasingContractServiceTest {


    @Mock
    private ContractRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LeaseContractService service;


    @Test
    public void shouldAddContract() {
        //Given
        ContractDto contractDto = prepareContractDto();
        Contract contract = prepareContract();

        Mockito.when(modelMapper.map(any(ContractDto.class), eq(Contract.class))).thenReturn(contract);

        //When
        service.createContract(contractDto);


        //Then
        Mockito.verify(repository, Mockito.times(1)).save(any(Contract.class));
    }


    @Test
    public void shouldReturnContract() {

        //Given
        long contractNo = 1L;
        ContractDto contractDto = prepareContractDto();
        Contract contract = prepareContract();
        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(contract));
        Mockito.when(modelMapper.map(any(Contract.class), eq(ContractDto.class))).thenReturn(contractDto);


        //When
        ContractDto responseContractDto = service.getContractById(contractNo);

        Assert.assertTrue(responseContractDto.getContractNo() == 1L);
        Assert.assertTrue(responseContractDto.getVehicle().getBrand().equals("BMW"));

    }

    @Test
    public void shouldUpdateContract() {

        //Given
        long contractNo = 1L;
        ContractDto contractDto = prepareContractDto();
        contractDto.getVehicle().setVin("X123456");
        Contract contract = prepareContract();
        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(contract));
        Mockito.when(repository.save(any(Contract.class))).thenReturn(contract);
        Mockito.when(modelMapper.map(any(Contract.class), eq(ContractDto.class))).thenReturn(contractDto);


        //When
        ContractDto responseContractDto = service.updateContract(contractNo, contractDto);

        Assert.assertTrue(responseContractDto.getContractNo() == 1L);
        Assert.assertTrue(responseContractDto.getVehicle().getVin().equals("X123456"));

    }

    @Test
    public void shouldDeleteContract() {
        //Given
        long contractNo = 1L;

        //When
        service.deleteContract(contractNo);

        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(any(Long.class));
    }


    private Contract prepareContract() {

        Customer customer = Customer.builder().id(1L).firstName("Jon").lastName("Doe")
                .birthDate(LocalDate.of(2000, 04, 9)).build();
        Vehicle vehicle = Vehicle.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).build();

        return Contract.builder().contractNo(1L).vehicle(vehicle).customer(customer).build();
    }

    private ContractDto prepareContractDto() {

        CustomerDto customerDto = CustomerDto.builder().id(1L).firstName("Jon").lastName("Doe")
                .birthDate("2000-04-09").build();

        VehicleDto vehicleDto = VehicleDto.builder().id(1L).brand("BMW").model("X5")
                .modelYear(2020).price(50000.00).build();


        return ContractDto.builder().contractNo(1L).monthlyRate(200).customer(customerDto)
                .vehicle(vehicleDto).build();
    }

}
