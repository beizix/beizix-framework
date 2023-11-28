package org.beizix.core.usecase.uri.currentmatch.application.port.in;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.usecase.uri.currentmatch.application.domain.URICurrentMatching;
import org.beizix.core.usecase.uri.list.application.port.in.URIListPortIn;
import org.beizix.core.config.application.util.CommonUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class URICurrentMatchingService implements URICurrentMatchingPortIn {

  private final URIListPortIn uriListPortIn;
  private final CommonUtil commonUtil;

  @Override
  public URICurrentMatching connect(AppType appType, String uri) {
    String targetUri = commonUtil.removeLastChar(uri, "/");
    return uriListPortIn.connect(appType).stream()
        .filter(
            uriDetail -> {
              String comparePath = commonUtil.removeLastChar(uriDetail.getUri(), "/");
              if (comparePath.equals(targetUri)) {
                return true;
              }

              // {{pathVar}} 을 가진 URI 라면 배열검증 수행
              if (comparePath.endsWith("/{{pathVar}}")) {
                List<String> pathComponents =
                    Arrays.stream(comparePath.split("/")).collect(Collectors.toList());
                List<String> targetComponents =
                    Arrays.stream(targetUri.split("/")).collect(Collectors.toList());

                if (pathComponents.size() != targetComponents.size()) {
                  return false;
                }

                boolean matchResult = true;
                for (int i = 0; i < pathComponents.size(); i++) {
                  String pathComponent = pathComponents.get(i);
                  if (pathComponent.equals(PATH_VAR)) {
                    continue;
                  }

                  String targetComponent = targetComponents.get(i);

                  matchResult = pathComponent.equals(targetComponent);
                  if (!matchResult) {
                    break;
                  }
                }
                return matchResult;
              }

              return false;
            })
        .findFirst()
        .map(uriDetail -> new URICurrentMatching(uriDetail.getId(), uriDetail.getAppType(),
            uriDetail.getParentId(), uriDetail.getOrderNo(), uriDetail.getText(),
            uriDetail.getUri(), uriDetail.getShowOnNavi(), uriDetail.getOgTitle(),
            uriDetail.getOgDesc(), uriDetail.getOgKeywords(), uriDetail.getOgImage(),
            uriDetail.getRoles()))
        .orElse(null);
  }
}
