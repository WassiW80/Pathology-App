package com.example.pathology;

public class Member {
    private String name;
    private String password;
    private String hospitalOrClinic;
    private String phoneNumber;
    private String branch;

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHospitalOrClinic() {
        return hospitalOrClinic;
    }

    public void setHospitalOrClinic(String hospitalOrClinic) {
        this.hospitalOrClinic = hospitalOrClinic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
