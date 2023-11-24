package org.beizix.core.usecase.file.upload.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.utility.common.CommonUtil;
import org.beizix.core.config.enums.FileStorageType;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalStorageFileUploadStrategy implements FileUploadStrategy {
  @Value("${path.upload.private}")
  private String privatePath;

  @Value("${path.upload.public}")
  private String publicPath;

  @Value("${path.upload.tmpdir}")
  private String tmpPath;

  private final CommonUtil commonUtil;

  @PostConstruct
  public void initialize() throws Exception {
    log.info(String.format("FileIoService - initialize : path.upload.private is %s", privatePath));
    log.info(String.format("FileIoService - initialize : path.upload.public is %s", publicPath));
    log.info(String.format("FileIoService - initialize : path.upload.tmpdir is %s", tmpPath));

    try {
      Files.createDirectories(Paths.get(privatePath));
      Files.createDirectories(Paths.get(publicPath));
      Files.createDirectories(Paths.get(tmpPath));
    } catch (IOException e) {
      throw new IOException("Could not initialize storage", e);
    }
  }

  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.LOCAL;
  }

  @Override
  public void operate(
      MultipartFile multipartFile, Boolean isPublic, String createSubPath, String createFilename)
      throws IOException {
    if (multipartFile == null || multipartFile.isEmpty()) {
      throw new RuntimeException("Failed to store empty file.");
    }

    String targetPath = isPublic ? publicPath : privatePath;
    Path path = Paths.get(commonUtil.removeLastChar(targetPath, "/"), createSubPath);
    Files.createDirectories(path);

    Path destinationFile = (path.resolve(Paths.get(createFilename)).normalize().toAbsolutePath());

    if (!destinationFile.getParent().equals(path.toAbsolutePath())) {
      // This is a security check
      throw new RuntimeException("Cannot store file outside current directory.");
    }

    try (InputStream inputStream = multipartFile.getInputStream()) {
      Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
      destinationFile.toFile().setWritable(true, false);
      destinationFile.toFile().setReadable(true, false);
    }
  }
}
