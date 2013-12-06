package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class Device extends Common {

  public static final int subsystem = Constants.SUBSYS_DEVICE;

  public Device(Group group) {
    super(group);
  }

  public void setAllow() {}

  public void setDeny() {}

  public void getList() {}
}
