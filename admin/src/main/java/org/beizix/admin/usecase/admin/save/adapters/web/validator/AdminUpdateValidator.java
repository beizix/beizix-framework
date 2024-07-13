package org.beizix.admin.usecase.admin.save.adapters.web.validator;

import java.util.Optional;
import org.beizix.admin.usecase.admin.save.adapters.web.model.AdminUpdateBindingVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdminUpdateValidator implements Validator {
  private final String PASSWORD_REGEX = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%\\-]).{6,15})";

  @Override
  public boolean supports(Class<?> clazz) {
    return AdminUpdateBindingVO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AdminUpdateBindingVO vo = (AdminUpdateBindingVO) target;

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
