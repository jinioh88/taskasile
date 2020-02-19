package com.taskagile.app.domin;

import com.taskagile.app.domain.application.impl.UserServiceImpl;
import com.taskagile.app.domain.command.RegistrationCommand;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.common.mail.MailManager;
import com.taskagile.app.domain.common.mail.MessageVariable;
import com.taskagile.app.domain.exception.EmailAddressExistsException;
import com.taskagile.app.domain.exception.RegistrationException;
import com.taskagile.app.domain.model.user.RegistrationManagement;
import com.taskagile.app.domain.model.user.User;
import com.taskagile.app.domain.model.user.event.UserRegisteredEvent;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
  private RegistrationManagement registrationManagementMock;
  private DomainEventPublisher domainEventPublisherMock;
  private MailManager mailManagerMock;
  private UserServiceImpl instance;

  @Before
  public void setUp() {
    registrationManagementMock = mock(RegistrationManagement.class);
    domainEventPublisherMock = mock(DomainEventPublisher.class);
    mailManagerMock = mock(MailManager.class);
    instance = new UserServiceImpl(registrationManagementMock, domainEventPublisherMock, mailManagerMock);
  }

  @Test(expected = IllegalArgumentException.class)
  public void register_nullCommand_shouldFail() throws RegistrationException {
    instance.register(null);
  }

  @Test(expected = RegistrationException.class)
  public void register_existingEmailAddress_shouldFail() throws RegistrationException {
    String username = "sunny";
    String emailAddress = "existing@taskagile.com";
    String password = "MyPassword!";
    doThrow(EmailAddressExistsException.class).when(registrationManagementMock)
      .register(username, emailAddress, password);

    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
    instance.register(command);
  }

  @Test
  public void register_validCommand_shouldSucceed() throws RegistrationException {
    String username = "sunny";
    String emailAddress = "sunny@taskagile.com";
    String password = "MyPassword!";
    User newUser = User.create(username, emailAddress, password);
    when(registrationManagementMock.register(username, emailAddress, password))
      .thenReturn(newUser);
    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

    instance.register(command);

    verify(mailManagerMock).send(
      emailAddress,
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", newUser)
    );
    verify(domainEventPublisherMock).publish(new UserRegisteredEvent(newUser));
  }
}
