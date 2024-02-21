package com.example.ParcialSB.services;

import com.example.ParcialSB.entities.Customer;
import com.example.ParcialSB.entities.Product;
import com.example.ParcialSB.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){

        return productRepository.findAll();
    }

    public Product save(Product product){

        return productRepository.save(product);
    }
    public Boolean findById( Integer id ){
        Optional<Product> optionalProduct = productRepository.findById( id );

        return optionalProduct.isPresent() ? true : false;
    }


}
