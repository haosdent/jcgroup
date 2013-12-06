package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class Freezer extends Common {

  public static final int subsystem = Constants.SUBSYS_FREEZER;

  public Freezer(Group group) {
    super(group);
  }

  public void setState() {}

  public void getState() {}
}
