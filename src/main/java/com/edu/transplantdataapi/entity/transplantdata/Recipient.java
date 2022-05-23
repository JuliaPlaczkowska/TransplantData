package com.edu.transplantdataapi.entity.transplantdata;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recipient{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;
}
