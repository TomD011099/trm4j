package world.inetum.realdolmen.validation;

import world.inetum.realdolmen.project.ProjectRepository;
import world.inetum.realdolmen.timeRegistration.models.NewTimeRegistrationModel;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

public class TrmHoursWorkedBelowPurchasedValidator implements ConstraintValidator<TrmHoursWorkedBelowPurchased, NewTimeRegistrationModel> {

    @EJB
    ProjectRepository projectRepository;

    @Override
    public boolean isValid(NewTimeRegistrationModel newTimeRegistrationModel, ConstraintValidatorContext constraintValidatorContext) {
        Duration remaining = projectRepository.getRemainingTimeById(newTimeRegistrationModel.getProjectId());
        Duration requested = Duration.between(newTimeRegistrationModel.getStartTime(), newTimeRegistrationModel.getEndTime());

        return remaining.compareTo(requested) >= 0;
    }
}
