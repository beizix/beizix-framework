package app.module.admin.usecase.article.createArticle.ports.application.domain;

import app.module.core.config.adapter.persistence.entity.UploadFile;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateArticle {
  private Long id;
  Integer orderNo;
}
