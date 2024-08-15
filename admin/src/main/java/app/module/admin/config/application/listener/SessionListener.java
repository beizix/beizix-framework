package app.module.admin.config.application.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Spring Boot 을 이용하지만 내장 Tomcat 이 아닌 외부 WAS 를 이용하는 경우 HttpSessionListener 를 이용해 Session 유효기간을 지정해야
 * 한다.
 */
@Component
public class SessionListener implements HttpSessionListener {
  @Value("${app.admin.session.validity.period.seconds}")
  Integer sessionValiditySeconds;

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    se.getSession().setMaxInactiveInterval(sessionValiditySeconds);
  }
}
