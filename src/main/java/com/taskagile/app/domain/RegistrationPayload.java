package com.taskagile.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationPayload {
  @Size(min = 2, max = 50, message = "2~50자 로 입력")
  @NotNull
  private String userName;

  @Email(message = "필수 입력")
  @Size(max = 100, message = "100자 내외로 입력")
  @NotNull
  private String emailAddress;

  @Size(min = 6, max = 30, message = "6~30자로 비밀번호 입력")
  @NotNull
  private String password;

}
