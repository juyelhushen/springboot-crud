package com.jskool.crudwithstream.repository;

import com.jskool.crudwithstream.entity.Customer;
import com.jskool.crudwithstream.payload.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("select new com.jskool.crudwithstream.payload.CustomerResponse(c.id,c.name,c.age) from Customer c")
    List<CustomerResponse> getAll();
}
