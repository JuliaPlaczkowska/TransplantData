package com.edu.transplantdataapi.dto.prediction;

import lombok.Data;

/*
Transplant DTO class fo mapping into weka instance.
Properties are the same as in bone-marrow.arrf file
 */
@Data
public class TransplantToPredictDto {
    private double donorAge;
    private String donorBloodABO;
    private String donorPresenceOfCMV;
    private double recipientAge;
    private String recipientBloodABO;
    private String recipientPresenceOfCMV;
    private String disease;
    private String diseaseGroup;
    private String matchHLA;
    private String mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private String riskGroup;
    private String stemCellSource;
    private String postRelapse;
    private double CD34perKg;
    private double CD3perKg;
}
