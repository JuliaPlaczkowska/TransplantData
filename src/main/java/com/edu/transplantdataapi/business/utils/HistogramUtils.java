package com.edu.transplantdataapi.business.utils;

import java.util.List;

public class HistogramUtils {

    public static List<String> getMatchHlaLabels() {
        return List.of(
                "10",
                "9",
                "8",
                "7"
        );
    }

    public static List<String> getBooleanLabels() {
        return List.of(
                "true",
                "false"
        );
    }

    public static List<String> getAntigenLabels() {
        return List.of(
                "0",
                "1",
                "2",
                "3"
        );
    }

    public static List<String> getAlleleLabels() {
        return List.of(
                "0",
                "1",
                "2",
                "3",
                "4"
        );
    }

    public static List<String> getGroupHlaLabels() {
        return List.of(
                "matched",
                "mismatched",
                "one_antigen",
                "one_allel",
                "DRB1_cell",
                "two_diffs",
                "three_diffs"
        );
    }

    public static List<String> getRiskGroupLabels() {
        return List.of(
                "low",
                "high"
        );
    }

    public static List<String> getCdLabels() {
        return List.of(
                "<5,0",
                "5,0+",
                "10+",
                "15+",
                "20+",
                "25+"
        );
    }
}
