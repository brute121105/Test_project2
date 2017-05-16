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
    private int is_add_contact;

    public int getIs_add_contact() {
        return is_add_contact;
    }

    public void setIs_add_contact(int is_add_contact) {
        this.is_add_contact = is_add_contact;
    }

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
