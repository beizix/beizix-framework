package app.module.admin.usecase.uicode.save.application.port.in;

import app.module.admin.usecase.uicode.save.application.port.out.UICodeMaxOrderNoPortOut;
import app.module.admin.usecase.uicode.save.application.port.out.UICodeSavePortOut;
import app.module.admin.usecase.uicode.view.application.port.in.UICodeViewPortIn;
import lombok.RequiredArgsConstructor;
import app.module.core.config.application.exception.AlreadyExistItemException;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UICodeSaveService implements UICodeSavePortIn {
  private final UICodeSavePortOut createUpdateDao;
  private final UICodeMaxOrderNoPortOut maxOrderNoDao;
  private final UICodeViewPortIn uiCodeViewPortIn;
  private final MessageUtil messageUtil;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public String connect(UICodeSaveCommand createCommand, boolean isUpdate) {
    if (isUpdate)
      uiCodeViewPortIn
          .connect(createCommand.getId())
          .ifPresent(
              item -> {
                throw new AlreadyExistItemException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });

    if (createCommand.getOrderNo() == null)
      createCommand.setOrderNo(maxOrderNoDao.connect(createCommand.getParentId()).orElse(-1) + 1);

    return createUpdateDao.connect(createCommand);
  }
}
