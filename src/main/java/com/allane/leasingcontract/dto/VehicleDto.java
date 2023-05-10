package com.allane.leasingcontract.dto;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

    private Long id;

    @NonNull
    private String brand;

    @NonNull
    private String model;

    @NonNull
    @Min(value = 1885, message = "Year cannot be less than 1885")
    private Integer modelYear;

    private String vin;

    @NonNull
    private Double price;

}
