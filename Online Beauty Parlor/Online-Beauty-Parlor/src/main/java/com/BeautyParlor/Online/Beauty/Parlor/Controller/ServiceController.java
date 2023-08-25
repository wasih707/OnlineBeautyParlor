package com.BeautyParlor.Online.Beauty.Parlor.Controller;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Service;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createService(@RequestBody Service service){
        Service existingService= serviceRepository.findBySid(service.getSid());
        if(existingService!=null){
            return ResponseEntity.badRequest().body("Can't create Service with the provided ID. Service already to exist. Please modify it.");
        }
        serviceRepository.save(service);
        return ResponseEntity.ok("New Service "+ service.getSid() +" created");
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateService( @RequestBody Service service ){

        Service existingService= serviceRepository.findBySid(service.getSid());

        if (existingService==null) {
            return ResponseEntity.notFound().build();
        } else {
            existingService.setDescription(service.getDescription());
            existingService.setCharges(service.getCharges());
            serviceRepository.save(existingService);
            return ResponseEntity.ok("Service Updated Successfully");
        }
    }

    @DeleteMapping("delete/{sid}")
    public ResponseEntity<?> deleteService(@PathVariable String sid){
        Service existingService= serviceRepository.findBySid(sid);
        if(existingService==null){
            return ResponseEntity.badRequest().body("Service ID "+ sid+" doesn't exist");
        }
        serviceRepository.deleteById(sid);
        return ResponseEntity.ok("Service "+ sid +" deleted successfully" );
    }

    @GetMapping("/display")
    public List<Service> displayServices(){
        return serviceRepository.findAll();
    }
}
