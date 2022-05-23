package com.edu.transplantdataapi.dto.prediction;

import com.edu.transplantdataapi.dto.patient.DonorDto;
import com.edu.transplantdataapi.dto.patient.RecipientDto;
import com.edu.transplantdataapi.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransplantDto {

    private RecipientDto recipient;
    private DonorDto donor;
    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;
    private UserDto user;
}
