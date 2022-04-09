package com.edu.transplantdataapi.entity.transplantdata;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Data @Getter @Setter
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
}
