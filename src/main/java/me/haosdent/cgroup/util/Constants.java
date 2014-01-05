package me.haosdent.cgroup.util;

public class Constants {

  public static final String PREFIX_CGROUP_DIR = "/cgroup/%s";

  public static final String SHELL_MKDIR = "mkdir -p %s";
  public static final String SHELL_RMDIR = "rm -rf %s";
  public static final String SHELL_MOUNT = "mount -t cgroup -o%s %s %s";
  public static final String SHELL_UMOUNT = "umount %s";

  public static final String SHELL_CG_CLEAR = "cgclear";
  public static final String SHELL_CG_CREATE = "cgcreate -g %s:%s";
  public static final String SHELL_CG_DELETE = "cgdelete %s:%s";
  public static final String SHELL_CG_SET = "cgset -r %s=%s %s";
  public static final String SHELL_CG_GET = "cgget -r %s %s";
  public static final String SHELL_CG_CLASSIFY = "cgclassify -g %s:%s %s";
  public static final String SHELL_CG_EXEC = "cgexec -g %s:%s %s %s";

  public static final int SUBSYS_COMMON = 0;
  public static final int SUBSYS_BLKIO = 1;
  public static final int SUBSYS_CPU = 2;
  public static final int SUBSYS_CPUACCT = 4;
  public static final int SUBSYS_CPUSET = 8;
  public static final int SUBSYS_DEVICE = 16;
  public static final int SUBSYS_FREEZER = 32;
  public static final int SUBSYS_MEMORY = 64;
  public static final int SUBSYS_NET_CLS = 128;
  public static final int SUBSYS_NET_PRIO = 256;

  public static final String SUBSYS_BLKIO_STR = "blkio";
  public static final String SUBSYS_CPU_STR = "cpu";
  public static final String SUBSYS_CPUACCT_STR = "cpuacct";
  public static final String SUBSYS_CPUSET_STR = "cpuset";
  public static final String SUBSYS_DEVICE_STR = "device";
  public static final String SUBSYS_FREEZER_STR = "freezer";
  public static final String SUBSYS_MEMORY_STR = "memory";
  public static final String SUBSYS_NET_CLS_STR = "net_cls";
  public static final String SUBSYS_NET_PRIO_STR = "net_prio";
}
