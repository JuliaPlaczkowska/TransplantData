package com.edu.transplantdataapi.business.validation;

import com.edu.transplantdataapi.entities.enums.ClassFactor;
import com.edu.transplantdataapi.entities.enums.Factor;
import com.edu.transplantdataapi.exceptions.InvalidClassFactorNameException;
import com.edu.transplantdataapi.exceptions.InvalidFactorNameException;

public class FactorsValidator {

    public static Factor validateFactorName(String factorName) {
        try{
            return Factor.valueOf(factorName);
        }
        catch (IllegalArgumentException e){
            throw new InvalidFactorNameException(factorName);
        }
    }

    public static ClassFactor validateClassFactorName(String classFactorName) {
        try{
            return ClassFactor.valueOf(classFactorName);
        }
        catch (IllegalArgumentException e){
            throw new InvalidClassFactorNameException(classFactorName);
        }
    }
}
