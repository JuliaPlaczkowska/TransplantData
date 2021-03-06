package com.edu.transplantdataapi.business.utils;

public class ArffUtils {
    public static final String TRANSPLANT_DATA_ARFF =
            """
               @relation 'bone-marrow

               @attribute donor_age numeric
               @attribute donor_ABO {0,A,AB,B}
               @attribute donor_CMV {absent,present}
               @attribute recipient_age numeric
               @attribute recipient_ABO {0,A,AB,B}
               @attribute recipient_CMV {absent,present}
               @attribute disease {ALL,AML,chronic,lymphoma,nonmalignant}
               @attribute disease_group {malignant,nonmalignant}
               @attribute HLA_match {10/10,7/10,8/10,9/10}
               @attribute HLA_mismatch {matched,mismatched}
               @attribute antigen {0,1,2,3}
               @attribute allel {0,1,2,3,4}
               @attribute HLA_group_1 {DRB1_cell,matched,mismatched,one_allel,one_antigen,three_diffs,two_diffs}
               @attribute risk_group {high,low}
               @attribute stem_cell_source {bone_marrow,peripheral_blood}
               @attribute tx_post_relapse {no,yes}
               @attribute CD34_x1e6_per_kg numeric
               @attribute CD3_x1e8_per_kg numeric
               @attribute survival_status {0,1}

               @data
               """;
}
