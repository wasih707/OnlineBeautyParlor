package com.BeautyParlor.Online.Beauty.Parlor.testController;

import com.BeautyParlor.Online.Beauty.Parlor.Controller.BeauticianController;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.BeauticianLoginRequest;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.BeauticianRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Services.BeauticianService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestBeauticianController {
    @InjectMocks
    private BeauticianController beauticianController;

    @Mock
    private BeauticianRepository beauticianRepository;

    @Mock
    private HttpSession httpSession;

    @Test
    public void testRegisterBeauticianSuccess() {
        Beautician beautician = new Beautician();
        beautician.setBeauticianId("beautician123");
        beautician.setPassword("password");

        when(beauticianRepository.findByBeauticianId(anyString())).thenReturn(null);
        when(beauticianRepository.save(beautician)).thenReturn(beautician);

        ResponseEntity<?> response = beauticianController.registerBeautician(beautician, httpSession);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Beautician Registered Successfully", response.getBody());
    }

    @Test
    public void testRegisterBeauticianAlreadyRegistered() {
        Beautician beautician = new Beautician();
        beautician.setBeauticianId("beautician123");

        when(beauticianRepository.findByBeauticianId(anyString())).thenReturn(beautician);

        ResponseEntity<?> response = beauticianController.registerBeautician(beautician, httpSession);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Beautician is already registered, Please login Beautician", response.getBody());
    }

    @Test
    public void testLoginBeauticianSuccess() {
        Beautician existingBeautician = new Beautician();
        existingBeautician.setBeauticianId("beautician123");
        existingBeautician.setPassword(new BCryptPasswordEncoder().encode("password"));

        BeauticianLoginRequest loginRequest = new BeauticianLoginRequest();
        loginRequest.setBeauticianId("beautician123");
        loginRequest.setPassword("password");

        when(beauticianRepository.findByBeauticianId(anyString())).thenReturn(existingBeautician);

        ResponseEntity<?> response = beauticianController.loginBeautician(loginRequest, httpSession);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Beautician Authenticated Successfully", response.getBody());
    }

    @Test
    public void testLoginBeauticianInvalidPassword() {
        Beautician existingBeautician = new Beautician();
        existingBeautician.setBeauticianId("beautician123");
        existingBeautician.setPassword(new BCryptPasswordEncoder().encode("password"));

        BeauticianLoginRequest loginRequest = new BeauticianLoginRequest();
        loginRequest.setBeauticianId("beautician123");
        loginRequest.setPassword("wrongpassword");

        when(beauticianRepository.findByBeauticianId(anyString())).thenReturn(existingBeautician);

        ResponseEntity<?> response = beauticianController.loginBeautician(loginRequest, httpSession);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Beautician Email id or Password", response.getBody());
    }

    @Test
    public void testDisplayAllBeautician() {
        List<Beautician> beauticians = new ArrayList<>();
        beauticians.add(new Beautician());
        beauticians.add(new Beautician());

        when(beauticianRepository.findAll()).thenReturn(beauticians);

        List<Beautician> result = beauticianController.displayAllBeautician();

        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateBeauticianSuccess() {
        Beautician existingBeautician = new Beautician();
        existingBeautician.setBeauticianId("beautician123");

        Beautician updatedBeautician = new Beautician();
        updatedBeautician.setBeauticianId("beautician123");
        updatedBeautician.setName("Updated Name");
        updatedBeautician.setAddress("Updated Address");

        when(beauticianRepository.findByBeauticianId("beautician123")).thenReturn(existingBeautician);
        when(beauticianRepository.save(any(Beautician.class))).thenReturn(updatedBeautician);

        ResponseEntity<?> response = beauticianController.updateBeautician(updatedBeautician);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedBeautician.getName(), existingBeautician.getName());
        assertEquals(updatedBeautician.getAddress(), existingBeautician.getAddress());
    }

    @Test
    public void testUpdateBeauticianNotFound() {
        Beautician updatedBeautician = new Beautician();
        updatedBeautician.setBeauticianId("beautician123");

        when(beauticianRepository.findByBeauticianId("beautician123")).thenReturn(null);

        ResponseEntity<?> response = beauticianController.updateBeautician(updatedBeautician);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateAvailabilitySuccess() {
        Beautician existingBeautician = new Beautician();
        existingBeautician.setBeauticianId("beautician123");

        when(beauticianRepository.findByBeauticianId("beautician123")).thenReturn(existingBeautician);

        ResponseEntity<?> response = beauticianController.updateAvailability("beautician123", true);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(existingBeautician.isAvailability());
    }

    @Test
    public void testUpdateAvailabilityBeauticianNotFound() {
        when(beauticianRepository.findByBeauticianId("beautician123")).thenReturn(null);

        ResponseEntity<?> response = beauticianController.updateAvailability("beautician123", true);

        assertEquals(400, response.getStatusCodeValue());
    }
}
