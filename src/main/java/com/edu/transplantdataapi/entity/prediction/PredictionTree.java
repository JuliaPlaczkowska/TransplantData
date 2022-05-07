package com.edu.transplantdataapi.entity.prediction;

import com.edu.transplantdataapi.datatransferobject.prediction.TransplantDto;
import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import lombok.*;
import weka.classifiers.trees.J48;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PredictionTree {

    //może do bazy zapisywać tylko wybrane rzeczy

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<String> factors;
    private String classFactor;
    private J48 tree;

}
