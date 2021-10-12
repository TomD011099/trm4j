package world.inetum.realdolmen.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TrmHoursWorkedBelowPurchasedValidator.class)
public @interface TrmHoursWorkedBelowPurchased {

    String message() default "Registered hours cannot be more than purchased hours";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
