package me.haosdent.cgroup.util;

public class Threads {

  public static native int getThreadId();

  static {
    System.loadLibrary("Threads");
  }
}
