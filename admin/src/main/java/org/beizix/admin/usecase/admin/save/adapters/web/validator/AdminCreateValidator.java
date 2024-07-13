package org.beizix.admin.usecase.admin.save.adapters.web.validator;

import java.util.Optional;
import org.beizix.admin.usecase.admin.save.adapters.web.model.AdminCreateBindingVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AdminCreateValidator implements Validator {
  private final String PASSWORD_REGEX = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%_\\-]).{6,15})";

  @Override
  public boolean supports(Class<?> clazz) {
    return AdminCreateBindingVO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AdminCreateBindingVO vo = (AdminCreateBindingVO) target;

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
