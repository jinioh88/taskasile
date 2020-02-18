package com.taskagile.app.domain.application;

import com.taskagile.app.domain.command.RegistrationCommand;
import com.taskagile.app.domain.exception.RegistrationException;

public interface UserService {
  void register(RegistrationCommand command) throws RegistrationException;
}
