package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Cpu extends Common {

  private static final Logger LOG = LoggerFactory.getLogger(Cpu.class);

  public static final int SUBSYS = Constants.SUBSYS_CPU;
  public static final String PROP_CPU_CFS_PERIOD_US = "cpu.cfs_period_us";
  public static final String PROP_CPU_CFS_QUOTA_US = "cpu.cfs_quota_us";
  public static final String PROP_CPU_STAT = "cpu.stat";
  public static final String PROP_CPU_SHARES = "cpu.shares";
  public static final String PROP_CPU_RT_PERIOD_US = "cpu.rt_period_us";
  public static final String PROP_CPU_RT_RUNTIME_US = "cpu.rt_runtime_us";

  public Cpu(Group group) {
    super(group);
  }

  public static class Stat {
    public final int nrPeriods;
    public final int nrThrottled;
    public final int throttledTime;

    public Stat(String statStr) {
      String[] splits = statStr.split("\n");
      this.nrPeriods = Integer.parseInt(splits[0].split(" ")[1]);
      this.nrThrottled = Integer.parseInt(splits[1].split(" ")[1]);
      this.throttledTime = Integer.parseInt(splits[2].split(" ")[1]);
    }

    @Override
    public boolean equals(Object obj) {
      Stat excepted = (Stat) obj;
      boolean result = false;
      if (this.nrPeriods == excepted.nrPeriods
              && this.nrThrottled == excepted.nrThrottled
              && this.throttledTime == excepted.throttledTime) {
        result = true;
      }
      return result;
    }
  }

  public void setShares(int shares) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_SHARES, shares + "");
  }

  public int getShares() throws IOException {
    int shares = Integer.parseInt(shell.cgget(group.getName(), PROP_CPU_SHARES));
    return shares;
  }

  public void setCfsPeriodTime(long time) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_CFS_PERIOD_US, time + "");
  }

  public int getCfsPeriodTime() throws IOException {
    int period = Integer.parseInt(shell.cgget(group.getName(), PROP_CPU_CFS_PERIOD_US));
    return period;
  }

  public void setCfsQuotaTime(long time) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_CFS_QUOTA_US, time + "");
  }

  public int getCfsQuataTime() throws IOException {
    int quata = Integer.parseInt(shell.cgget(group.getName(), PROP_CPU_CFS_QUOTA_US));
    return quata;
  }

  public void setRtPeriodTime(long time) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_RT_PERIOD_US, time + "");
  }

  public int getRtPeriodTime() throws IOException {
    int period = Integer.parseInt(shell.cgget(group.getName(), PROP_CPU_RT_PERIOD_US));
    return period;
  }

  public void setRtRuntimeTime(long time) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_RT_RUNTIME_US, time + "");
  }

  public Stat getStat() throws IOException {
    String result = shell.cgget(group.getName(), PROP_CPU_STAT);
    Stat stat = new Stat(result);
    return stat;
  }
}
