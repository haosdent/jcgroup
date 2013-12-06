package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class Cpuacct extends Common {

  public static final int subsystem = Constants.SUBSYS_CPUACCT;

  public Cpuacct(Group group) {
    super(group);
  }

  public void getUsage() {}

  public void resetUsage() {}

  public void getStat() {}

  public void getPerUsage() {}
}
