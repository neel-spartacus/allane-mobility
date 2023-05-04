package com.allane.leasingcontract.service;

import com.allane.leasingcontract.dto.ContractDto;
import com.allane.leasingcontract.model.Contract;
import com.allane.leasingcontract.repository.ContractRepository;
import com.allane.leasingcontract.utils.DtoEntityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.allane.leasingcontract.utils.DtoEntityUtils.*;

@Service
public class LeaseContractService {


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private ContractRepository repository;

    public void createContract(ContractDto contractDto) {
        Contract contract = convertContractDtoToEntity(contractDto,modelMapper);
        repository.save(contract);
    }


    public ContractDto getContractById(long contractId) {
        Optional<Contract> contract = repository.findById(contractId);
        ContractDto contractDto = null;
        if (contract.isPresent()) {
            contractDto = convertContractEntityToDto(contract.get(),modelMapper);
        }
        return contractDto;
    }

    public void deleteContract(long contractId) {
        repository.deleteById(contractId);
    }

    public ContractDto updateContract(long contractId, ContractDto contract) {

        Contract updatedContract = repository.findById(contractId)
                .map(existingContract -> {
                    existingContract.setVehicle(convertVehicleDtoToEntity(contract.getVehicle(), modelMapper));
                    existingContract.setCustomer(convertCustomerDtoToEntity(contract.getCustomer(), modelMapper));
                    existingContract.setMonthlyRate(contract.getMonthlyRate());
                    return repository.save(existingContract);
                })
                .orElseGet(() -> repository.save(convertContractDtoToEntity(contract,modelMapper)));
        return convertContractEntityToDto(updatedContract,modelMapper);
    }
}
