package app.module.core.config.adapter.web.rest.error;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestObjectErrorVO {
  private String message;
}
