package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class Cpuset extends Common {

  public static final int subsystem = Constants.SUBSYS_CPUSET;

  public Cpuset(Group group) {
    super(group);
  }

  public void setCpus() {}

  public void setMems() {}

  public void setMemMigrate() {}

  public void setCpuExclusive() {}

  public void setMemExclusive() {}

  public void setMemHardwall() {}

  public void getMemPressure() {}

  public void setMemPressureEnabled() {}

  public void setMemSpreadPage() {}

  public void setMemSpreadSlab() {}

  public void setSchedLoadBlance() {}

  public void setSchedRelaxDomainLevel() {}
}
