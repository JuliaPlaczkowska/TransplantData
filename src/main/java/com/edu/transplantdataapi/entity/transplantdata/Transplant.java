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

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;

    @OneToOne(mappedBy = "transplant")
    private SurvivalResult survivalResult;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;
}
