package me.haosdent.cgroup.manage;

import me.haosdent.cgroup.subsystem.*;
import me.haosdent.cgroup.util.Shell;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Group {

  private Shell shell;
  private Admin admin;
  private String name;

  private Blkio blkio;
  private Cpu cpu;
  private Cpuacct cpuacct;
  private Cpuset cpuset;
  private Devices devices;
  private Freezer freezer;
  private Memory memory;
  private NetCls netCls;
  private NetPrio netPrio;

  private List<Common> subSystemList = new LinkedList<Common>();

  protected Group(Admin admin, String name, int subsystems) throws IOException {
    this.admin = admin;
    this.shell = admin.getShell();
    shell.cgcreate(name, subsystems);
    admin.getGroupList().add(this);
  }

  public void delete() throws IOException {
    shell.cgdelete(name);
    List<Group> groupList = admin.getGroupList();
    synchronized (groupList) {
      groupList.remove(this);
    }
  }

  public String getName() {
    return name;
  }

  public Shell getShell() {
    return shell;
  }

  public Admin getAdmin() {
    return admin;
  }

  public List<Common> getSubSystemList() {
    return subSystemList;
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

  public Devices getDevices() {
    if (devices == null) {
      devices = new Devices(this);
    }

    return devices;
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
