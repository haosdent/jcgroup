package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Cpuacct extends Common {

  public static final int SUBSYS = Constants.SUBSYS_CPUACCT;
  public static final String PROP_CPUACCT_USAGE = "cpuacct.usage";
  public static final String PROP_CPUACCT_STAT = "cpuacct.stat";
  public static final String PROP_CPUACCT_USAGE_PERCPU = "cpuacct.usage_percpu";

  public Cpuacct(Group group) {
    super(group);
  }

  public static class Stat {
    public final long userTime;
    public final long systemTime;

    public Stat(String statStr) {
      //TODO
      userTime = 1;
      systemTime = 1;
    }
  }

  public long getUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_CPUACCT_USAGE);
    return Long.parseLong(result);
  }

  public void resetUsage() throws IOException {
    shell.cgset(group.getName(), PROP_CPUACCT_USAGE, 0 + "");
  }

  public Stat getStat() throws IOException {
    String result = shell.cgget(group.getName(), PROP_CPUACCT_STAT);
    Stat stat = new Stat(result);
    return stat;
  }

  public long getPerUsage() throws IOException {
    //TODO
    String result = shell.cgget(group.getName(), PROP_CPUACCT_USAGE_PERCPU);
    return Long.parseLong(result);
  }
}
