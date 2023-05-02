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
    ResponseEntity addCustomer(@RequestBody @Valid CustomerDto customerDto){
        customerService.addCustomer(customerDto);
        return new ResponseEntity<>("Customer added", HttpStatus.CREATED);
    }

    @GetMapping("customers/{id}")
    ResponseEntity getContract(@PathVariable(name = "id") long customerId){
        CustomerDto customerDto=customerService.getCustomerByID(customerId);
        return customerDto!=null? new ResponseEntity(customerDto,HttpStatus.OK):
                new ResponseEntity<>("No Customer found for the given id " + customerId,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("customers/{id}")
    ResponseEntity deleteVehicle(@PathVariable(name = "id") long customerId){
        CustomerDto customerDto=customerService.getCustomerByID(customerId);
        if (Objects.nonNull(customerDto)) {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>("Customer deleted with id " + customerId,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No customer found with id " + customerId,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("customers/{id}")
    ResponseEntity updateCustomer(@PathVariable(name="id") @NotNull long customerId, @RequestBody @Valid CustomerDto customer){
        CustomerDto updatedCustomer=customerService.updateCustomer(customerId,customer);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }
}
