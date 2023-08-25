package com.BeautyParlor.Online.Beauty.Parlor.testEntity;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCustomer {
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
    }

    @Test
    public void testGetAndSetUserId() {
        customer.setUserId("cust123");
        assertEquals("cust123", customer.getUserId());
    }

    @Test
    public void testGetAndSetName() {
        customer.setName("Keshav ");
        assertEquals("Keshav ", customer.getName());
    }

    @Test
    public void testGetAndSetAddress() {
        customer.setAddress("Kanpur ");
        assertEquals("Kanpur ", customer.getAddress());
    }

    @Test
    public void testGetAndSetContactNo() {
        customer.setContactNo("9988998899");
        assertEquals("9988998899", customer.getContactNo());
    }

    @Test
    public void testGetAndSetPassword() {
        customer.setPassword("mypassword");
        assertEquals("mypassword", customer.getPassword());
    }
}
