package com.jskool.crudwithstream.service;


import com.jskool.crudwithstream.entity.Product;
import com.jskool.crudwithstream.payload.ProductRequest;
import com.jskool.crudwithstream.payload.ProductResponse;

import java.util.List;

public interface ProductService {
    String addProduct(ProductRequest request);

    Product updateProduct(long id, ProductRequest request);

    List<Product> getAll();

    Product getProductById(Long id);

    void delete(long id);
}
