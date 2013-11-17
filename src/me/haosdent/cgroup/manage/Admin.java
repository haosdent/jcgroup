package me.haosdent.cgroup.manage;

import static me.haosdent.cgroup.util.Constants.*;

import me.haosdent.cgroup.util.Shell;

public class Admin {

  private Shell shell;

  public Admin() {
    this.shell = new Shell(this);
    shell.mount(SUBSYS_BLKIO | SUBSYS_CPU, "cgroup");
  }

  public Shell getShell() {
    return shell;
  }

  public Group createGroup(String name) {
    return new Group(name, shell);
  }
}
