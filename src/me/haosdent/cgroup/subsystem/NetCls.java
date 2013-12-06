package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class NetCls extends Common {

  public static final int subsystem = Constants.SUBSYS_NET_CLS;

  public NetCls(Group group) {
    super(group);
  }

  public void setClassId() {}

  public void getClassId() {}
}
