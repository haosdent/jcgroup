package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

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

  public void getStat() {}

  public void getPhysicalUsage() {}

  public void getWithSwapUsage() {}

  public void getMaxPhysicalUsage() {}

  public void getMaxWithSwapUsage() {}

  public void setPhysicalUsageLimit() {}

  public void setWithSwapUsageLimit() {}

  public void getPhysicalFailCount() {}

  public void getWithSwapFailCount() {}

  public void setForceEmpty() {}

  public void setSwappiness() {}

  public void setUseHierarchy() {}

  public void setOomControl() {}
}
