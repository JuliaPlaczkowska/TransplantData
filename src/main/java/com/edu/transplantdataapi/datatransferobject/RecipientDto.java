package com.edu.transplantdataapi.datatransferobject;

import com.edu.transplantdataapi.entity.transplantdata.Patient;
import com.edu.transplantdataapi.entity.transplantdata.Recipient;

public class RecipientDto {


    private PatientDto patient;

    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;


    public RecipientDto(Patient patient, String bloodRh,
                        double bodyMass,
                        String disease, String diseaseGroup,
                        String riskGroup) {
        this.patient = new PatientDto(patient);
        this.bloodRh = bloodRh;
        this.bodyMass = bodyMass;
        this.disease = disease;
        this.diseaseGroup = diseaseGroup;
        this.riskGroup = riskGroup;
    }

    public RecipientDto(Recipient recipient) {

        this.patient = new PatientDto(recipient.getPatient());
        this.bloodRh = recipient.getBloodRh();
        this.bodyMass = recipient.getBodyMass();
        this.disease = recipient.getDisease();
        this.diseaseGroup = recipient.getDiseaseGroup();
        this.riskGroup = recipient.getRiskGroup();

    }

    public Recipient convertToRecipient(){
        return new Recipient(
                this.patient.convertToPatient(),
                this.bloodRh,
                this.bodyMass,
                this.disease,
                this.diseaseGroup,
                this.riskGroup
        );
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
        return patient.convertToPatient();
    }

    public void setPatient(Patient patient) {
        this.patient = new PatientDto(patient);
    }
}
