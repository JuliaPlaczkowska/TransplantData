package com.edu.transplantdataapi.dto.prediction;

import com.edu.transplantdataapi.dto.DonorDto;
import com.edu.transplantdataapi.dto.RecipientDto;
import com.edu.transplantdataapi.dto.UserDto;
import com.edu.transplantdataapi.entity.transplantdata.Donor;
import com.edu.transplantdataapi.entity.transplantdata.Recipient;
import com.edu.transplantdataapi.entity.transplantdata.Transplant;
import lombok.Data;

@Data
public class TransplantDto {

    private RecipientDto recipient;
    private DonorDto donor;

//    //Recipient
//    private Integer recipientNumber;
//    private double recipientAge;
//    private String recipientBloodABO;
//    private String recipientPresenceOfCMV;
//    private String bloodRh;
//    private double bodyMass;
//    private String disease;
//    private String diseaseGroup;
//    private String riskGroup;
//
//    //Donor
//    private Integer donorNumber;
//    private double donorAge;
//    private String donorBloodABO;
//    private String donorPresenceOfCMV;
//    private String stemCellSource;

    //Transplant
    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;

    private UserDto user;

    public TransplantDto(Transplant transplant) {

        Recipient recipient = transplant.getRecipient();
        this.recipient = new RecipientDto(recipient);
//        this.recipientNumber = recipient.getPatient().getNumber();
//        this.recipientAge = recipient.getPatient().getAge();
//        this.recipientBloodABO = recipient.getPatient().getBloodABO();
//        this.recipientPresenceOfCMV = recipient.getPatient().getPresenceOfCMV();
//        this.bloodRh = recipient.getBloodRh();
//        this.bodyMass = recipient.getBodyMass();
//        this.disease = recipient.getDisease();
//        this.diseaseGroup = recipient.getDiseaseGroup();
//        this.riskGroup = recipient.getRiskGroup();

        Donor donor = transplant.getDonor();
        this.donor = new DonorDto(donor);
//        this.donorNumber = donor.getPatient().getNumber();
//        this.donorAge = donor.getPatient().getAge();
//        this.donorBloodABO = donor.getPatient().getBloodABO();
//        this.donorPresenceOfCMV = donor.getPatient().getPresenceOfCMV();
//        this.stemCellSource = donor.getStemCellSource();


        this.matchHLA = transplant.getMatchHLA();
        this.mismatchHLA = transplant.getMismatchHLA();
        this.antigen = transplant.getAntigen();
        this.allele = transplant.getAllele();
        this.group1HLA = transplant.getGroup1HLA();
        this.postRelapse = transplant.getPostRelapse();
        this.CD34perKg = transplant.getCD34perKg();
        this.CD3perKg = transplant.getCD3perKg();

    }

    public Transplant convertToTransplant(UserDto user) {
        return new Transplant(
                this.donor.convertToDonor(),
                this.recipient.convertToRecipient(),
                this.matchHLA,
                this.mismatchHLA,
                this.antigen,
                this.allele,
                this.group1HLA,
                this.postRelapse,
                this.CD34perKg,
                this.CD3perKg,
                user.convertToUser()
        );
    }

    public TransplantDto(RecipientDto recipient,
                         DonorDto donor,
                         int matchHLA,
                         boolean mismatchHLA,
                         int antigen,
                         int allele,
                         String group1HLA,
                         boolean postRelapse,
                         double CD34perKg,
                         double CD3perKg) {
        this.recipient = recipient;
        this.donor = donor;
        this.matchHLA = matchHLA;
        this.mismatchHLA = mismatchHLA;
        this.antigen = antigen;
        this.allele = allele;
        this.group1HLA = group1HLA;
        this.postRelapse = postRelapse;
        this.CD34perKg = CD34perKg;
        this.CD3perKg = CD3perKg;
    }

    public TransplantDto() {
    }

  }
