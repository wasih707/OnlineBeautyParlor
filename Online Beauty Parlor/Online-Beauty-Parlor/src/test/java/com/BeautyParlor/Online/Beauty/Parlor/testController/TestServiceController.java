package com.BeautyParlor.Online.Beauty.Parlor.testController;

import com.BeautyParlor.Online.Beauty.Parlor.Controller.ServiceController;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Service;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestServiceController {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceController serviceController;

    @Test
    public void testCreateServiceSuccess() {
        Service newService = new Service();
        newService.setSid("service123");

        when(serviceRepository.findBySid("service123")).thenReturn(null);

        ResponseEntity<?> response = serviceController.createService(newService);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testCreateServiceServiceExists() {
        Service existingService = new Service();
        existingService.setSid("service123");

        when(serviceRepository.findBySid("service123")).thenReturn(existingService);

        ResponseEntity<?> response = serviceController.createService(existingService);

        assertEquals(400, response.getStatusCodeValue());
    }
    @Test
    public void testUpdateServiceSuccess() {
        Service existingService = new Service();
        existingService.setSid("service123");

        Service updatedService = new Service();
        updatedService.setSid("service123");
        updatedService.setDescription("Updated Description");
        updatedService.setCharges(25.0f);

        when(serviceRepository.findBySid("service123")).thenReturn(existingService);
        when(serviceRepository.save(existingService)).thenReturn(updatedService);

        ResponseEntity<String> response = serviceController.updateService(updatedService);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateServiceNotFound() {
        Service updatedService = new Service();
        updatedService.setSid("service123");
        updatedService.setDescription("Updated Description");
        updatedService.setCharges(25.0f);

        when(serviceRepository.findBySid("service123")).thenReturn(null);

        ResponseEntity<String> response = serviceController.updateService(updatedService);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteServiceSuccess() {
        String sid = "service123";
        Service existingService = new Service();
        existingService.setSid(sid);

        when(serviceRepository.findBySid(sid)).thenReturn(existingService);

        ResponseEntity<?> response = serviceController.deleteService(sid);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteServiceNotFound() {
        String sid = "service123";

        when(serviceRepository.findBySid(sid)).thenReturn(null);

        ResponseEntity<?> response = serviceController.deleteService(sid);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testDisplayServices() {
        List<Service> services = new ArrayList<>();
        services.add(new Service());
        services.add(new Service());

        when(serviceRepository.findAll()).thenReturn(services);

        List<Service> response = serviceController.displayServices();

        assertEquals(2, response.size());
    }

}
