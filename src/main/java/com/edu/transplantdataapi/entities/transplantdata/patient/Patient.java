package com.edu.transplantdataapi.entities.transplantdata.patient;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Long id;
    private long number;
    private double age;
    private String bloodABO;
    private String presenceOfCMV;

    @OneToOne(mappedBy = "patient")
    private Donor donor;

    @OneToOne(mappedBy = "patient")
    private Recipient recipient;
}
