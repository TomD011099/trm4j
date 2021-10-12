package world.inetum.realdolmen.validation;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import world.inetum.realdolmen.project.Project;
import world.inetum.realdolmen.project.models.NewProjectModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTests {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @BeforeEach
    void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @Test
    void project_nullName_invalid() {
        Project p = new Project();
        p.setName(null);
        p.setPurchasedHours(Duration.ofHours(5));

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            if (c.getMessage().equals("must not be null")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void project_emptyName_invalid() {
        Project p = new Project();
        p.setName("  ");
        p.setPurchasedHours(Duration.ofHours(5));

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertEquals(1, violations.size());

        ConstraintViolationImpl constraintViolation = (ConstraintViolationImpl) violations.toArray()[0];
        assertEquals("must not be blank", constraintViolation.getMessage());
    }

    @Test
    void project_nullHours_invalid() {
        Project p = new Project();
        p.setName("Test");
        p.setPurchasedHours(null);

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            if (c.getMessage().equals("must not be null")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void project_negativeHours_invalid() {
        Project p = new Project();
        p.setName("Test");
        p.setPurchasedHours(Duration.ofHours(-5));

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            if (c.getMessage().equals("Registered hours cannot be negative")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void project_zeroHours_invalid() {
        Project p = new Project();
        p.setName("Test");
        p.setPurchasedHours(Duration.ZERO);

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            if (c.getMessage().equals("Registered hours cannot be negative")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void project_goodProject_valid() {
        Project p = new Project();
        p.setName("Test");
        p.setPurchasedHours(Duration.ofHours(5));

        Set<ConstraintViolation<Project>> violations = validator.validate(p);

        assertTrue(violations.isEmpty());
    }

    @Test
    void newProjectModel_nullName_invalid() {
        NewProjectModel p = new NewProjectModel();
        p.setName(null);
        p.setPurchasedHours(5);

        Set<ConstraintViolation<NewProjectModel>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            if (c.getMessage().equals("must not be null")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void newProjectModel_emptyName_invalid() {
        NewProjectModel p = new NewProjectModel();
        p.setName("  ");
        p.setPurchasedHours(5);

        Set<ConstraintViolation<NewProjectModel>> violations = validator.validate(p);

        assertEquals(1, violations.size());

        ConstraintViolationImpl constraintViolation = (ConstraintViolationImpl) violations.toArray()[0];
        assertEquals("must not be blank", constraintViolation.getMessage());
    }

    @Test
    void newProjectModel_nullHours_invalid() {
        NewProjectModel p = new NewProjectModel();
        p.setName("Test");
        p.setPurchasedHours(null);

        Set<ConstraintViolation<NewProjectModel>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            if (c.getMessage().equals("must not be null")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void newProjectModel_negativeHours_invalid() {
        NewProjectModel p = new NewProjectModel();
        p.setName("Test");
        p.setPurchasedHours(-5);

        Set<ConstraintViolation<NewProjectModel>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            System.out.println(c.getMessage());
            if (c.getMessage().equals("must be greater than 0")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void newProjectModel_zeroHours_invalid() {
        NewProjectModel p = new NewProjectModel();
        p.setName("Test");
        p.setPurchasedHours(0);

        Set<ConstraintViolation<NewProjectModel>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());

        boolean isFound = false;
        for (Object o : violations.toArray()) {
            ConstraintViolationImpl c = (ConstraintViolationImpl) o;
            if (c.getMessage().equals("must be greater than 0")) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }

    @Test
    void newProjectModel_goodNewProjectModel_valid() {
        NewProjectModel p = new NewProjectModel();
        p.setName("Test");
        p.setPurchasedHours(5);

        Set<ConstraintViolation<NewProjectModel>> violations = validator.validate(p);

        assertTrue(violations.isEmpty());
    }
}
