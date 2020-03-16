package com.taskagile.app.domain.application.impl;

import com.taskagile.app.domain.application.UserService;
import com.taskagile.app.domain.command.RegistrationCommand;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.common.mail.MailManager;
import com.taskagile.app.domain.common.mail.MessageVariable;
import com.taskagile.app.domain.exception.RegistrationException;
import com.taskagile.app.domain.model.user.RegistrationManagement;
import com.taskagile.app.domain.model.user.User;
import com.taskagile.app.domain.model.user.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final RegistrationManagement registrationManagement;
  private final DomainEventPublisher domainEventPublisher;
  private final MailManager mailManager;

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    User newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getPassword());

    sendWelcomeMessage(newUser);
    domainEventPublisher.publish(new UserRegisteredEvent(newUser));
  }

  private void sendWelcomeMessage(User user) {
    mailManager.send(
      user.getEmailAddress(),
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", user)
    );
  }
}
