package com.BeautyParlor.Online.Beauty.Parlor.testController;

import com.BeautyParlor.Online.Beauty.Parlor.Controller.CustomerController;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.CustomerLoginRequest;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class TestCustomerController {
    private CustomerController customerController;
    private CustomerRepository customerRepository;


    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerController = new CustomerController(customerRepository); // Inject the mock repository through the constructor
    }

    @Test
    public void testRegisterCustomerSuccess() {
        Customer newCustomer = new Customer();
        newCustomer.setUserId("user123");
        newCustomer.setPassword("mypassword");

        when(customerRepository.findByUserId(anyString())).thenReturn(null);
        when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);

        ResponseEntity<?> response = customerController.registerCustomer(newCustomer, null);

        verify(customerRepository, times(1)).findByUserId(anyString());
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer Registered Successfully", response.getBody());
    }

    @Test
    public void testRegisterCustomerAlreadyRegistered() {
        Customer existingCustomer = new Customer();
        existingCustomer.setUserId("user123");
        existingCustomer.setPassword("existingpassword");

        when(customerRepository.findByUserId(anyString())).thenReturn(existingCustomer);

        ResponseEntity<?> response = customerController.registerCustomer(existingCustomer, null);

        verify(customerRepository, times(1)).findByUserId(anyString());
        verify(customerRepository, never()).save(any(Customer.class));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Customer is already registered, Please login!!", response.getBody());
    }

    @Test
    public void testLoginCustomerSuccess() {
        Customer existingCustomer = new Customer();
        existingCustomer.setUserId("user123");
        existingCustomer.setPassword(new BCryptPasswordEncoder().encode("mypassword"));

        CustomerLoginRequest loginRequest = new CustomerLoginRequest();
        loginRequest.setUserId("user123");
        loginRequest.setPassword("mypassword");

        when(customerRepository.findByUserId(anyString())).thenReturn(existingCustomer);
//        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        HttpSession httpSession = mock(HttpSession.class);
        ResponseEntity<?> response = customerController.loginCustomer(loginRequest, httpSession);

        verify(customerRepository, times(1)).findByUserId(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Authenticated Successfully", response.getBody());
    }

    @Test
    public void testLoginCustomerInvalidCredentials() {
        CustomerLoginRequest loginRequest = new CustomerLoginRequest();
        loginRequest.setUserId("user123");
        loginRequest.setPassword("invalidpassword");

        when(customerRepository.findByUserId(anyString())).thenReturn(null);

        ResponseEntity<?> response = customerController.loginCustomer(loginRequest, null);

        verify(customerRepository, times(1)).findByUserId(anyString());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Customer Email id or Password", response.getBody());
    }


    @Test
    public void testUpdateCustomerSuccess() {
        Customer existingCustomer = new Customer();
        existingCustomer.setUserId("user123");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setUserId("user123");
        updatedCustomer.setPassword("newpassword");
        updatedCustomer.setName("New Name");
        updatedCustomer.setAddress("New Address");
        updatedCustomer.setContactNo("New Contact");

        when(customerRepository.findByUserId(anyString())).thenReturn(existingCustomer);
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        ResponseEntity<String> response = customerController.updateCustomer(updatedCustomer);

        verify(customerRepository, times(1)).findByUserId(anyString());
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer Updated Successfully", response.getBody());
    }

    @Test
    public void testUpdateCustomerNotFound() {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setUserId("user123");

        when(customerRepository.findByUserId(anyString())).thenReturn(null);

        ResponseEntity<String> response = customerController.updateCustomer(updatedCustomer);

        verify(customerRepository, times(1)).findByUserId(anyString());
        verify(customerRepository, never()).save(any(Customer.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDisplayAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setUserId("user123");
        Customer customer2 = new Customer();
        customer2.setUserId("user456");

        List<Customer> allCustomers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(allCustomers);

        List<Customer> result = customerController.displayAll();

        verify(customerRepository, times(1)).findAll();
        assertEquals(allCustomers, result);
//    }
//    @Test
//    public void testDeleteCustomerSuccess() {
//        CustomerLoginRequest loginRequest = new CustomerLoginRequest();
//        loginRequest.setUserId("user123");
//        loginRequest.setPassword("mypassword");
//
//        Customer existingCustomer = new Customer();
//        existingCustomer.setUserId("user123");
//        existingCustomer.setPassword(new BCryptPasswordEncoder().encode("mypassword"));
//
//        when(customerRepository.findByUserId(anyString())).thenReturn(existingCustomer);
//        when(customerRepository.existsById(anyString())).thenReturn(true);
//
//        ResponseEntity<String> response = customerController.deleteCustomer("user123", loginRequest);
//
//        verify(customerRepository, times(1)).findByUserId(anyString());
//        verify(customerRepository, times(1)).existsById(anyString());
//        verify(customerRepository, times(1)).deleteById(anyString());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Customer deleted successfully", response.getBody());
//    }
    }
}
