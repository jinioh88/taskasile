package com.taskagile.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public final class JsonUtils {
  public static String toJson(Object object) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("Failed to convert object to Json String", e);

      return null;
    }
  }
}
