package me.haosdent.cgroup.manage;

import static me.haosdent.cgroup.util.Constants.*;

import me.haosdent.cgroup.util.Shell;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Admin {

  private Shell shell;
  private String name;
  private String password;
  private List<Group> groupList = new LinkedList<Group>();

  public Admin(String name, String password, int subsystems) throws IOException {
    this.name = name;
    this.password = password;
    this.shell = new Shell(this);
    shell.mount(name, subsystems);
  }

  public void umount() throws IOException {
    shell.umount(name);
  }

  public Shell getShell() {
    return shell;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public List<Group> getGroupList() {
    return groupList;
  }

  public Group createGroup(String name, int subsystems) throws IOException {
    Group group = new Group(this, name, subsystems);
    return group;
  }
}
