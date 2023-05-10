package com.allane.leasingcontract.service;


import com.allane.leasingcontract.dto.CustomerDto;
import com.allane.leasingcontract.model.Customer;
import com.allane.leasingcontract.repository.CustomerRepository;
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
public class CustomerServiceTest {


    @Mock
    private CustomerRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerService service;


    @Test
    public void shouldAddCustomer() {
        //Given
        CustomerDto customerDto = CustomerDto.builder().id(1L).firstName("Jon").lastName("Doe")
                .birthDate("2000-04-09").build();

        Customer customer = Customer.builder().id(1L).firstName("Jon").lastName("Doe")
                .birthDate(LocalDate.of(2000, 04, 9)).build();

        Mockito.when(modelMapper.map(any(CustomerDto.class), eq(Customer.class))).thenReturn(customer);

        //When
        service.addCustomer(customerDto);


        //Then
        Mockito.verify(repository, Mockito.times(1)).save(any(Customer.class));
    }


    @Test
    public void shouldReturnCustomer() {

        //Given
        long customerId = 1L;

        CustomerDto customerDto = CustomerDto.builder().id(1L).firstName("Jon").lastName("Doe")
                .birthDate("2000-04-09").build();

        Customer customer = Customer.builder().id(customerId).firstName("Jon").lastName("Doe")
                .birthDate(LocalDate.of(2000, 8, 10)).build();

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        Mockito.when(modelMapper.map(any(Customer.class), eq(CustomerDto.class))).thenReturn(customerDto);


        //When
        CustomerDto responseCustomerDto = service.getCustomerByID(customerId);

        Assert.assertEquals(1,responseCustomerDto.getId().longValue());
        Assert.assertEquals("Jon",responseCustomerDto.getFirstName());

    }

    @Test
    public void shouldUpdateCustomer() {

        //Given
        long customerId = 1L;

        Customer customer = Customer.builder().id(customerId).firstName("Jon").lastName("Doe")
                .birthDate(LocalDate.of(2000, 8, 10)).build();

        CustomerDto customerDto = CustomerDto.builder().id(1L).firstName("Jon").lastName("Doe")
                .birthDate("2000-04-09").build();

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        Mockito.when(modelMapper.map(any(Customer.class), eq(CustomerDto.class))).thenReturn(customerDto);


        //When
        CustomerDto responseCustomerDto = service.getCustomerByID(customerId);

        Assert.assertTrue(responseCustomerDto.getId() == 1L);
        Assert.assertTrue(responseCustomerDto.getFirstName().equals("Jon"));

    }

    @Test
    public void shouldDeleteCustomer() {
        //Given
        long customerId = 1L;

        //When
        service.deleteCustomer(customerId);

        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(any(Long.class));
    }
}
