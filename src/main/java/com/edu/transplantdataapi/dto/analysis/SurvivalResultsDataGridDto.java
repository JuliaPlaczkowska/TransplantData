package com.edu.transplantdataapi.dto.analysis;

import com.edu.transplantdataapi.entities.transplantdata.Donor;
import com.edu.transplantdataapi.entities.transplantdata.Recipient;
import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entities.transplantdata.Transplant;
import lombok.Data;
@Data
public class SurvivalResultsDataGridDto {

    private Integer recipientNumber;
    private double recipientAge;
    private String recipientBloodABO;
    private String recipientPresenceOfCMV;
    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;

    private Integer donorNumber;
    private double donorAge;
    private String donorBloodABO;
    private String donorPresenceOfCMV;
    private String stemCellSource;

    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;

    private double ancRecovery;
    private double pltRecovery;
    private boolean acuteGvHD_II_III_IV;
    private boolean acuteGvHD_III_IV;
    private double time_to_acuteGvHD_III_IV;
    private boolean extensiveChronicGvHD;
    private boolean relapse;
    private double survivalTime;
    private boolean survivalStatus;


    public SurvivalResultsDataGridDto(SurvivalResult survivalResult) {

        Recipient recipient = survivalResult.getTransplant().getRecipient();
        this.recipientNumber = recipient.getPatient().getNumber();
        this.recipientAge = recipient.getPatient().getAge();
        this.recipientBloodABO = recipient.getPatient().getBloodABO();
        this.recipientPresenceOfCMV = recipient.getPatient().getPresenceOfCMV();
        this.bloodRh = recipient.getBloodRh();
        this.bodyMass = recipient.getBodyMass();
        this.disease = recipient.getDisease();
        this.diseaseGroup = recipient.getDiseaseGroup();
        this.riskGroup = recipient.getRiskGroup();

        Donor donor = survivalResult.getTransplant().getDonor();
        this.donorNumber = donor.getPatient().getNumber();
        this.donorAge = donor.getPatient().getAge();
        this.donorBloodABO = donor.getPatient().getBloodABO();
        this.donorPresenceOfCMV = donor.getPatient().getPresenceOfCMV();
        this.stemCellSource = donor.getStemCellSource();

        Transplant transplant = survivalResult.getTransplant();
        this.matchHLA = transplant.getMatchHLA();
        this.mismatchHLA = transplant.isMismatchHLA();
        this.antigen = transplant.getAntigen();
        this.allele = transplant.getAllele();
        this.group1HLA = transplant.getGroup1HLA();
        this.postRelapse = transplant.isPostRelapse();
        this.CD34perKg = transplant.getCD34perKg();
        this.CD3perKg = transplant.getCD3perKg();

        this.ancRecovery = survivalResult.getAncRecovery();
        this.pltRecovery = survivalResult.getPltRecovery();
        this.acuteGvHD_II_III_IV = survivalResult.isAcuteGvHD_II_III_IV();
        this.acuteGvHD_III_IV = survivalResult.isAcuteGvHD_III_IV();
        this.time_to_acuteGvHD_III_IV = survivalResult.getTime_to_acuteGvHD_III_IV();
        this.extensiveChronicGvHD = survivalResult.isExtensiveChronicGvHD();
        this.relapse = survivalResult.isRelapse();
        this.survivalTime = survivalResult.getSurvivalTime();
        this.survivalStatus = survivalResult.isSurvivalStatus();

    }
}


