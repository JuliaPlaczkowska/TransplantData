package com.edu.transplantdataapi.dto.prediction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurvivalResultDto {
    private Long number;
    private TransplantDto transplant;
    private int ancRecovery;
    private int pltRecovery;
    private boolean acuteGvHD_II_III_IV;
    private boolean acuteGvHD_III_IV;
    private double time_to_acuteGvHD_III_IV;
    private boolean extensiveChronicGvHD;
    private boolean relapse;
    private double survivalTime;
    private boolean survivalStatus;
}
