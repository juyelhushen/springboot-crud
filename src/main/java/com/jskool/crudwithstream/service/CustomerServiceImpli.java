package com.jskool.crudwithstream.service;

import com.jskool.crudwithstream.entity.Customer;
import com.jskool.crudwithstream.payload.CustomerRequest;
import com.jskool.crudwithstream.payload.CustomerResponse;
import com.jskool.crudwithstream.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpli implements CustomerService {

    private final CustomerRepository repository;


    @Override
    public ResponseEntity<String> addCustomer(CustomerRequest request) {
        try {
            if (isValidCustomerRequest(request)) {
                repository.save(saveCustomer(request));
                return new ResponseEntity<>("Customer has been added", HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Boolean isValidCustomerRequest(CustomerRequest request) {
        return request.getCustomerName() != null && request.getAge() != 0;
    }

    public Customer saveCustomer(CustomerRequest request) {
        return Customer.builder()
                .name(request.getCustomerName())
                .age(request.getAge())
                .build();
    }


    @Transactional
    @Override
    public ResponseEntity<String> updateCustomer(long id, CustomerRequest request) {
        try {
            Customer customer = repository.findById(id).orElse(null);

            if (customer == null) {
                return ResponseEntity.notFound().build();
            }

            customer.setName(request.getCustomerName());
            customer.setAge(request.getAge());
            repository.save(customer);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Details updated successfully");
            return ResponseEntity.accepted().headers(headers).build();
        } catch (Exception e) {
            e.fillInStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @Override
    public ResponseEntity<List<CustomerResponse>> getAll() {
        try {
            List<CustomerResponse> responses = repository.getAll().stream().toList();
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getCustomerById(Long id) {
        try {
            Optional<Customer> result = repository.findAll().stream().filter(c -> c.getId() == id).findFirst();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Customer> result = repository.findById(id);
        System.out.println("dskfnsadjkfnssa dksjd sdakjfasd sjadkfcnask jsdkc");
        HttpHeaders headers = new HttpHeaders();
        if (result.isPresent()) {
            repository.deleteById(id);
            headers.add("Message", "Customer id " + id + "has been deleted successfully.");
            return ResponseEntity.noContent().headers(headers).build();
        } else {
            headers.add("Message", "No Details found with customer id " + id);
            return ResponseEntity.notFound().headers(headers).build();
        }
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getCustomerByAgeGreaterThan(int age) {
        try {
            List<CustomerResponse> responses = repository.getAll().stream().filter(c -> c.getAge() > age).toList();
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<List<CustomerResponse>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
