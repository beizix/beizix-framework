package org.beizix.admin.adapter.web.admin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.beizix.admin.adapter.web.admin.model.query.AdminListReqParam;

@Component
public class AdminRemoveValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return AdminListReqParam.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AdminListReqParam dto = (AdminListReqParam) target;

    if (dto.getCheckedIds() == null) {
      errors.reject("valid.common.required.remove.item");
    }
  }
}
