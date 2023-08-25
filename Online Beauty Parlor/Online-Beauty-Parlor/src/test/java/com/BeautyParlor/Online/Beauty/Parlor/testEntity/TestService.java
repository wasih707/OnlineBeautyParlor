package com.BeautyParlor.Online.Beauty.Parlor.testEntity;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestService {
    private Service service;

    @BeforeEach
    public void setUp() {
        service = new Service();
    }

    @Test
    public void testGetAndSetSid() {
        service.setSid("service123");
        assertEquals("service123", service.getSid());
    }

    @Test
    public void testGetAndSetDescription() {
        service.setDescription("Haircut");
        assertEquals("Haircut", service.getDescription());
    }

    @Test
    public void testGetAndSetCharges() {
        service.setCharges(30.0f);
        assertEquals(30.0f, service.getCharges(), 0.01); // Using delta for float comparison
    }
}
