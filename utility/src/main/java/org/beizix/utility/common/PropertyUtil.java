package org.beizix.utility.common;

public class PropertyUtil {
  public static boolean isAdminSingleRole() {
    return System.getProperty("recycle.admin.singleRole", "").equals("true");
  }

  public static boolean isCoreDataRequired() {
    return System.getProperty("recycle.initData.core", "").equals("true");
  }
}
