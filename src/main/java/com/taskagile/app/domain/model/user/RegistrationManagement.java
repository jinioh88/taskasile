package com.taskagile.app.domain.model.user;

import com.taskagile.app.domain.common.security.PasswordEncryptor;
import com.taskagile.app.domain.exception.EmailAddressExistsException;
import com.taskagile.app.domain.exception.RegistrationException;
import com.taskagile.app.domain.exception.UsernameExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationManagement {
  private final UserRepository repository;
  private PasswordEncryptor passwordEncryptor;

  public User register(String userName, String emailAddress, String password) throws RegistrationException {
    User existingUser = repository.findByUsername(userName);
    if (existingUser != null) {
      throw new UsernameExistsException();
    }

    existingUser = repository.findByEmailAddress(emailAddress.toLowerCase());
    if (existingUser != null) {
      throw new EmailAddressExistsException();
    }

    String encryptedPassword = passwordEncryptor.encrypt(password);
    User newUser = User.create(userName, emailAddress.toLowerCase(), encryptedPassword);
    repository.save(newUser);

    return newUser;
  }
}
