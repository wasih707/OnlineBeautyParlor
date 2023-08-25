package com.BeautyParlor.Online.Beauty.Parlor.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Appointment")
public class Appointment {
    @Id
    private String appointmentId;
    private Customer customer;
    private Beautician beautician;
    private Service service;
    private String status;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Beautician getBeautician() {
        return beautician;
    }

    public void setBeautician(Beautician beautician) {
        this.beautician = beautician;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
