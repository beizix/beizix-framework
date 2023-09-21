package org.beizix.admin.adapter.web.admin.validator;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.beizix.admin.adapter.web.admin.model.save.AdminSaveReqVO;

@Component
public class AdminCreateValidator implements Validator {
  private final String PASSWORD_REGEX = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%_\\-]).{6,15})";

  @Override
  public boolean supports(Class<?> clazz) {
    return AdminSaveReqVO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AdminSaveReqVO vo = (AdminSaveReqVO) target;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.common.required");

    Optional.ofNullable(vo.getPassword())
        .filter(password -> !password.matches(PASSWORD_REGEX))
        .ifPresent(password -> errors.rejectValue("password", "valid.common.password.wrong"));

    Optional.ofNullable(vo.getRoleIds())
        .filter(ids -> ids.size() < 1)
        .ifPresent(
            ids ->
                errors.rejectValue(
                    "roleIds", "valid.common.required.size.min", new Object[] {1}, ""));
  }
}
