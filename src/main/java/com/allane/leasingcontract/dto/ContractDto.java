package com.allane.leasingcontract.dto;


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

    @Min(value = 1, message = "Monthly rate cannot be less than 1 euro")
    private double monthlyRate;

    @Valid
    @NotNull
    private CustomerDto customer;

    @Valid
    @NotNull
    private VehicleDto vehicle;


}
