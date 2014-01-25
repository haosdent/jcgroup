package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NetPrio extends Common {

  public static final int SUBSYS = Constants.SUBSYS_NET_PRIO;
  public static final String PROP_NET_PRIO_PRIOIDX = "net_prio.prioidx";
  public static final String PROP_NET_PRIO_IFPRIOMAP = "net_prio.ifpriomap";

  public NetPrio(Group group) {
    super(group);
  }

  public int getPrioId() throws IOException {
    String output = shell.cgget(group.getName(), PROP_NET_PRIO_PRIOIDX);
    return Integer.parseInt(output);
  }

  public Map<String, Integer> getIfPrioMap() throws IOException {
    String output = shell.cgget(group.getName(), PROP_NET_PRIO_IFPRIOMAP);
    String[] splits = output.split("\n");
    Map<String, Integer> ifPrioMap = new HashMap<String, Integer>();
    for (String split : splits) {
      String[] tmpSplits = split.split(" ");
      ifPrioMap.put(tmpSplits[0], Integer.parseInt(tmpSplits[1]));
    }
    return ifPrioMap;
  }

  public void addIfPrioMap(String iface, int priority) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(iface);
    sb.append(' ');
    sb.append(priority);
    shell.cgset(group.getName(), PROP_NET_PRIO_IFPRIOMAP, sb.toString());
  }
}
