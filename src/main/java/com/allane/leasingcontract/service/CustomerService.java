package com.allane.leasingcontract.service;

import com.allane.leasingcontract.dto.CustomerDto;
import com.allane.leasingcontract.model.Customer;
import com.allane.leasingcontract.repository.CustomerRepository;
import com.allane.leasingcontract.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

import static com.allane.leasingcontract.utils.DateUtils.*;
import static com.allane.leasingcontract.utils.DtoEntityUtils.convertCustomerDtoToEntity;
import static com.allane.leasingcontract.utils.DtoEntityUtils.convertCustomerEntityToDto;

@Service
public class CustomerService {

    private ModelMapper modelMapper;

    private CustomerRepository repository;

    @Autowired
    public CustomerService(ModelMapper modelMapper, CustomerRepository repository) {
        this.modelMapper = mapperWithLocaleDateProvider(modelMapper);
        this.repository = repository;
    }

    public void addCustomer(CustomerDto customerDto) {
        if(isValid(customerDto.getBirthDate()) && isEligibleForLeasing(customerDto.getBirthDate())){
            Customer customer = convertCustomerDtoToEntity(customerDto, modelMapper);
            repository.save(customer);
        }
        else{
            throw new ValidationException("Customer cannot be added as the birthdate provide is not valid or he/she is not eligible for leasing a vehicle");
        }

    }

    public CustomerDto getCustomerByID(long customerId) {

        Optional<Customer> customer = repository.findById(customerId);
        CustomerDto customerDto = null;
        if (customer.isPresent()) {
            customerDto = convertCustomerEntityToDto(customer.get(), modelMapper);
        }
        return customerDto;
    }

    public void deleteCustomer(long customerId) {
        repository.deleteById(customerId);
    }

    public CustomerDto updateCustomer(long customerId, CustomerDto customerDto) {


        Customer updatedCustomer = repository.findById(customerId)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(customerDto.getFirstName());
                    existingCustomer.setLastName(customerDto.getLastName());
                    if(isValid(customerDto.getBirthDate()) && isEligibleForLeasing(customerDto.getBirthDate())){
                        existingCustomer.setBirthDate(convertToLocalDate(customerDto.getBirthDate()));
                        return repository.save(existingCustomer);
                    }
                    else{
                        throw new ValidationException("Customer cannot be added/modified as the birthdate provide is not valid or he/she is not eligible for leasing a vehicle");
                    }

                })
                .orElseGet(() -> repository.save(convertCustomerDtoToEntity(customerDto, modelMapper)));
        return convertCustomerEntityToDto(updatedCustomer, modelMapper);

    }
}
