package world.inetum.realdolmen.validation;

import world.inetum.realdolmen.project.Project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HoursWorkedBelowPurchasedValidator implements ConstraintValidator<HoursWorkedBelowPurchased, Project> {
    @Override
    public boolean isValid(Project project, ConstraintValidatorContext constraintValidatorContext) {
        if (project.getPurchasedHours() != null) {
            return project.getPurchasedHours().compareTo(project.getWorkedTime()) >= 0;
        }
        //To allow for optionality --> add @NotNull for null checks
        return true;

    }
}
