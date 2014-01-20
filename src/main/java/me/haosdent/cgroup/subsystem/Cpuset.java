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
    StringBuilder sb = new StringBuilder();
    for (int num : nums) {
      sb.append(num);
      sb.append(',');
    }
    shell.cgset(group.getName(), PROP_CPUSET_CPUS, sb.toString());
  }

  public int[] getCpus() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_CPUS);
    int[] nums = {};
    //TODO
    return nums;
  }

  public void setMems(int[] nums) throws IOException {
    StringBuilder sb = new StringBuilder();
    for (int num : nums) {
      sb.append(num);
      sb.append(',');
    }
    shell.cgset(group.getName(), PROP_CPUSET_MEMS, sb.toString());
  }

  public int[] getMems() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEMS);
    int[] nums = {};
    //TODO
    return nums;
  }

  public void setMemMigrate(boolean flag) throws IOException {
    int value = flag? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_MIGRATE, value + "");
  }

  public boolean isMemMigrate() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEMORY_MIGRATE);
    boolean flag = Boolean.parseBoolean(output);
    return flag;
  }

  public void setCpuExclusive(boolean flag) throws IOException {
    int value = flag? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_CPU_EXCLUSIVE, value + "");
  }

  public boolean isCpuExclusive() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_CPU_EXCLUSIVE);
    boolean flag = Boolean.parseBoolean(output);
    return flag;
  }

  public void setMemExclusive(boolean flag) throws IOException {
    int value = flag ? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEM_EXCLUSIVE, value + "");
  }

  public boolean isMemExclusive() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEM_EXCLUSIVE);
    boolean flag = Boolean.parseBoolean(output);
    return flag;
  }

  public void setMemHardwall(boolean flag) throws IOException {
    int value = flag ? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEM_HARDWALL, value + "");
  }

  public boolean isMemHardwall() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEM_HARDWALL);
    boolean flag = Boolean.parseBoolean(output);
    return flag;
  }

  public int getMemPressure() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEMORY_PRESSURE);
    return Integer.parseInt(output);
  }

  public void setMemPressureEnabled(boolean flag) throws IOException {
    int value = flag ? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_PRESSURE_ENABLED, value + "");
  }

  public boolean isMemPressureEnabled() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEMORY_PRESSURE_ENABLED);
    return Boolean.parseBoolean(output);
  }

  public void setMemSpreadPage(boolean flag) throws IOException {
    int value = flag ? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_SPREAD_PAGE, value + "");
  }

  public boolean isMemSpreadPage() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEMORY_SPREAD_PAGE);
    return Boolean.parseBoolean(output);
  }

  public void setMemSpreadSlab(boolean flag) throws IOException {
    int value = flag ? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_MEMORY_SPREAD_SLAB, value + "");
  }

  public boolean isMemSpreadSlab() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_MEMORY_SPREAD_SLAB);
    return Boolean.parseBoolean(output);
  }

  public void setSchedLoadBlance(boolean flag) throws IOException {
    int value = flag ? 1 : 0;
    shell.cgset(group.getName(), PROP_CPUSET_SCHED_LOAD_BALANCE, value + "");
  }

  public boolean isSchedLoadBlance() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_SCHED_LOAD_BALANCE);
    return Boolean.parseBoolean(output);
  }

  public void setSchedRelaxDomainLevel(int value) throws IOException {
    shell.cgset(group.getName(), PROP_CPUSET_SCHED_RELAX_DOMAIN_LEVEL, value + "");
  }

  public int getSchedRelaxDomainLevel() throws IOException {
    String output = shell.cgget(group.getName(), PROP_CPUSET_SCHED_RELAX_DOMAIN_LEVEL);
    return Integer.parseInt(output);
  }
}
