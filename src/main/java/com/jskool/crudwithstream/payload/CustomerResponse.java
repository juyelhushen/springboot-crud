package com.jskool.crudwithstream.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponse {
    private long customerId;
    private String customerName;
    private int age;
}
