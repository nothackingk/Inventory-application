package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = MinMaxInventoryValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidMinMaxInventory {

    String message() default
            "Inventory must be between the minimum and maximum values";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}