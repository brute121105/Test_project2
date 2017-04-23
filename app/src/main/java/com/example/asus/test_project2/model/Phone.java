package com.example.asus.test_project2.model;

import org.litepal.crud.DataSupport;

/**
 * Created by asus on 2017/4/22.
 */

public class Phone extends DataSupport{
    private  int id;
    private String phone;
    private String phoneText;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneText() {
        return phoneText;
    }

    public void setPhoneText(String phoneText) {
        this.phoneText = phoneText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
