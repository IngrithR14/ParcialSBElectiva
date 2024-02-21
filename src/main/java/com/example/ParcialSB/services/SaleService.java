package com.example.ParcialSB.services;

import com.example.ParcialSB.entities.Customer;
import com.example.ParcialSB.entities.Product;
import com.example.ParcialSB.entities.Sale;
import com.example.ParcialSB.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    SaleRepository saleRepository;
    public Customer getCustomer(Integer id) {
        Optional<Sale> optionalSale = saleRepository.findById( id );
        return optionalSale.isPresent() ? optionalSale.get().getCustomer() : null;
    }
    public List<Product> getProducts(Integer id) {
        Optional<Sale> optionalSale = saleRepository.findById( id );
        return optionalSale.isPresent() ? optionalSale.get().getProducts(): null;
    }
    public Sale save(Sale sale,Customer customer,List<Product> products){
        sale.setCustomer(customer);
        sale.setProducts(products);
        return saleRepository.save(sale);
    }
    public boolean validarStock(Product product, int cantidad) {
        if (product.getStock() >= cantidad) {
            return true;
        }
        return false;
    }
}
