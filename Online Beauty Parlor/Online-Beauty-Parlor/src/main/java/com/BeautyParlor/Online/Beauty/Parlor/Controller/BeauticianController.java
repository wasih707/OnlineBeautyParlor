package com.BeautyParlor.Online.Beauty.Parlor.Controller;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.BeauticianLoginRequest;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import com.BeautyParlor.Online.Beauty.Parlor.Entity.CustomerLoginRequest;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.BeauticianRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.CustomerRepository;
import com.BeautyParlor.Online.Beauty.Parlor.Services.BeauticianService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautician")
public class BeauticianController {

    @Autowired
    private BeauticianRepository beauticianRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerBeautician(@RequestBody Beautician beautician, HttpSession httpSession){

        Beautician existingBeautician = beauticianRepository.findByBeauticianId(beautician.getBeauticianId());
        if(existingBeautician!=null){
            return ResponseEntity.badRequest().body("Beautician is already registered, Please login Beautician");
        }
        beautician.setPassword(new BCryptPasswordEncoder().encode(beautician.getPassword()));
        beauticianRepository.save(beautician);
        return ResponseEntity.ok("Beautician Registered Successfully");
    }

@PostMapping("/login")
public ResponseEntity<?> loginBeautician(@RequestBody BeauticianLoginRequest beauticianLoginRequest , HttpSession httpSession){
    Beautician existingBeautician= beauticianRepository.findByBeauticianId(beauticianLoginRequest.getBeauticianId());
    if(existingBeautician==null || !new BCryptPasswordEncoder().matches(beauticianLoginRequest.getPassword(),existingBeautician.getPassword())){
        return ResponseEntity.badRequest().body("Invalid Beautician Email id or Password");

    }
    httpSession.setAttribute("beautician",existingBeautician);
    return ResponseEntity.ok("Beautician Authenticated Successfully");
}

@GetMapping("/findAll")
    public List<Beautician> displayAllBeautician(){
        return beauticianRepository.findAll();
}

@PutMapping("/update")
    public ResponseEntity<?> updateBeautician(@RequestBody Beautician beautician){
        Beautician existingBeautician = beauticianRepository.findByBeauticianId(beautician.getBeauticianId());
        if(existingBeautician==null){
            return ResponseEntity.badRequest().body("No such Beautician exist: "+ beautician.getBeauticianId());

        }
        existingBeautician.setAvailability(beautician.isAvailability());
        existingBeautician.setPassword(beautician.getPassword());
        existingBeautician.setAddress(beautician.getAddress());
        existingBeautician.setContactNo(beautician.getContactNo());
        existingBeautician.setName(beautician.getName());
        beauticianRepository.save(existingBeautician);
        return ResponseEntity.ok(existingBeautician);
}


    @PutMapping("/setAvailability/{beauticianId}")
    public ResponseEntity<?> updateAvailability(@PathVariable String beauticianId, @RequestParam boolean isAvailable){
        Beautician existingBeautician = beauticianRepository.findByBeauticianId(beauticianId);
        if(existingBeautician==null){
            return ResponseEntity.badRequest().body("No such Beautician exist: "+ beauticianId);

        }
        existingBeautician.setAvailability(isAvailable);
        beauticianRepository.save(existingBeautician);
        return ResponseEntity.ok("Availability status is updated");
    }

}
