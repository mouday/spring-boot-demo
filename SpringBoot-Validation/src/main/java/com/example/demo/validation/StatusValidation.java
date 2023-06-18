package com.example.demo.validation;

import com.example.demo.validator.StatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StatusValidator.class)
public @interface StatusValidation {
    String[] statusType() default {};

    String message() default "状态值错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
