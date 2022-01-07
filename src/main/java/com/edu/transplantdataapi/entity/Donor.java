package com.edu.transplantdataapi.entity;

import javax.persistence.*;

@Entity
public class Donor{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Long id;

    @OneToOne
    private Patient patient;

    private String stemCellSource;

    public Donor(Patient patient, String stemCellSource) {
        this.patient = patient;
        this.stemCellSource = stemCellSource;
    }

    public Donor() {

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStemCellSource() {
        return stemCellSource;
    }

    public void setStemCellSource(String stemCellSource) {
        this.stemCellSource = stemCellSource;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    }
