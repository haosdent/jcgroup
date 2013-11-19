package me.haosdent.cgroup.util;

import static me.haosdent.cgroup.util.Constants.*;

import me.haosdent.cgroup.manage.Admin;

import java.io.IOException;

public class Shell {

  Admin admin;

  public Shell(Admin admin) {
    this.admin = admin;
  }

  private StringBuffer getSubsystemsFlag(int subsystems) {
    StringBuffer sb = new StringBuffer();
    if ((subsystems & SUBSYS_BLKIO) != 0) {
      sb.append(SUBSYS_BLKIO_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_CPU) != 0) {
      sb.append(SUBSYS_CPU_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_CPUACCT) != 0) {
      sb.append(SUBSYS_CPUACCT_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_CPUSET) != 0) {
      sb.append(SUBSYS_CPUSET_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_DEVICE) != 0) {
      sb.append(SUBSYS_DEVICE_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_FREEZER) != 0) {
      sb.append(SUBSYS_FREEZER_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_MEMORY) != 0) {
      sb.append(SUBSYS_MEMORY_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_NET_CLS) != 0) {
      sb.append(SUBSYS_NET_CLS_STR);
      sb.append(',');
    }
    if ((subsystems & SUBSYS_NET_PRIO) != 0) {
      sb.append(SUBSYS_NET_PRIO_STR);
      sb.append(',');
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb;
  }

  public void exec(String cmd) throws IOException {
    Process process = Runtime.getRuntime().exec(cmd);
    try {
      process.waitFor();
    } catch (InterruptedException ie) {
      throw new IOException(ie.toString());
    }
  }

  public void mount(String name, int subsystems) throws IOException {
    String cmd = String.format(SHELL_MOUNT, getSubsystemsFlag(subsystems), name);
    exec(cmd);
  }

  public void umount(String name) throws IOException {
    String cmd = String.format(SHELL_UMOUNT, name);
    exec(cmd);
  }

  public void cgcreate(String group, int subsystems) throws IOException {
    String cmd = String.format(SHELL_CG_CREATE, getSubsystemsFlag(subsystems), group);
    exec(cmd);
  }

  public void cgdelete(String group) throws IOException {
    String cmd = String.format(SHELL_CG_DELETE, group);
    exec(cmd);
  }

  public void cgclassify(String group, int subsystems, int task) throws IOException {
    String cmd = String.format(SHELL_CG_CLASSIFY, group, task);
    exec(cmd);
  }

  public void cgset(String group, String prop, String value) throws IOException {
    String cmd = String.format(SHELL_CG_SET, prop, value, group);
    exec(cmd);
  }

  public void cgget(String group, String prop) throws IOException {
    String cmd = String.format(SHELL_CG_GET, prop, group);
    exec(cmd);
  }

  public void cgclear() throws IOException {
    String cmd = SHELL_CG_CLEAR;
    exec(cmd);
  }

  public void cgexec(String group, int subsystems, String command) throws IOException {
    String cmd = String.format(SHELL_CG_EXEC, getSubsystemsFlag(subsystems), group, command);
    exec(cmd);
  }
}
