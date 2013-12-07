package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Memory extends Common {

  public static final int SUBSYS = Constants.SUBSYS_MEMORY;
  public static final String PROP_MEMORY_STAT = "memory.stat";
  public static final String PROP_MEMORY_USAGE_IN_BYTES = "memory.usage_in_bytes";
  public static final String PROP_MEMORY_MEMSW_USAGE_IN_BYTES = "memory.memsw.usage_in_bytes";
  public static final String PROP_MEMORY_MAX_USAGE_IN_BYTES = "memory.max_in_bytes";
  public static final String PROP_MEMORY_MEMSW_MAX_USAGE_IN_BYTES = "memory.memsw.usage_in_bytes";
  public static final String PROP_MEMORY_LIMIT_IN_BYTES = "memory.limit_in_bytes";
  public static final String PROP_MEMORY_MEMSW_LIMIT_IN_BYTES = "memory.memsw.limit_in_bytes";
  public static final String PROP_MEMORY_FAILCNT = "memory.failcnt";
  public static final String PROP_MEMORY_MEMSW_FAILCNT = "memory.memsw.failcnt";
  public static final String PROP_MEMORY_FORCE_EMPTY = "memory.force_empty";
  public static final String PROP_MEMORY_SWAPPINESS = "memory.swappiness";
  public static final String PROP_MEMORY_USE_HIERARCHY = "memory.use_hierarchy";
  public static final String PROP_MEMORY_OOM_CONTROL = "memory.oom_control";

  public Memory(Group group) {
    super(group);
  }

  public static class Stat {
    public final long cacheSize;
    public final long rssSize;
    public final long mappedFileSize;
    public final long pgpginNum;
    public final long pgpgoutNum;
    public final long swapSize;
    public final long activeAnonSize;
    public final long inactiveAnonSize;
    public final long activeFileSize;
    public final long inactiveFileSize;
    public final long unevictableSize;
    public final long hierarchicalMemoryLimitSize;
    public final long hierarchicalMemswLimitSize;

    public Stat(String statStr) {
      //TODO
      this.cacheSize = 0;
      this.rssSize = 0;
      this.mappedFileSize = 0;
      this.pgpginNum = 0;
      this.pgpgoutNum = 0;
      this.swapSize = 0;
      this.activeAnonSize = 0;
      this.inactiveAnonSize = 0;
      this.activeFileSize = 0;
      this.inactiveFileSize = 0;
      this.unevictableSize = 0;
      this.hierarchicalMemoryLimitSize = 0;
      this.hierarchicalMemswLimitSize = 0;
    }
  }

  public Stat getStat() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_STAT);
    Stat stat = new Stat(result);
    return stat;
  }

  public long getPhysicalUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public long getWithSwapUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MEMSW_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public long getMaxPhysicalUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MAX_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public long getMaxWithSwapUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MEMSW_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public void setPhysicalUsageLimit(long value) throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_LIMIT_IN_BYTES, value + "");
  }

  public void setWithSwapUsageLimit(long value) throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_MEMSW_LIMIT_IN_BYTES, value + "");
  }

  public int getPhysicalFailCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_FAILCNT);
    return Integer.parseInt(result);
  }

  public int getWithSwapFailCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MEMSW_FAILCNT);
    return Integer.parseInt(result);
  }

  public void clearForceEmpty() throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_FORCE_EMPTY, 0 + "");
  }

  public void setSwappiness(int value) throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_FORCE_EMPTY, value + "");
  }

  public void setUseHierarchy(boolean flag) throws IOException {
    int value = flag? 1 : 0;
    shell.cgset(group.getName(), PROP_MEMORY_USE_HIERARCHY, value + "");
  }

  public void setOomControl(boolean flag) throws IOException {
    int value = flag? 1 : 0;
    shell.cgset(group.getName(), PROP_MEMORY_OOM_CONTROL, value + "");
  }
}
