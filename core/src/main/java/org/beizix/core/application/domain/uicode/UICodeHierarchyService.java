package org.beizix.core.application.domain.uicode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.application.port.in.uicode.UICodeHierarchyPortIn;
import org.beizix.core.application.domain.uicode.model.UICodeInput;
import org.beizix.core.application.port.out.uicode.UICodeHierarchyPortOut;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
class UICodeHierarchyService implements UICodeHierarchyPortIn {
  private final UICodeHierarchyPortOut uiCodeHierarchyPortOut;

  @Transactional
  @Cacheable("UICodeHierarchyCache")
  @Override
  public UICodeInput operate() {
    UICodeInput topNode = uiCodeHierarchyPortOut.connect();
    scanningNodes(topNode, 1);
    return topNode;
  }

  private void scanningNodes(UICodeInput node, int startDepth) {
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

      for (UICodeInput subMenu : node.getNodes()) {
        scanningNodes(subMenu, startDepth);
      }
    }
  }
}
