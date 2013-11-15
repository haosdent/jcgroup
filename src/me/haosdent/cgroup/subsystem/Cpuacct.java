package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;

public class Cpuacct extends Common {

  public Cpuacct(Group group) {
    super(group);
  }

  public void getUsage() {}

  public void resetUsage() {}

  public void getStat() {}

  public void getPerUsage() {}
}
