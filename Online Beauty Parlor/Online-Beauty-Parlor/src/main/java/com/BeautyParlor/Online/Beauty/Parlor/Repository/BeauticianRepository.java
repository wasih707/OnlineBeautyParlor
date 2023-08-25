package com.BeautyParlor.Online.Beauty.Parlor.Repository;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Beautician;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeauticianRepository extends MongoRepository<Beautician,String> {
    Beautician findByBeauticianId(String beauticianId);
}
