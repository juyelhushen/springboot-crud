package com.jskool.crudwithstream.service;

import com.jskool.crudwithstream.payload.CustomerRequest;
import com.jskool.crudwithstream.payload.CustomerResponse;
import com.jskool.crudwithstream.payload.ProductRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CustomerService {


    ResponseEntity<String> addCustomer(CustomerRequest request);

    ResponseEntity<String> updateCustomer(long id, CustomerRequest request);

    ResponseEntity<List<CustomerResponse>> getAll();

    ResponseEntity<?> getCustomerById(Long id);

    ResponseEntity<String> delete(Long id);

    ResponseEntity<List<CustomerResponse>> getCustomerByAgeGreaterThan(int age);


}
