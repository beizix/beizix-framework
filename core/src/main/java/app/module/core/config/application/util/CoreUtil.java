package app.module.core.config.application.util;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.uri.ancestry.application.domain.URIAncestry;
import app.module.core.config.adapter.web.rest.error.RestErrorVO;
import app.module.core.config.adapter.web.rest.error.RestFieldErrorVO;
import app.module.core.config.adapter.web.rest.error.RestObjectErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.thymeleaf.util.StringUtils;

@Component
@RequiredArgsConstructor
public class CoreUtil {
  private final MessageUtil messageUtil;
  /**
   * thymeleaf 에서 사용 목적. URI 목록 중 findId 와 매칭되는 URI 가 있는지 여부
   *
   * @param uriList URI 목록
   * @param findId 찾는 URI 아이디
   * @return boolean 매칭 여부
   */
  public boolean containsCurrentURI(List<URIAncestry> uriList, String findId) {
    return uriList.stream().anyMatch(node -> node.getId().equals(findId));
  }

  public String getUriTitle(URIAncestry uri) {
    return StringUtils.isEmpty(messageUtil.getMessage(uri.getId()))
        ? uri.getText()
        : messageUtil.getMessage(uri.getId());
  }

  /**
   * ObjectError 목록을 DTO 객체로 변환
   *
   * @param objectErrors ObjectError 목록
   * @return List<RestObjectErrorDto>
   */
  public List<RestObjectErrorVO> getRestObjectErrors(List<ObjectError> objectErrors) {
    return objectErrors.stream()
        .map(
            objectError ->
                RestObjectErrorVO.builder()
                    .message(
                        !StringUtils.isEmpty(objectError.getDefaultMessage())
                            ? objectError.getDefaultMessage()
                            : messageUtil.getMessage(
                                objectError.getCode(), objectError.getArguments()))
                    .build())
        .collect(Collectors.toList());
  }

  public ResponseEntity<?> getValidationFailResponseEntity(BindingResult bindingResult) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(
            RestErrorVO.builder()
                .objectErrors(getRestObjectErrors(bindingResult.getGlobalErrors()))
                .fieldErrors(getRestFieldErrors(bindingResult.getFieldErrors()))
                .build());
  }

  /**
   * bindResult 의 fieldErrors 정보를 DTO(RestFieldErrorDto) 목록으로 변환해서 전달
   *
   * @param fieldErrors bindResult 의 fieldErrors
   * @return List<RestFieldErrorDto>
   */
  public List<RestFieldErrorVO> getRestFieldErrors(List<FieldError> fieldErrors) {
    return fieldErrors.stream()
        .map(
            fieldError ->
                new RestFieldErrorVO(
                    fieldError.getField(),
                    !StringUtils.isEmpty(fieldError.getDefaultMessage())
                        ? fieldError.getDefaultMessage()
                        : messageUtil.getMessage(fieldError.getCode(), fieldError.getArguments())))
        .collect(Collectors.toList());
  }
}
