package org.beizix.utility.common;

public class PropertyUtil {
  public static boolean isAdminSingleRole() {
    return System.getProperty("beizix.admin.singleRole", "").equals("true");
  }

  public static boolean isCoreDataRequired() {
    return System.getProperty("beizix.initData.core", "").equals("true");
  }
}
