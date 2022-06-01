package com.edu.transplantdataapi.dto.analysis;

import com.edu.transplantdataapi.entities.transplantdata.patient.Donor;
import com.edu.transplantdataapi.entities.transplantdata.patient.Recipient;
import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entities.transplantdata.Transplant;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurvivalResultsDataGridDto {

    private long recipientNumber;
    private double recipientAge;
    private String recipientBloodABO;
    private String recipientPresenceOfCMV;
    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;

    private long donorNumber;
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
}


