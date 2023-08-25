package com.BeautyParlor.Online.Beauty.Parlor.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection= "Beautician")
public class Beautician {
    @Id
    private String beauticianId;
    private String name;
    private String address;
    private String contactNo;
    private String password;
    private boolean availability;


    public String getBeauticianId() {
        return beauticianId;
    }

    public void setBeauticianId(String beauticianId) {
        this.beauticianId = beauticianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
