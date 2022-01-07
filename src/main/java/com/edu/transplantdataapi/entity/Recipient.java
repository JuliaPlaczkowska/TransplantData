package com.edu.transplantdataapi.entity;

import javax.persistence.*;

@Entity
public class Recipient{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Patient patient;

    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;

    public Recipient(Patient patient,
                     String bloodRh,
                     double bodyMass,
                     String disease,
                     String diseaseGroup,
                     String riskGroup) {
        this.patient = patient;
        this.bloodRh = bloodRh;
        this.bodyMass = bodyMass;
        this.disease = disease;
        this.diseaseGroup = diseaseGroup;
        this.riskGroup = riskGroup;
    }

    public Recipient() {

    }

    public double getBodyMass() {
        return bodyMass;
    }

    public void setBodyMass(double bodyMass) {
        this.bodyMass = bodyMass;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiseaseGroup() {
        return diseaseGroup;
    }

    public void setDiseaseGroup(String diseaseGroup) {
        this.diseaseGroup = diseaseGroup;
    }

    public String getRiskGroup() {
        return riskGroup;
    }

    public void setRiskGroup(String riskGroup) {
        this.riskGroup = riskGroup;
    }

    public String getBloodRh() {
        return bloodRh;
    }

    public void setBloodRh(String bloodRh) {
        this.bloodRh = bloodRh;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
