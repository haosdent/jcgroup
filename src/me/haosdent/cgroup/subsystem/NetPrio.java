package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class NetPrio extends Common {

  public static final int SUBSYS = Constants.SUBSYS_NET_PRIO;
  public static final String PROP_NET_PRIO_PRIOIDX = "net_prio.prioidx";
  public static final String PROP_NET_PRIO_IFPRIOMAP = "net_prio.ifpriomap";

  public NetPrio(Group group) {
    super(group);
  }

  public int getPrioId() throws IOException {
    String result = shell.cgget(group.getName(), PROP_NET_PRIO_PRIOIDX);
    return Integer.parseInt(result);
  }

  public String getIfPrioMap() throws IOException {
    String result = shell.cgget(group.getName(), PROP_NET_PRIO_IFPRIOMAP);
    return result;
  }

  public void addIfPrioMap(String iface, int priority) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(iface);
    sb.append(' ');
    sb.append(priority);
    shell.cgset(group.getName(), PROP_NET_PRIO_IFPRIOMAP, sb.toString());
  }
}
