package com.example.appdirect.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.appdirect.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
