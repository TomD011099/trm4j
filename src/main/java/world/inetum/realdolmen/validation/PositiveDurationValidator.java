package world.inetum.realdolmen.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;


public class PositiveDurationValidator implements ConstraintValidator<PositiveDuration, Duration> {
    @Override
    public boolean isValid(Duration duration, ConstraintValidatorContext constraintValidatorContext) {
        if (duration != null) {
            return !duration.isNegative();
        }
        //To allow for optionality --> add @NotNull for null checks
        return true;
    }
}
