package com.taskagile.app.domain.common.security;

public interface PasswordEncryptor {
  String encrypt(String rawPassword);
}
