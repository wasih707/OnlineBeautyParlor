package com.BeautyParlor.Online.Beauty.Parlor.testEntity;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Appointment;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TestAppointment {
    private Appointment appointment;
    private Customer mockCustomer;
    private Beautician mockBeautician;
    private Service mockService;

    @BeforeEach
    public void setUp() {
        appointment = new Appointment();
        mockCustomer = mock(Customer.class);
        mockBeautician = mock(Beautician.class);
        mockService = mock(Service.class);
    }

    @Test
    public void testGetAndSetAppointmentId() {
        appointment.setAppointmentId("appointment123");
        assertEquals("appointment123", appointment.getAppointmentId());
    }

    @Test
    public void testGetAndSetCustomer() {
        appointment.setCustomer(mockCustomer);
        assertEquals(mockCustomer, appointment.getCustomer());
    }

    @Test
    public void testGetAndSetBeautician() {
        appointment.setBeautician(mockBeautician);
        assertEquals(mockBeautician, appointment.getBeautician());
    }

    @Test
    public void testGetAndSetService() {
        appointment.setService(mockService);
        assertEquals(mockService, appointment.getService());
    }

    @Test
    public void testGetAndSetStatus() {
        appointment.setStatus("Scheduled");
        assertEquals("Scheduled", appointment.getStatus());
    }
}
