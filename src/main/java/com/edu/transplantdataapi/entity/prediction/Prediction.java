package com.edu.transplantdataapi.entity.prediction;


import com.edu.transplantdataapi.entity.transplantdata.Transplant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PredictionTree predictionTree;

    @OneToOne
    private Transplant transplant;
    private String classificationResult;
    private double accuracy;
}
