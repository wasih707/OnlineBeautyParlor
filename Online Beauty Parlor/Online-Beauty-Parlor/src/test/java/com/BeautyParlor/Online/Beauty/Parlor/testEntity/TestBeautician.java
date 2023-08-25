package com.BeautyParlor.Online.Beauty.Parlor.testEntity;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBeautician {
    private Beautician beautician;

    @BeforeEach
    public void setUp() {
        beautician = new Beautician();
    }

    @Test
    public void testGetAndSetBeauticianId() {
        beautician.setBeauticianId("beautician123");
        assertEquals("beautician123", beautician.getBeauticianId());
    }

    @Test
    public void testGetAndSetName() {
        beautician.setName("Alice");
        assertEquals("Alice", beautician.getName());
    }

    @Test
    public void testGetAndSetAddress() {
        beautician.setAddress("123 Main St");
        assertEquals("123 Main St", beautician.getAddress());
    }

    @Test
    public void testGetAndSetContactNo() {
        beautician.setContactNo("555-123-4567");
        assertEquals("555-123-4567", beautician.getContactNo());
    }

    @Test
    public void testGetAndSetPassword() {
        beautician.setPassword("mypassword");
        assertEquals("mypassword", beautician.getPassword());
    }

    @Test
    public void testGetAndSetAvailability() {
        beautician.setAvailability(true);
        assertEquals(true, beautician.isAvailability());
    }
}
