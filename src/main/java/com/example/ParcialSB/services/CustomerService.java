package com.example.ParcialSB.services;

import com.example.ParcialSB.entities.Customer;
import com.example.ParcialSB.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> findAll(){

        return customerRepository.findAll();
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findById( Integer id ){
        Optional<Customer> optionalCustomer = customerRepository.findById( id );

        return optionalCustomer.isPresent() ? optionalCustomer.get() : null;
    }

    public void delete( Customer customer ){
        customerRepository.delete( customer );
    }
}
