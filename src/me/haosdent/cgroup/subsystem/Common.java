package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;

public abstract class Common {

  Group group;

  public Common(Group group) {
    this.group = group;
  }

  public void addThreadId() {}

  public void addThreadGroupId() {}

  public void setEventControl() {}

  public void setNotifyOnRelease() {}

  public void setReleaseAgent() {}
}
