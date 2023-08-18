package com.myproject.partyverse.utils;

public class StringUtils {

  public static boolean isNullOrEmpty(String str){
    return str == null || str.isEmpty();
  }

  public static String printStackTrace(Exception e) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Exception: " + e.getMessage() + "\n\t");
    StackTraceElement[] var2 = e.getStackTrace();
    int var3 = var2.length;

    for(int var4 = 0; var4 < var3; ++var4) {
      StackTraceElement stmt = var2[var4];
      stringBuilder.append(stmt.toString()).append("\n\t");
    }

    return stringBuilder.toString();
  }
}
