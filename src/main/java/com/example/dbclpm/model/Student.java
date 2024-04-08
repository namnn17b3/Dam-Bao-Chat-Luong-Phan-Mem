package com.example.dbclpm.model;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String password;
    private Date dateOfBirth;
    private String administrativeClass;
    
    public Student() {}
    
    public Student(int id, String name, String email, String phone, String address, String gender, String password,
            Date dateOfBirth, String administrativeClass) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.administrativeClass = administrativeClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdministrativeClass() {
        return administrativeClass;
    }

    public void setAdministrativeClass(String administrativeClass) {
        this.administrativeClass = administrativeClass;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
                + ", gender=" + gender + ", password=" + password + ", dateOfBirth=" + dateOfBirth
                + ", administrativeClass=" + administrativeClass + "]";
    }
}
