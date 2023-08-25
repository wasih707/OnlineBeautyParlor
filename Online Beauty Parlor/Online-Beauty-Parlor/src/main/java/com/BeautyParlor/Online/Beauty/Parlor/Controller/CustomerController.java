package com.BeautyParlor.Online.Beauty.Parlor.Controller;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.CustomerLoginRequest;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.CustomerRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Services.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer, HttpSession httpSession){
        Customer existingCustomer = customerRepository.findByUserId((customer.getUserId()));
        if(existingCustomer!=null){
            return ResponseEntity.badRequest().body("Customer is already registered, Please login!!");

        }
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody CustomerLoginRequest customerLoginRequest, HttpSession httpSession){
        Customer existingCustomer= customerRepository.findByUserId(customerLoginRequest.getUserId());
        if(existingCustomer==null || !new BCryptPasswordEncoder().matches(customerLoginRequest.getPassword(),existingCustomer.getPassword())){
            return ResponseEntity.badRequest().body("Invalid Customer Email id or Password");

        }
        httpSession.setAttribute("admin",existingCustomer);
        return ResponseEntity.ok("User Authenticated Successfully");
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String userId, @RequestBody CustomerLoginRequest customerLoginRequest){

        Customer existingCustomer= customerRepository.findByUserId(customerLoginRequest.getUserId());
        if( !new BCryptPasswordEncoder().matches(customerLoginRequest.getPassword(),existingCustomer.getPassword())){
            return ResponseEntity.badRequest().body("Invalid Customer Password");
        }
        if (existingCustomer!=null) {
            customerRepository.deleteById(userId);
            return ResponseEntity.ok("Customer deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer( @RequestBody Customer customer){

        Customer existingCustomer= customerRepository.findByUserId(customer.getUserId());

        if (existingCustomer==null) {
            return ResponseEntity.notFound().build();
        } else {
            existingCustomer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
            existingCustomer.setName(customer.getName());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setContactNo(customer.getContactNo());
            customerRepository.save(existingCustomer);
            return ResponseEntity.ok("Customer Updated Successfully");
        }
    }

    @GetMapping("/findAll")
        public List<Customer> displayAll(){
            return customerRepository.findAll();
    }

}


