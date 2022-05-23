package com.edu.transplantdataapi.entity.transplantdata;

import com.edu.transplantdataapi.entity.analysis.AnalysisResult;
import com.edu.transplantdataapi.entity.prediction.Prediction;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "transplant_id")
    private Transplant transplant;
    @ManyToMany
    @JoinTable(
            name = "survival_result_analysis_result",
            joinColumns = @JoinColumn(name = "survival_result_id"),
            inverseJoinColumns = @JoinColumn(name = "analysis_result_id"))
    private List<AnalysisResult> analysisResult;
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
