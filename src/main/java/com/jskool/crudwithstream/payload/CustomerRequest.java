package com.jskool.crudwithstream.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private String customerName;
    private int age;
}
