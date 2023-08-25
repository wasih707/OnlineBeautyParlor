package com.BeautyParlor.Online.Beauty.Parlor.testController;

import com.BeautyParlor.Online.Beauty.Parlor.Controller.AppointmentController;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Appointment;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Service;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.AppointmentRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.BeauticianRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.CustomerRepository;
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
public class TestAppointmentController {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private BeauticianRepository beauticianRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private AppointmentController appointmentController;

    @Test
    public void testCreateAppointmentSuccess() {
        Appointment appointment = new Appointment();
        Customer customer = new Customer();
        customer.setUserId("user123");
        Beautician beautician = new Beautician();
        beautician.setBeauticianId("beautician123");
        Service service = new Service();
        service.setSid("service123");

        appointment.setAppointmentId("appointment123");
        appointment.setCustomer(customer);
        appointment.setBeautician(beautician);
        appointment.setService(service);

        when(appointmentRepository.findByAppointmentId("appointment123")).thenReturn(null);
        when(customerRepository.findByUserId("user123")).thenReturn(customer);
        when(beauticianRepository.findByBeauticianId("beautician123")).thenReturn(beautician);


        ResponseEntity<?> response = appointmentController.createAppointment(appointment);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testCreateAppointmentAppointmentExists() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("appointment123");

        when(appointmentRepository.findByAppointmentId("appointment123")).thenReturn(appointment);

        ResponseEntity<?> response = appointmentController.createAppointment(appointment);

        assertEquals(400, response.getStatusCodeValue());
    }
    @Test
    public void testUpdateStatusSuccess() {
        Appointment appointment = new Appointment();
        Beautician beautician = new Beautician();
        beautician.setBeauticianId("beautician123");

        appointment.setAppointmentId("appointment123");
        appointment.setBeautician(beautician);

        when(appointmentRepository.findByAppointmentId("appointment123")).thenReturn(appointment);
        when(beauticianRepository.findByBeauticianId("beautician123")).thenReturn(beautician);
        when(beauticianRepository.save(beautician)).thenReturn(beautician);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        ResponseEntity<?> response = appointmentController.updateStatus(appointment);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Completed", appointment.getStatus());
    }

    @Test
    public void testUpdateStatusAppointmentNotFound() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("appointment123");

        when(appointmentRepository.findByAppointmentId("appointment123")).thenReturn(null);

        ResponseEntity<?> response = appointmentController.updateStatus(appointment);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testDisplayAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        when(appointmentRepository.findAll()).thenReturn(appointmentList);

        List<Appointment> response = appointmentController.displayAllAppointments();

        assertEquals(appointmentList, response);
    }

    @Test
    public void testDeleteAppointmentSuccess() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("appointment123");

        when(appointmentRepository.findByAppointmentId("appointment123")).thenReturn(appointment);

        ResponseEntity<?> response = appointmentController.deleteAppointment("appointment123");

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteAppointmentNotFound() {
        when(appointmentRepository.findByAppointmentId("appointment123")).thenReturn(null);

        ResponseEntity<?> response = appointmentController.deleteAppointment("appointment123");

        assertEquals(400, response.getStatusCodeValue());
    }


}
