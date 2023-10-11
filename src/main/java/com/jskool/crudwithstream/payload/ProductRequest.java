package com.jskool.crudwithstream.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {
    private String title;
    private String description;
}
