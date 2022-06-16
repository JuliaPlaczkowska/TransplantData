package com.edu.transplantdataapi.business.validation;

import com.edu.transplantdataapi.exceptions.InvalidSignificanceValueException;

public class SignificanceValidator {
    public static void validateSignificance(double value) {
        if(value<=0.0 || value > 0.5)
            throw new InvalidSignificanceValueException(value);
    }
}
