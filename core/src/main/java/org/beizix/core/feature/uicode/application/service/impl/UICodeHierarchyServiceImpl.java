package org.beizix.core.feature.uicode.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.uicode.application.service.UICodeHierarchyService;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.persistence.dao.UICodeHierarchyDao;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
class UICodeHierarchyServiceImpl implements UICodeHierarchyService {
  private final UICodeHierarchyDao uiCodeHierarchyDao;

  @Transactional
  @Cacheable("UICodeHierarchyCache")
  @Override
  public UICode operate() {
    UICode topNode = uiCodeHierarchyDao.operate();
    scanningNodes(topNode, 1);
    return topNode;
  }

  private void scanningNodes(UICode node, int startDepth) {
    node.setDepth(startDepth++);

//    log.info(
//        String.format(
//            "name: %s (depth: %s), uri: %s", node.getId(), node.getDepth(), node.getCodeValue()));

    if (!node.getNodes().isEmpty()) {

      // orderNo 오름차순 정렬
      node.getNodes()
          .sort(
              (item1, item2) -> {
                if (item1.getOrderNo() > item2.getOrderNo()) {
                  return 1;
                } else if (item1.getOrderNo() < item2.getOrderNo()) {
                  return -1;
                }
                return 0;
              });

      for (UICode subMenu : node.getNodes()) {
        scanningNodes(subMenu, startDepth);
      }
    }
  }
}
