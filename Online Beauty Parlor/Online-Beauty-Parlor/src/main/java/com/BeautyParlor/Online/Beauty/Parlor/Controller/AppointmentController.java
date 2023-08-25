package com.BeautyParlor.Online.Beauty.Parlor.Controller;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Appointment;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Service;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.AppointmentRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.BeauticianRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.CustomerRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BeauticianRepository beauticianRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @PostMapping("/set")
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment){
        Appointment existingAppointment=  appointmentRepository.findByAppointmentId(appointment.getAppointmentId());

        if(existingAppointment!=null){
            return ResponseEntity.badRequest().body("Appoint already exists with similar Appointment ID: "+ appointment.getAppointmentId());
        }
        Customer customer= appointment.getCustomer();
        Customer existingCustomer= customerRepository.findByUserId(customer.getUserId());
        if(existingCustomer==null){
            return ResponseEntity.badRequest().body("No such Customer exist: "+ customer.getUserId());
        }
        Beautician beautician= appointment.getBeautician();
        Beautician existingBeautician = beauticianRepository.findByBeauticianId(beautician.getBeauticianId());
        if(existingBeautician==null)
        {
            return ResponseEntity.badRequest().body("No such beautician exist");
        }
        if(!existingBeautician.isAvailability()) {
            return ResponseEntity.badRequest().body("Beautician is not Available");
        }

        Service service= appointment.getService();
        Service existingService= serviceRepository.findBySid(service.getSid());
        if(existingService==null){
            return ResponseEntity.badRequest().body("No such service exists");
        }


        existingBeautician.setAvailability(false);
        beauticianRepository.save(existingBeautician);
        appointment.setStatus("Active");

        appointment.setBeautician(existingBeautician);
        appointment.setCustomer(existingCustomer);
        appointment.setService(existingService);
        appointmentRepository.save(appointment);
        return ResponseEntity.ok("Appointment Set Successfully!!");
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody Appointment appointment){
        Appointment existingAppointment= appointmentRepository.findByAppointmentId(appointment.getAppointmentId());
        if(existingAppointment==null){
            return ResponseEntity.badRequest().body("No Such Appointment exist: "+ appointment.getAppointmentId());
        }
        Beautician beautician = appointment.getBeautician();
        Beautician existingBeautician = beauticianRepository.findByBeauticianId(beautician.getBeauticianId());
        existingBeautician.setAvailability(true);
        beauticianRepository.save(existingBeautician);

        appointment.setStatus("Completed");

        appointment.setBeautician(existingBeautician);


        appointmentRepository.save(appointment);
        return ResponseEntity.ok("Beautician :"+ existingBeautician.getName() +" is avaialble now to take task" );

    }

    @GetMapping("/display")
    public List<Appointment> displayAllAppointments(){
        return appointmentRepository.findAll();
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable String appointmentId){
        Appointment existingAppointment= appointmentRepository.findByAppointmentId(appointmentId);
        if(existingAppointment==null){
            return ResponseEntity.badRequest().body("No Such Appointment exist: "+ appointmentId);
        }
        appointmentRepository.deleteById(appointmentId);
        return ResponseEntity.ok("Appointment Deleted Successfully");
    }

}
