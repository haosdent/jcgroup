package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class NetPrio extends Common {

  public static final int subsystem = Constants.SUBSYS_NET_PRIO;

  public NetPrio(Group group) {
    super(group);
  }

  public void getPrioId() {};

  public void getIfPrioMap() {};

  public void addIfPrioMap() {};
}
