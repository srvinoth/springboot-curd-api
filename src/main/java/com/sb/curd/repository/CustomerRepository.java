package com.sb.curd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sb.curd.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
