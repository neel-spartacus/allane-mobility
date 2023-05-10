package com.allane.leasingcontract.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @NotNull
    @Column(name = "model_year")
    private Integer modelYear;


    @Column(name = "vin")
    private String vin;

    @NotNull
    private Double price;

    @OneToOne(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Contract contract;

}
