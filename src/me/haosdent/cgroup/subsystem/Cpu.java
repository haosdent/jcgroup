package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;

public class Cpu extends Common {

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
