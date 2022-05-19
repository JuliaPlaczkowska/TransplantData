package com.edu.transplantdataapi.validation;

import com.edu.transplantdataapi.enums.ClassFactor;
import com.edu.transplantdataapi.enums.Factor;
import com.edu.transplantdataapi.exceptions.InvalidClassFactorNameException;
import com.edu.transplantdataapi.exceptions.InvalidFactorNameException;

public class FactorsValidator {
    public static Factor validateFactorName(String factorName) throws InvalidFactorNameException {
        try{
            return Factor.valueOf(factorName);
        }
        catch (IllegalArgumentException e){
            throw new InvalidFactorNameException(factorName);
        }
    }

    public static ClassFactor validateClassFactorName(String classFactorName) throws InvalidClassFactorNameException {
        try{
            return ClassFactor.valueOf(classFactorName);
        }
        catch (IllegalArgumentException e){
            throw new InvalidClassFactorNameException(classFactorName);
        }
    }
}
