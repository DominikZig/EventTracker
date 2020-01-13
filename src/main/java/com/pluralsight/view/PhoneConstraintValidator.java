package com.pluralsight.view;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String>
{

    @Override
    public void initialize(Phone phone)
    {

    }

    //This method could potentially be used to interact with a DB to do business rules validation, not just syntax
    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt)
    {
        if (phoneField == null)
        {
            return false;
        }

        return phoneField.matches("[0-9()-]*");
    }
}
