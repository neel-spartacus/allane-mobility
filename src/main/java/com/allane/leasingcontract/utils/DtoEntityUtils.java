package com.allane.leasingcontract.utils;

import com.allane.leasingcontract.dto.ContractDto;
import com.allane.leasingcontract.dto.CustomerDto;
import com.allane.leasingcontract.dto.VehicleDto;
import com.allane.leasingcontract.model.Contract;
import com.allane.leasingcontract.model.Customer;
import com.allane.leasingcontract.model.Vehicle;
import org.modelmapper.ModelMapper;

public  class DtoEntityUtils {

    public static Contract convertContractDtoToEntity(ContractDto contractDto, ModelMapper modelMapper) {
        return modelMapper.map(contractDto, Contract.class);
    }

    public static ContractDto convertContractEntityToDto(Contract contract,ModelMapper modelMapper) {
        return modelMapper.map(contract, ContractDto.class);
    }

    public static VehicleDto convertVehicleEntityToDto(Vehicle vehicle,ModelMapper modelMapper) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }

    

    public static Vehicle convertVehicleDtoToEntity(VehicleDto vehicleDto,ModelMapper modelMapper) {
        return modelMapper.map(vehicleDto, Vehicle.class);
    }

    public static Customer convertCustomerDtoToEntity(CustomerDto customerDto,ModelMapper modelMapper) {
        return modelMapper.map(customerDto, Customer.class);
    }

    public static CustomerDto convertCustomerEntityToDto(Customer customer,ModelMapper modelMapper) {
        return modelMapper.map(customer, CustomerDto.class);
    }


}
