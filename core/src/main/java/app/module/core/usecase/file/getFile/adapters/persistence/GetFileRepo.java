package app.module.core.usecase.file.getFile.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetFileRepo extends JpaRepository<UploadFile, Long> {}
