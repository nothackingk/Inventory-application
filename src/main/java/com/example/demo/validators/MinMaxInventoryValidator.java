package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinMaxInventoryValidator
        implements ConstraintValidator<ValidMinMaxInventory, Part> {

    @Override
    public boolean isValid(Part part,
                           ConstraintValidatorContext context) {

        if (part == null) {
            return true;
        }

        int inv = part.getInv();
        int min = part.getMinInv();
        int max = part.getMaxInv();

        if (inv < min) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Inventory cannot be less than the minimum inventory")
                    .addConstraintViolation();
            return false;
        }

        if (max > 0 && inv > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Inventory cannot be greater than the maximum inventory")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}