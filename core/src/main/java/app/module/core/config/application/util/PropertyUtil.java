package app.module.core.config.application.util;

public class PropertyUtil {
  public static boolean isCoreDataRequired() {
    return System.getProperty("app.initData.core", "").equals("true");
  }
}
