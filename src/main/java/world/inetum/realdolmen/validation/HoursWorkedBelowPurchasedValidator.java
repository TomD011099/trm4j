package world.inetum.realdolmen.validation;

import world.inetum.realdolmen.project.Project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HoursWorkedBelowPurchasedValidator implements ConstraintValidator<HoursWorkedBelowPurchased, Project> {
    @Override
    public boolean isValid(Project project, ConstraintValidatorContext constraintValidatorContext) {
        return project.getPurchasedHours().compareTo(project.getWorkedTime()) >= 0;
    }
}
