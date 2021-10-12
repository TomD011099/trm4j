package world.inetum.realdolmen.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositiveTrmDurationValidator.class)
public @interface PositiveTrmDuration {

    String message() default "Registered hours cannot be negative";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
