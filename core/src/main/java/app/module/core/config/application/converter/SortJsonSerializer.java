package app.module.core.config.application.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Sort;

import java.io.IOException;

/**
 * Sort 객체의 JSON 직렬화시 유용한 정보를 담기위해 선언.
 */
@JsonComponent
public class SortJsonSerializer extends JsonSerializer<Sort> {

  @Override
  public void serialize(Sort value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException, IOException {
    gen.writeStartArray();

    value
        .iterator()
        .forEachRemaining(
            v -> {
              try {
                gen.writeObject(v);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });

    gen.writeEndArray();
  }

  @Override
  public Class<Sort> handledType() {
    return Sort.class;
  }
}
