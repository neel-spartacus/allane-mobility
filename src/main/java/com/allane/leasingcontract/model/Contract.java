package com.allane.leasingcontract.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "contract")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "contractNo")
    private Long contractNo;

    @NotNull
    private Double monthlyRate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "contract_vehicle",
            joinColumns =
                    { @JoinColumn(name = "contractNo", referencedColumnName = "contractNo") },
            inverseJoinColumns =
                    { @JoinColumn(name = "vehicle_id", referencedColumnName = "id",unique = true,nullable = false) })
    private Vehicle vehicle;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        //customer.addContract(this);
        this.customer = customer;
    }

    public Long getContractNo() {
        return contractNo;
    }


    public Double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(Double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return contractNo.equals(contract.contractNo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractNo=" + contractNo +
                ", monthlyRate=" + monthlyRate +
                '}';
    }
}
