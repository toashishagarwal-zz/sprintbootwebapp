package com.example.appdirect.services;

import com.example.appdirect.domain.Product;

public interface ProductService {
	Iterable<Product> listAllProducts();
	Product getProductById(Integer id);
    Product saveProduct(Product product);
    void deleteProduct(Integer id);
}
