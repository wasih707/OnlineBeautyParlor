package com.BeautyParlor.Online.Beauty.Parlor.Repository;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    Appointment findByAppointmentId(String appointmentId);
}
