package com.example.demo.validator;

import com.example.demo.validation.StatusValidation;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class StatusValidator implements ConstraintValidator<StatusValidation, Integer> {
    private List<String> statusType;

    @Override
    public void initialize(StatusValidation constraintAnnotation) {
        this.statusType = Arrays.asList(constraintAnnotation.statusType());
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value != null) {
            if (!this.statusType.contains(String.valueOf(value))) {
                return false;
            }
        }
        return true;
    }
}
