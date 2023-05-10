package com.allane.leasingcontract.controller;


import com.allane.leasingcontract.dto.ContractDto;
import com.allane.leasingcontract.service.LeaseContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
public class LeasingContractController {

    @Autowired
    private LeaseContractService leaseContractService;

    @PostMapping("contract")
    ResponseEntity<?> createContract(@RequestBody @Valid ContractDto contract) {

        leaseContractService.createContract(contract);
        return new ResponseEntity<>("Contract created", HttpStatus.CREATED);
    }

    @GetMapping("contracts/{id}")
    ResponseEntity<?> getContract(@PathVariable(name = "id") long contractId) {
        ContractDto contract = leaseContractService.getContractById(contractId);
        return contract != null ? new ResponseEntity<>(contract, HttpStatus.OK) :
                new ResponseEntity<>("No contract found for the given id " + contractId, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("contracts/{id}")
    ResponseEntity<?> deleteContract(@PathVariable(name = "id") long contractId) {
        ContractDto contract = leaseContractService.getContractById(contractId);
        if (Objects.nonNull(contract)) {
            leaseContractService.deleteContract(contractId);
            return new ResponseEntity<>("Contract deleted with id " + contractId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No contract found with id " + contractId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("contracts/{id}")
    ResponseEntity<?> updateContract(@PathVariable(name = "id") @NotNull long contractId, @RequestBody @Valid ContractDto contractDto) {
        ContractDto updatedContract = leaseContractService.updateContract(contractId, contractDto);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }


}
