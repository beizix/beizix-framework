package org.beizix.front.config.interceptor;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CurrentDeviceInterceptor implements HandlerInterceptor {
  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) {
    Device currentDevice = DeviceUtils.getCurrentDevice(request);
    if (modelAndView != null) {
      modelAndView.addObject("currentDevice", currentDevice);
    }
  }
}
