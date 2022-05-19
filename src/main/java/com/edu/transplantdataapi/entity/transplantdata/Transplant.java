package com.edu.transplantdataapi.entity.transplantdata;

import com.edu.transplantdataapi.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transplant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;

    @OneToOne
    private Donor donor;

    @OneToOne
    private Recipient recipient;

    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;

    @ManyToOne
    private User user;
}
