package org.beizix.core.application.domain.fileupload;

import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;
import org.beizix.core.config.enums.AcceptableFileType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.config.enums.FileUploadType;
import org.beizix.core.config.exception.UnAcceptableFileException;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;
import org.beizix.core.application.port.in.fileupload.FileUploadPortIn;
import org.beizix.core.application.domain.fileupload.strategy.FileUploadStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileUploadService implements FileUploadPortIn {
  private final MessageUtil messageUtil;
  private final Tika tika;
  private final CommonUtil commonUtil;
  private final Set<FileUploadStrategy> fileUploadStrategies;

  @Override
  public Optional<FileUploadInfo> connect(
      FileUploadType fileUploadType, MultipartFile multipartFile) throws IOException {
    if (multipartFile == null || multipartFile.isEmpty()) {
      return Optional.empty();
    }

    validateSelf(fileUploadType, multipartFile);

    String originalFilename = Objects.requireNonNull(multipartFile.getOriginalFilename());
    String subPath = getDirPath(fileUploadType.getSubPath());
    String createFilename = getUUIDFilename(getFileExtension(originalFilename).orElse(null));

    getFileUploadStrategy(fileUploadType.getFileStorageType())
        .operate(multipartFile, fileUploadType.isPubic(), subPath, createFilename);

    return Optional.of(
        FileUploadInfo.builder()
            .type(fileUploadType)
            .path(subPath)
            .name(createFilename)
            .originName(originalFilename)
            .fileLength(multipartFile.getSize())
            .build());
  }

  private void validateSelf(FileUploadType fileUploadType, MultipartFile multipartFile)
      throws IOException {
    String originalFilename = Objects.requireNonNull(multipartFile).getOriginalFilename();

    // step.1 - 파일 확장자 여부 체크
    String extension =
        getFileExtension(originalFilename)
            .orElseThrow(
                () ->
                    new UnAcceptableFileException(
                        messageUtil.getMessage("exception.file.no.extension", originalFilename)));

    // step.2 - 파일 확장자 체크
    AcceptableFileType fileType =
        getFileTypeMatchingExtension(fileUploadType, extension)
            .orElseThrow(
                () ->
                    new UnAcceptableFileException(
                        String.format("'.%s' - 허용되지 않는 파일 확장자입니다.", extension)));

    // step.3 - 파일 mime-type 체크
    try (InputStream is = multipartFile.getInputStream()) {
      String fileMimeType = tika.detect(is);
      getMimeType(fileType, fileMimeType)
          .orElseThrow(
              () ->
                  new UnAcceptableFileException(
                      String.format(
                          "'%s' of .%s - 허용되지 않는 MIME Type 입니다.", fileMimeType, extension)));
    }
  }

  private Optional<String> getFileExtension(String filename) {
    return Optional.of(filename)
        .filter(f -> f.contains("."))
        .map(f -> f.substring(filename.lastIndexOf(".") + 1).toLowerCase());
  }

  private Optional<AcceptableFileType> getFileTypeMatchingExtension(
      FileUploadType fileUploadType, String extension) {
    return fileUploadType.getAcceptableFileTypes().stream()
        .filter(acceptableFileType -> acceptableFileType.getExtensions().contains(extension))
        .findAny();
  }

  private Optional<String> getMimeType(AcceptableFileType acceptableFileType, String fileMimeType) {
    return acceptableFileType.getMimeTypes().stream()
        .filter(mimeType -> mimeType.equals(fileMimeType))
        .findAny();
  }

  private String getDirPath(String path) {
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    return commonUtil.removeLastChar(path, "/") + "/" + now.format(formatter);
  }

  private String getUUIDFilename(String extension) {
    return UUID.randomUUID() + "." + extension;
  }

  private FileUploadStrategy getFileUploadStrategy(FileStorageType fileStorageType) {
    return fileUploadStrategies.stream()
        .filter(fileUploadStrategy -> fileUploadStrategy.getStorageType().equals(fileStorageType))
        .findFirst()
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    String.format("No file upload strategy found: %s", fileStorageType.name())));
  }
}
