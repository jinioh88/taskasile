package com.taskagile.app.web;

import com.taskagile.app.domain.RegistrationPayload;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RegistrationPayLoadTest {
  private Validator validator;

  @Before
  public void setpu() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void validate_blankPayload_shouldFail() {
    RegistrationPayload payLoad = new RegistrationPayload();
    Set<ConstraintViolation<RegistrationPayload>> validations = validator.validate(payLoad);

    assertEquals(3, validations.size());
  }

  @Test
  public void validate_payloadWithInvalidEmail_shouldFail() {
    RegistrationPayload payLoad = new RegistrationPayload();
    payLoad.setEmailAddress("badEmail");
    payLoad.setUserName("OhSejin");
    payLoad.setPassword("1234556");

    Set<ConstraintViolation<RegistrationPayload>> validations = validator.validate(payLoad);

    assertEquals(1, validations.size());
  }
}
