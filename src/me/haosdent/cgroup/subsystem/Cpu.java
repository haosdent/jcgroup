package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class Cpu extends Common {

  public static final int subsystem = Constants.SUBSYS_CPU;

  public Cpu(Group group) {
    super(group);
  }

  public void setShares() {}

  public void setCfsPeriodTime() {}

  public void setCfsQuotaTime() {}

  public void setRtPeriodTime() {}

  public void setRtRuntimeTime() {}

  public void getStat() {}
}
