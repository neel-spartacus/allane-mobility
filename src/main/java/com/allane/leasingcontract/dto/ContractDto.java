package com.allane.leasingcontract.dto;


import com.allane.leasingcontract.model.Customer;
import com.allane.leasingcontract.model.Vehicle;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {

    private Long contractNo;

    @Min(value = 1,message = "Monthly rate cannot be less than 1 euro")
    private double monthlyRate;

    @Valid
    @NotNull
    private CustomerDto customer;

    @Valid
    @NotNull
    private VehicleDto vehicle;


}
