package app.module.admin.config.application.util;

import java.util.List;
import app.module.core.config.adapter.web.interceptor.model.URITopTierVO;
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
