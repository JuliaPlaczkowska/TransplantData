package com.edu.transplantdataapi.datatransferobject.prediction;

import com.edu.transplantdataapi.entity.Donor;
import com.edu.transplantdataapi.entity.Recipient;
import com.edu.transplantdataapi.entity.Transplant;
import lombok.Data;

@Data
public class TransplantPredictionDto {

    //Recipient
    private Integer recipientNumber;
    private double recipientAge;
    private String recipientBloodABO;
    private String recipientPresenceOfCMV;
    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;

    //Donor
    private Integer donorNumber;
    private double donorAge;
    private String donorBloodABO;
    private String donorPresenceOfCMV;
    private String stemCellSource;


    //Transplant
    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;

    public TransplantPredictionDto(Transplant transplant) {

        Recipient recipient = transplant.getRecipient();
        this.recipientNumber = recipient.getPatient().getNumber();
        this.recipientAge = recipient.getPatient().getAge();
        this.recipientBloodABO = recipient.getPatient().getBloodABO();
        this.recipientPresenceOfCMV = recipient.getPatient().getPresenceOfCMV();
        this.bloodRh = recipient.getBloodRh();
        this.bodyMass = recipient.getBodyMass();
        this.disease = recipient.getDisease();
        this.diseaseGroup = recipient.getDiseaseGroup();
        this.riskGroup = recipient.getRiskGroup();

        Donor donor = transplant.getDonor();
        this.donorNumber = donor.getPatient().getNumber();
        this.donorAge = donor.getPatient().getAge();
        this.donorBloodABO = donor.getPatient().getBloodABO();
        this.donorPresenceOfCMV = donor.getPatient().getPresenceOfCMV();
        this.stemCellSource = donor.getStemCellSource();


        this.matchHLA = transplant.getMatchHLA();
        this.mismatchHLA = transplant.getMismatchHLA();
        this.antigen = transplant.getAntigen();
        this.allele = transplant.getAllele();
        this.group1HLA = transplant.getGroup1HLA();
        this.postRelapse = transplant.getPostRelapse();
        this.CD34perKg = transplant.getCD34perKg();
        this.CD3perKg = transplant.getCD3perKg();

    }
}
