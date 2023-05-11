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
    ResponseEntity<ContractDto> createContract(@RequestBody @Valid ContractDto contract) {
        leaseContractService.createContract(contract);
        return ResponseEntity.status(HttpStatus.CREATED).body(contract);
    }

    @GetMapping("contracts/{id}")
    ResponseEntity<ContractDto> getContract(@PathVariable(name = "id") long contractId) {
        ContractDto contract = leaseContractService.getContractById(contractId);
        return contract != null ? ResponseEntity.ok(contract) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("contracts/{id}")
    ResponseEntity<Void> deleteContract(@PathVariable(name = "id") long contractId) {
        ContractDto contract = leaseContractService.getContractById(contractId);
        if (Objects.nonNull(contract)) {
            leaseContractService.deleteContract(contractId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("contracts/{id}")
    ResponseEntity<ContractDto> updateContract(@PathVariable(name = "id") @NotNull long contractId, @RequestBody @Valid ContractDto contractDto) {
        ContractDto updatedContract = leaseContractService.updateContract(contractId, contractDto);
        return ResponseEntity.ok(updatedContract);
    }


}
