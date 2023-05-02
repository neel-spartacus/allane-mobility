package com.allane.leasingcontract.service;


import com.allane.leasingcontract.dto.CustomerDto;
import com.allane.leasingcontract.model.Customer;
import com.allane.leasingcontract.repository.CustomerRepository;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {


    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @Test
    public void shouldAddCustomer(){
        //Given
        CustomerDto customer= CustomerDto.builder().id(1L).firstName("Jon").lastName("Doe")
                .birthDate("2000-04-09").build();

        //When
        service.addCustomer(customer);

        //Then
        Mockito.verify(repository,Mockito.times(1)).save(any(Customer.class));
    }


    @Test
    public void shouldReturnCustomer(){

        //Given
        Long customerId=1L;

        Customer customer= Customer.builder().id(customerId).firstName("Jon").lastName("Doe")
                .birthDate(LocalDate.of(2000,8,10)).build();

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(customer));

        //When
        CustomerDto customerDto=service.getCustomerByID(customerId);

        Assert.assertTrue(customerDto.getId()==1L);
        Assert.assertTrue(customerDto.getFirstName().equals("Jon"));

    }

    @Test
    public void shouldUpdateCustomer(){

        //Given
        Long customerId=1L;

        Customer customer= Customer.builder().id(customerId).firstName("Jon").lastName("Doe")
                .birthDate(LocalDate.of(2000,8,10)).build();

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(customer));

        //When
        CustomerDto customerDto=service.getCustomerByID(customerId);

        Assert.assertTrue(customerDto.getId()==1L);
        Assert.assertTrue(customerDto.getFirstName().equals("Jon"));

    }
}
