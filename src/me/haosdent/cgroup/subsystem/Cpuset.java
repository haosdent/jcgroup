package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Cpuset extends Common {

  public static final int SUBSYS = Constants.SUBSYS_CPUSET;
  public static final String PROP_CPUSET_CPUS = "cpuset.cpus";
  public static final String PROP_CPUSET_MEMS = "cpuset.mems";
  public static final String PROP_CPUSET_MEMORY_MIGRATE = "cpuset.memory_migrate";
  public static final String PROP_CPUSET_CPU_EXCLUSIVE = "cpuset.cpu_exclusive";
  public static final String PROP_CPUSET_MEM_EXCLUSIVE = "cpuset.mem_exclusive";
  public static final String PROP_CPUSET_MEM_HARDWALL = "cpuset.mem_hardwall";
  public static final String PROP_CPUSET_MEMORY_PRESSURE = "cpuset.memory_pressure";
  public static final String PROP_CPUSET_MEMORY_PRESSURE_ENABLED = "cpuset.memory_pressure_enabled";
  public static final String PROP_CPUSET_MEMORY_SPREAD_PAGE = "cpuset.memory_spread_page";
  public static final String PROP_CPUSET_MEMORY_SPREAD_SLAB = "cpuset.memory_spread_slab";
  public static final String PROP_CPUSET_SCHED_LOAD_BALANCE = "cpuset.sched_load_balance";
  public static final String PROP_CPUSET_SCHED_RELAX_DOMAIN_LEVEL = "cpuset.sched_relax_domain_level";

  public Cpuset(Group group) {
    super(group);
  }

  public void setCpus(int[] nums) throws IOException {
    StringBuffer sb = new StringBuffer();
    for (int i = 0, l = nums.length; i < l; i++) {
      sb.append(nums[i]);
      sb.append(',');
    }
    shell.cgset(group.getName(), PROP_CPUSET_CPUS, sb.toString());
  }

  public void setMems(int[] nums) throws IOException {
    StringBuffer sb = new StringBuffer();
    for (int i = 0, l = nums.length; i < l; i++) {
      sb.append(nums[i]);
      sb.append(',');
    }
    shell.cgset(group.getName(), PROP_CPUSET_MEMS, sb.toString());
  }

  public void setMemMigrate(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_MIGRATE, value + "");
  }

  public void setCpuExclusive(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEM_EXCLUSIVE, value + "");
  }

  public void setMemExclusive(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEM_EXCLUSIVE, value + "");
  }

  public void setMemHardwall(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEM_HARDWALL, value + "");
  }

  public int getMemPressure() throws IOException {
    String result = shell.cgget(group.getName(), PROP_CPUSET_MEMORY_PRESSURE);
    return Integer.parseInt(result);
  }

  public void setMemPressureEnabled(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_PRESSURE_ENABLED, value + "");
  }

  public void setMemSpreadPage(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_SPREAD_PAGE, value + "");
  }

  public void setMemSpreadSlab(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_SPREAD_SLAB, value + "");
  }

  public void setSchedLoadBlance(boolean flag) throws IOException {
    int value = flag == true? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_SCHED_LOAD_BALANCE, value + "");
  }

  public void setSchedRelaxDomainLevel(int value) throws IOException {
    shell.cgset(group.getName(), PROP_CPUSET_SCHED_RELAX_DOMAIN_LEVEL, value + "");
  }
}
