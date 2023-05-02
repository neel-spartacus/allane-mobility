package com.allane.leasingcontract.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
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
    @Min(value = 1885,message = "Year cannot be less than 1885")
    private Integer modelYear;

    private String vin;

    @NonNull
    private Double price;


    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
