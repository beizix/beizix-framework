package org.beizix.core.usecase.cache.refresh.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.beizix.core.config.adapter.web.rest.response.RestResponse;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RefreshCacheController {
  private final CacheManager cacheManager;

  @GetMapping("/refresh/cacheable/all")
  ResponseEntity<?> operate() {
    cacheManager
        .getCacheNames()
        .forEach(name -> Optional.ofNullable(cacheManager.getCache(name)).ifPresent(Cache::clear));

    return ResponseEntity.status(HttpStatus.OK)
        .body(RestResponse.builder().message("CORE CACHES ARE REFRESHED.").build());
  }
}
