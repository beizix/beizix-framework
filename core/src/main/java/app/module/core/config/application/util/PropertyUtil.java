package app.module.core.config.application.util;

public class PropertyUtil {
  public static boolean isCoreDataRequired() {
    return System.getProperty("beizix.initData.core", "").equals("true");
  }
}
