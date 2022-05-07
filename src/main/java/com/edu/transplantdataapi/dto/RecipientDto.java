package com.edu.transplantdataapi.dto;

import com.edu.transplantdataapi.entity.transplantdata.Patient;
import com.edu.transplantdataapi.entity.transplantdata.Recipient;
import lombok.Data;

@Data
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
}
