package com.jskool.crudwithstream.controller;

import com.jskool.crudwithstream.payload.CustomerRequest;
import com.jskool.crudwithstream.payload.CustomerResponse;
import com.jskool.crudwithstream.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequest request) {
        try {
            return customerService.addCustomer(request);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable long id, @RequestBody CustomerRequest request) {
        try {
            return customerService.updateCustomer(id, request);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CustomerResponse>> getAll() {
        try {
            return customerService.getAll();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<List<CustomerResponse>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getcustomer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            return customerService.getCustomerById(id);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            return customerService.delete(id);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/age/{age}")
    public ResponseEntity<List<CustomerResponse>> getCustomerByAgeGreaterThan(@PathVariable int age) {
        try {
            return customerService.getCustomerByAgeGreaterThan(age);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



