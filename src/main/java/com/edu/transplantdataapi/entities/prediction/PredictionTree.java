package com.edu.transplantdataapi.entities.prediction;

import com.edu.transplantdataapi.entities.enums.ClassFactor;
import com.edu.transplantdataapi.entities.enums.Factor;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Factor> factors;
    @Enumerated(EnumType.STRING)
    private ClassFactor classFactor;
    private J48 tree;

}
