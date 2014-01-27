package me.haosdent.cgroup.util;

import static me.haosdent.cgroup.util.Constants.*;

import com.google.common.annotations.VisibleForTesting;
import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.subsystem.Cpu;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {

  private static final Logger LOG = LoggerFactory.getLogger(Shell.class);
  Admin admin;
  String prefix;

  public Shell(Admin admin) {
    this.admin = admin;
    StringBuilder sb = new StringBuilder();
    sb.append("echo ");
    sb.append(admin.getPassword());
    sb.append("|sudo -S ");
    this.prefix = sb.toString();
  }

  @VisibleForTesting
  public Shell() {
  }

  private static StringBuilder getSubsystemsFlag(int subsystems) {
    StringBuilder sb = new StringBuilder();
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

  public String exec(String cmd) throws IOException {
    return exec(cmd, false);
  }

  public String exec(String cmd, boolean isPrivilege) throws IOException {
    if (isPrivilege) {
      cmd = prefix + cmd;
    }
    LOG.info("Shell cmd:" + cmd);
    Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
    try {
      process.waitFor();
      String output = IOUtils.toString(process.getInputStream());
      String errorOutput = IOUtils.toString(process.getErrorStream());
      LOG.info("Shell Output:" + output);
      if (errorOutput.length() != 0 && !errorOutput.contains("[sudo] password for") && !errorOutput.contains("Password:")) {
        LOG.error("Shell Error Output:" + errorOutput);
        throw new IOException(errorOutput);
      }
      return output;
    } catch (InterruptedException ie) {
      throw new IOException(ie.toString());
    }
  }

  public void mount(String name, int subsystems) throws IOException {
    String path = String.format(PREFIX_CGROUP_DIR, name);
    mkdir(path);
    StringBuilder flag = getSubsystemsFlag(subsystems);
    String cmd = String.format(SHELL_MOUNT, flag, flag, path);
    exec(cmd, true);
  }

  public void umount(String name) throws IOException {
    String path = String.format(PREFIX_CGROUP_DIR, name);
    String cmd = String.format(SHELL_UMOUNT, path);
    exec(cmd, true);
    rmdir(path);
  }

  public void mkdir(String path) throws IOException {
    String cmd = String.format(SHELL_MKDIR, path);
    exec(cmd, true);
  }

  public void rmdir(String path) throws IOException {
    String cmd = String.format(SHELL_RMDIR, path);
    exec(cmd, true);
  }

  public void cgcreate(String group, int subsystems) throws IOException {
    String cmd =
        String.format(SHELL_CG_CREATE, getSubsystemsFlag(subsystems), group);
    exec(cmd, true);
  }

  public void cgdelete(String group, int subsystems) throws IOException {
    String cmd = String.format(SHELL_CG_DELETE, getSubsystemsFlag(subsystems), group);
    exec(cmd, true);
  }

  public void cgclassify(String group, int subsystems, int task)
      throws IOException {
    String cmd =
        String.format(SHELL_CG_CLASSIFY, getSubsystemsFlag(subsystems), group,
                task);
    exec(cmd, true);
  }

  public void cgset(String group, String prop, String value) throws IOException {
    String cmd = String.format(SHELL_CG_SET, prop, value, group);
    exec(cmd, true);
  }

  public String cgget(String group, String prop) throws IOException {
    String cmd = String.format(SHELL_CG_GET, prop, group);
    String result = filterCggetOutput(prop, exec(cmd));
    return result;
  }

  public void cgclear() throws IOException {
    String cmd = SHELL_CG_CLEAR;
    exec(cmd, true);
  }

  public void cgexec(String group, int subsystems, String command)
      throws IOException {
    String cmd =
        String.format(SHELL_CG_EXEC, getSubsystemsFlag(subsystems), group,
            command);
    exec(cmd, true);
  }

  public String filterCggetOutput(String prop, String result) {
    result = result.replaceAll(" {2,}", "");
    result = result.substring(result.indexOf(prop) + prop.length() + 2, result.length() - 2);
    return result;
  }
}
