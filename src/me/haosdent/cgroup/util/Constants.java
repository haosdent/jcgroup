package me.haosdent.cgroup.util;

public class Constants {

  public static final String SHELL_MOUNT = "mount -t cgroup -o %s %s %s";
  public static final String SHELL_UMOUNT = "umount %s";

  public static final String SHELL_CG_CLEAR = "cgclear";
  public static final String SHELL_CG_CREATE = "cgcreate -g %s:%s";
  public static final String SHELL_CG_DELETE = "cgdelete %s:%s";
  public static final String SHELL_CG_SET = "cgset -r %s=%s %s";
  public static final String SHELL_CG_CLASSIFY = "cgclassify -g %s:%s %s";
  public static final String SHELL_CG_EXEC = "cgexec -g %s:%s %s %s";

  public static final int SUBSYS_BLKIO = 0;
  public static final int SUBSYS_CPU = 1;
  public static final int SUBSYS_CPUACCT = 2;
  public static final int SUBSYS_CPUSET = 3;
  public static final int SUBSYS_DEVICE = 4;
  public static final int SUBSYS_FREEZER = 5;
  public static final int SUBSYS_MEMORY = 6;
  public static final int SUBSYS_NET_CLS = 7;
  public static final int SUBSYS_NET_PRIO = 8;
}
