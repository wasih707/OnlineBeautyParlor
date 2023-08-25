package com.BeautyParlor.Online.Beauty.Parlor.Services;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import com.BeautyParlor.Online.Beauty.Parlor.Repository.BeauticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeauticianService {
    @Autowired
    private BeauticianRepository beauticianRepository;
    public Beautician createBeautician(Beautician beautician){
        return beauticianRepository.save(beautician);
    }
}
