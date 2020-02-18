package com.taskagile.app.domain.common.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageVariable {
  private String key;
  private Object value;

  public static MessageVariable from(String key, Object value) {
    return new MessageVariable(key, value);
  }
}
