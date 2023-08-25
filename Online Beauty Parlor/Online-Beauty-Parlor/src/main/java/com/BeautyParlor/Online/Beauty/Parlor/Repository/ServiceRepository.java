package com.BeautyParlor.Online.Beauty.Parlor.Repository;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<Service,String> {

    Service findBySid(String sid);
}
