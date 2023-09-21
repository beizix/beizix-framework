package org.beizix.admin.adapter.web.admin.validator;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.beizix.admin.adapter.web.admin.model.save.AdminSaveReqVO;

@Component
public class AdminUpdateValidator implements Validator {
  private final String PASSWORD_REGEX = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%\\-]).{6,15})";

  @Override
  public boolean supports(Class<?> clazz) {
    return AdminSaveReqVO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AdminSaveReqVO vo = (AdminSaveReqVO) target;

    Optional.ofNullable(vo.getRoleIds())
        .filter(ids -> ids.size() < 1)
        .ifPresent(
            ids ->
                errors.rejectValue(
                    "roleIds", "valid.common.required.size.min", new Object[] {1}, ""));

    Optional.ofNullable(vo.getUpdatePassword())
        .filter(s -> !s.trim().isEmpty() && !s.matches(PASSWORD_REGEX))
        .ifPresent(s -> errors.rejectValue("updatePassword", "valid.common.password.wrong"));
  }
}
