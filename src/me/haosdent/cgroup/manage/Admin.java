package me.haosdent.cgroup.manage;

import me.haosdent.cgroup.util.Shell;

public class Admin {

  private Shell shell;

  public Admin() {
    this.shell = new Shell(this);
  }

  public Shell getShell() {
    return shell;
  }

  public Group createGroup(String name) {
    return new Group(name, shell);
  }
}
