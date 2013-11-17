package me.haosdent.cgroup.manage;

import me.haosdent.cgroup.subsystem.*;
import me.haosdent.cgroup.util.Shell;

public class Group {

  private Shell shell;

  private Blkio blkio;
  private Cpu cpu;
  private Cpuacct cpuacct;
  private Cpuset cpuset;
  private Device device;
  private Freezer freezer;
  private Memory memory;
  private NetCls netCls;
  private NetPrio netPrio;

  protected Group(String name, Shell shell) {
    this.shell = shell;
  }

  public void delete() {
    //TODO
  }

  public Shell getShell() {
    return shell;
  }

  public Blkio getBlkio() {
    if (blkio == null) {
      blkio = new Blkio(this);
    }
    return blkio;
  }

  public Cpu getCpu() {
    if (cpu == null) {
      cpu = new Cpu(this);
    }
    return cpu;
  }

  public Cpuacct getCpuacct() {
    if (cpuacct == null) {
      cpuacct = new Cpuacct(this);
    }

    return cpuacct;
  }

  public Cpuset getCpuset() {
    if (cpuset == null) {
      cpuset = new Cpuset(this);
    }

    return cpuset;
  }

  public Device getDevice() {
    if (device == null) {
      device = new Device(this);
    }

    return device;
  }

  public Freezer getFreezer() {
    if (freezer == null) {
      freezer = new Freezer(this);
    }

    return freezer;
  }

  public Memory getMemory() {
    if (memory == null) {
      memory = new Memory(this);
    }

    return memory;
  }

  public NetCls getNetCls() {
    if (netCls == null) {
      netCls = new NetCls(this);
    }

    return netCls;
  }

  public NetPrio getNetPrio() {
    if (netPrio == null) {
      netPrio = new NetPrio(this);
    }

    return netPrio;
  }
}
