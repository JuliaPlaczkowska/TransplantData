package com.edu.transplantdataapi.service;


import javax.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Long id;
    private Integer number;
    private double age;
    private String bloodABO;
    private String presenceOfCMV;

    public Patient(
            Integer number,
            double age,
            String bloodABO,
            String presenceOfCMV) {
        this.number = (number  == null)? 0 : number;
        this.age = age;
        this.bloodABO = bloodABO;
        this.presenceOfCMV = presenceOfCMV;
    }

    public Patient() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getBloodABO() {
        return bloodABO;
    }

    public void setBloodABO(String bloodABO) {
        this.bloodABO = bloodABO;
    }

    public String getPresenceOfCMV() {
        return presenceOfCMV;
    }

    public void setPresenceOfCMV(String presenceOfCMV) {
        this.presenceOfCMV = presenceOfCMV;
    }
}
