package com.edu.transplantdataapi.entity.transplantdata;

import com.edu.transplantdataapi.entity.analysis.AnalysisResult;
import com.edu.transplantdataapi.entity.prediction.Prediction;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SurvivalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    @OneToOne
    private Transplant transplant;

    @ManyToOne
    private AnalysisResult analysisResult;

    @ManyToOne
    private Prediction prediction;

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
