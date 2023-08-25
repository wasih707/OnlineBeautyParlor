package com.BeautyParlor.Online.Beauty.Parlor.Repository;

import com.BeautyParlor.Online.Beauty.Parlor.Entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer findByUserId(String UserId);
}
