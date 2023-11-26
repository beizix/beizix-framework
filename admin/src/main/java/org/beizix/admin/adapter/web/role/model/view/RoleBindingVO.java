package org.beizix.admin.adapter.web.role.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.configuration.application.converter.CustomLocalDateTimeSerializer;

@Getter
@Setter
@AllArgsConstructor
public class RoleBindingVO {
  private final String createdBy;

  @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private final LocalDateTime createdAt;

  private final String updatedBy;

  @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private final LocalDateTime updatedAt;

  private String id;
  private String description;
  private Integer orderNo;
  private List<String> privilegeIds;
}
