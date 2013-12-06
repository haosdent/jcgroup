package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class Memory extends Common {

  public static final int subsystem = Constants.SUBSYS_MEMORY;

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
