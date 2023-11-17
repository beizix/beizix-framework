package org.beizix.admin.config.util;

import java.util.List;
import org.beizix.admin.config.interceptor.model.URITopTierVO;
import org.springframework.stereotype.Component;

@Component
public class AdminUtil {
  public boolean shouldDisplaySubNodes(List<URITopTierVO> uriList) {
    boolean result = false;
    if (uriList != null && !uriList.isEmpty()) {
      for (URITopTierVO uri : uriList) {
        if (uri.getShowOnNavi()) {
          result = true;
          break;
        }
      }
    }
    return result;
  }
}
