package world.inetum.realdolmen.validation;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import world.inetum.realdolmen.project.Project;
import world.inetum.realdolmen.timeRegistration.TimeRegistration;
import world.inetum.realdolmen.validation.HoursWorkedBelowPurchasedValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HoursWorkedBelowPurchasedTests {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    private Project p;

    private TimeRegistration t1;
    private TimeRegistration t2;
    private TimeRegistration t3;

    @BeforeEach
    void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        p = new Project(1L, "test", Duration.ofHours(20L));

        t1 = new TimeRegistration();
        t1.setId(1L);
        t1.setDay(LocalDate.of(2021, 10, 8));
        t1.setProject(p);
        t1.setDuration(Duration.ofHours(16L));

        t2 = new TimeRegistration();
        t2.setId(1L);
        t2.setDay(LocalDate.of(2021, 10, 8));
        t2.setProject(p);
        t2.setDuration(Duration.ofHours(4L));

        t3 = new TimeRegistration();
        t3.setId(1L);
        t3.setDay(LocalDate.of(2021, 10, 8));
        t3.setProject(p);
        t3.setDuration(Duration.ofHours(22L));
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @Test
    void hoursWorkedBelowPurchased_tooMuch_invalidResult() {
        p.setTimeRegistrations(Arrays.asList(t1, t2, t3));

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        //TODO assert the violation is correct
        ConstraintViolationImpl constraintViolation = (ConstraintViolationImpl) violations.toArray()[0];
        assertEquals("Registered hours cannot be more than purchased hours", constraintViolation.getMessage());
    }

    @Test
    void hoursWorkedBelowPurchased_stillAvailable_validResult() {
        p.setTimeRegistrations(Arrays.asList(t2));

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertTrue(violations.isEmpty());
    }

    @Test
    void hoursWorkedBelowPurchased_nothingLogged_validResult() {
        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertTrue(violations.isEmpty());
    }

    @Test
    void hoursWorkedBelowPurchased_exactHours_validResult() {
        p.setTimeRegistrations(Arrays.asList(t1, t2));

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertTrue(violations.isEmpty());
    }
}
