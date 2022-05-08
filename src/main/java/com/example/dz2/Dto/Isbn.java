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
public @interface Isbn {

    String message() default "{isbn.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    class IpAddressValidator implements ConstraintValidator<Isbn, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            Pattern pattern =
                    Pattern.compile("^(?:ISBN? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$");
            Matcher matcher = pattern.matcher(value);
            if(!matcher.matches())
                return false;
            else{
                int sum = 0;
                for (int i = 0; i < 9; i++)
                {
                    int digit = value.charAt(i) - '0';
                    if (0 > digit || 9 < digit)
                        continue;
                    sum += (digit * (10 - i));
                }

                char last = value.charAt(value.length()-1);
                if (last != 'X' && (last < '0' ||
                        last > '9'))
                    return false;

                sum += ((last == 'X') ? 10 : (last - '0'));

                return (sum % 11 == 0);
            }
        }
    }

}
