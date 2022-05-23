package com.edu.transplantdataapi.validation;

import com.edu.transplantdataapi.exceptions.InvalidSignificanceValue;

public class SignificanceValidator {
    public static void validateSignificance(double value)
            throws InvalidSignificanceValue
    {
        if(value<=0.0 || value > 0.5)
            throw new InvalidSignificanceValue(value);
    }
}
