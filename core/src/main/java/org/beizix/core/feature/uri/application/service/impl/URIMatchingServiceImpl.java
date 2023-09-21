package org.beizix.core.feature.uri.application.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URIListService;
import org.beizix.core.feature.uri.application.service.URIMatchingService;
import org.beizix.utility.common.CommonUtil;

@Service
@RequiredArgsConstructor
@Slf4j
class URIMatchingServiceImpl implements URIMatchingService {
  private final URIListService uriListService;
  private final CommonUtil commonUtil;

  @Override
  public URI operate(AppType appType, String uri) {
    String targetUri = commonUtil.removeLastChar(uri, "/");
    return uriListService.operate(appType).stream()
        .filter(
            item -> {
              String itemUri = commonUtil.removeLastChar(item.getUri(), "/");
              if (itemUri.equals(targetUri)) return true;

              // {{pathVars}} 을 가진 URI 라면 배열검증 수행
              if (itemUri.endsWith("/{{pathVars}}")) {
                List<String> itemValues =
                    Arrays.stream(itemUri.split("/")).collect(Collectors.toList());
                List<String> targetValues =
                    Arrays.stream(targetUri.split("/")).collect(Collectors.toList());

                if (itemValues.size() != targetValues.size()) return false;

                boolean matchResult = true;
                for (int i = 0; i < itemValues.size(); i++) {
                  String itemVal = itemValues.get(i);
                  if (itemVal.equals(pathVariable)) continue;

                  String targetVal = targetValues.get(i);

                  matchResult = itemVal.equals(targetVal);
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
