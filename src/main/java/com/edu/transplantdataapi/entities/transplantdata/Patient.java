package com.edu.transplantdataapi.entities.transplantdata;


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
    private Integer number;
    private double age;
    private String bloodABO;
    private String presenceOfCMV;
}
