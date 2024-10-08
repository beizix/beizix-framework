package app.module.admin.usecase.privilege.view.adapters.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import app.module.core.config.application.converter.CustomLocalDateTimeSerializer;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeViewVO {
  private final String createdBy;

  @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private final LocalDateTime createdAt;

  private final String updatedBy;

  @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private final LocalDateTime updatedAt;

  private final String id;
  private final String description;
  private final Integer orderNo;
}
