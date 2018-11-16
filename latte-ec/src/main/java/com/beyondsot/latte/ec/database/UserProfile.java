package com.beyondsot.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private long userid = 0;
    private String name = null;
    private String avater = null;
    private String gender = null;
    private String address = null;
    @Generated(hash = 1876333058)
    public UserProfile(long userid, String name, String avater, String gender,
            String address) {
        this.userid = userid;
        this.name = name;
        this.avater = avater;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserid() {
        return this.userid;
    }
    public void setUserid(long userid) {
        this.userid = userid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvater() {
        return this.avater;
    }
    public void setAvater(String avater) {
        this.avater = avater;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

