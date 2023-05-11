package com.allane.leasingcontract.controller;

import com.allane.leasingcontract.dto.CustomerDto;
import com.allane.leasingcontract.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("customer")
    ResponseEntity<CustomerDto> addCustomer(@RequestBody @Valid CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }

    @GetMapping("customers/{id}")
    ResponseEntity<CustomerDto> getContract(@PathVariable(name = "id") long customerId) {
        CustomerDto customerDto = customerService.getCustomerByID(customerId);
        return customerDto != null ? ResponseEntity.ok(customerDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("customers/{id}")
    ResponseEntity<Void> deleteVehicle(@PathVariable(name = "id") long customerId) {
        CustomerDto customerDto = customerService.getCustomerByID(customerId);
        if (Objects.nonNull(customerDto)) {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("customers/{id}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "id") @NotNull long customerId, @RequestBody @Valid CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }
}
