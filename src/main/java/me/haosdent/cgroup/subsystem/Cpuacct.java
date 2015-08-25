package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Cpuacct extends Common {

  private static final Logger LOG = LoggerFactory.getLogger(Cpuacct.class);

  public static final int SUBSYS = Constants.SUBSYS_CPUACCT;
  public static final String PROP_CPUACCT_USAGE = "cpuacct.usage";
  public static final String PROP_CPUACCT_STAT = "cpuacct.stat";
  public static final String PROP_CPUACCT_USAGE_PERCPU = "cpuacct.usage_percpu";

  public Cpuacct(Group group) {
    super(group);
  }

  @Override
  public int getSubsys() {
    return SUBSYS;
  }

  public static class Stat {
    public final long userTime;
    public final long systemTime;

    public Stat(String statStr) {
      String[] splits = statStr.split("\n");
      this.userTime = Integer.parseInt(splits[0].split(" ")[1]);
      this.systemTime = Integer.parseInt(splits[1].split(" ")[1]);
    }

    @Override
    public boolean equals(Object obj) {
      Stat excepted = (Stat) obj;
      boolean result = false;
      if (this.userTime == excepted.userTime
              && this.systemTime == excepted.systemTime) {
        result = true;
      }
      return result;
    }
  }

  public long getUsage() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUACCT_USAGE);
    return Long.parseLong(output);
  }

  public void resetUsage() throws IOException {
    shell.cgset(group.getName(), PROP_CPUACCT_USAGE, 0 + "");
  }

  public Stat getStat() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUACCT_STAT);
    Stat stat = new Stat(output);
    return stat;
  }

  public long[] getPerUsage() throws IOException {
    String[] outputs = shell.cgget(group.getName(), PROP_CPUACCT_USAGE_PERCPU).split(" ");
    long[] usages = new long[outputs.length];
    for (int i = 0, l = outputs.length; i < l; i++) {
      usages[i] = Long.parseLong(outputs[i]);
    }
    return usages;
  }
}
