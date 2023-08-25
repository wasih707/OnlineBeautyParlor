package com.BeautyParlor.Online.Beauty.Parlor.Services;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
