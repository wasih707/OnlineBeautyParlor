package com.BeautyParlor.Online.Beauty.Parlor.testEntity;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.CustomerLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCustomerLoginRequest {
    private CustomerLoginRequest customerLoginRequest;

    @BeforeEach
    public void setUp() {
        customerLoginRequest = new CustomerLoginRequest();
    }

    @Test
    public void testGetAndSetUserId() {
        customerLoginRequest.setUserId("user123");
        assertEquals("user123", customerLoginRequest.getUserId());
    }

    @Test
    public void testGetAndSetPassword() {
        customerLoginRequest.setPassword("mypassword");
        assertEquals("mypassword", customerLoginRequest.getPassword());
    }
}
