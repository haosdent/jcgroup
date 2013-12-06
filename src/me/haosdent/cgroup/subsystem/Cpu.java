package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Cpu extends Common {

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
      //TODO
      this.nrPeriods = 1;
      this.nrThrottled = 1;
      this.throttledTime = 1;
    }
  }

  public void setShares(int shares) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_SHARES, shares + "");
  }

  public void setCfsPeriodTime(long time) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_CFS_PERIOD_US, time + "");
  }

  public void setCfsQuotaTime(long time) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_CFS_QUOTA_US, time + "");
  }

  public void setRtPeriodTime(long time) throws IOException {
    shell.cgset(group.getName(), PROP_CPU_RT_PERIOD_US, time + "");
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
