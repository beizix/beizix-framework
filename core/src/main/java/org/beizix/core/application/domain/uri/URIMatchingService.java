package org.beizix.core.application.domain.uri;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.port.in.uri.URIListPortIn;
import org.beizix.core.application.port.in.uri.URIMatchingPortIn;
import org.beizix.utility.common.CommonUtil;

@Service
@RequiredArgsConstructor
@Slf4j
class URIMatchingService implements URIMatchingPortIn {
  private final URIListPortIn uriListPortIn;
  private final CommonUtil commonUtil;

  @Override
  public URIViewOutput connect(AppType appType, String uri) {
    String targetUri = commonUtil.removeLastChar(uri, "/");
    return uriListPortIn.connect(appType).stream()
        .filter(
            uriItem -> {
              String comparePath = commonUtil.removeLastChar(uriItem.getUri(), "/");
              if (comparePath.equals(targetUri)) return true;

              // {{pathVar}} 을 가진 URI 라면 배열검증 수행
              if (comparePath.endsWith("/{{pathVar}}")) {
                List<String> pathComponents =
                    Arrays.stream(comparePath.split("/")).collect(Collectors.toList());
                List<String> targetComponents =
                    Arrays.stream(targetUri.split("/")).collect(Collectors.toList());

                if (pathComponents.size() != targetComponents.size()) return false;

                boolean matchResult = true;
                for (int i = 0; i < pathComponents.size(); i++) {
                  String pathComponent = pathComponents.get(i);
                  if (pathComponent.equals(PATH_VAR)) continue;

                  String targetComponent = targetComponents.get(i);

                  matchResult = pathComponent.equals(targetComponent);
                  if (!matchResult) break;
                }
                return matchResult;
              }

              return false;
            })
        .findFirst()
        .orElse(null);
  }
}
