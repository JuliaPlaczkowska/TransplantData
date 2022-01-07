package com.edu.transplantdataapi.datatransferobject.prediction;

import lombok.Data;

@Data
public class TransplantPredictionDto {

    //recipient
    private Integer recipientNumber;
    private double recipientAge;
    private String recipientBloodABO;
    private String recipientPresenceOfCMV;
    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;

    //donor
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
}
