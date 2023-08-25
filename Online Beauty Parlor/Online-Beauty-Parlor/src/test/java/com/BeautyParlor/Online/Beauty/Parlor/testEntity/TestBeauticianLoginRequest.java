package com.BeautyParlor.Online.Beauty.Parlor.testEntity;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.BeauticianLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBeauticianLoginRequest {
    private BeauticianLoginRequest beauticianLoginRequest;

    @BeforeEach
    public void setUp() {
        beauticianLoginRequest = new BeauticianLoginRequest();
    }

    @Test
    public void testGetAndSetBeauticianId() {
        beauticianLoginRequest.setBeauticianId("beautician123");
        assertEquals("beautician123", beauticianLoginRequest.getBeauticianId());
    }

    @Test
    public void testGetAndSetPassword() {
        beauticianLoginRequest.setPassword("mypassword");
        assertEquals("mypassword", beauticianLoginRequest.getPassword());
    }
}
