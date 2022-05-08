package com.example.dz2.Dto;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = Isbn.IpAddressValidator.class)
@Documented
public @interface Username {
    String message() default "{username.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    class IpAddressValidator implements ConstraintValidator<Username, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value.matches("\\A\\p{ASCII}*\\z");
        }
    }
}
