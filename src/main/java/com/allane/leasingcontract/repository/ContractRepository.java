package com.allane.leasingcontract.repository;

import com.allane.leasingcontract.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
