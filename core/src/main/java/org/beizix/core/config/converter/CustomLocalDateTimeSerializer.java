package org.beizix.core.config.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** RestController 응답시 LocalDateTime 필드 속성의 JSON 직렬화를 위해 선언됨. */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
  private static final DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

  @Override
  public void serialize(
      LocalDateTime localDateTime,
      JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeString(formatter.format(localDateTime));
  }
}
