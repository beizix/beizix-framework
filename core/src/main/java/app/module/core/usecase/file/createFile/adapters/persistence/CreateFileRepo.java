package app.module.core.usecase.file.createFile.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateFileRepo extends JpaRepository<UploadFile, Long> {}
