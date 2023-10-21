package org.beizix.admin.adapter.web.role.model.list;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.converter.CustomLocalDateTimeSerializer;
import org.beizix.utility.enums.OperationType;

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
  
  private final String id;
  private final String description;
  private final Integer orderNo;
  private List<PrivilegeBindingVO> privileges;
}
