package world.inetum.realdolmen.validation;

import world.inetum.realdolmen.timeRegistration.models.NewTimeRegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveTrmDurationValidator implements ConstraintValidator<PositiveTrmDuration, NewTimeRegistrationModel> {
    @Override
    public boolean isValid(NewTimeRegistrationModel newTimeRegistrationModel, ConstraintValidatorContext constraintValidatorContext) {
        return newTimeRegistrationModel.getStartTime().isBefore(newTimeRegistrationModel.getEndTime());
    }
}
