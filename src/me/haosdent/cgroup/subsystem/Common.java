package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import me.haosdent.cgroup.util.Shell;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Common {

  protected Shell shell;
  protected Group group;

  public static final int SUBSYS = Constants.SUBSYS_COMMON;
  public List<Integer> taskList = new LinkedList<Integer>();

  public Common(Group group) {
    this.group = group;
    this.shell = group.getShell();
    List<Common> subSystemList = group.getSubSystemList();
    synchronized (subSystemList) {
      subSystemList.add(this);
    }
  }

  public Group getGroup() {
    return group;
  }

  public Shell getShell() {
    return shell;
  }

  public void addTask(int task) throws IOException {
    shell.cgclassify(group.getName(), SUBSYS, task);
    taskList.add(task);
  }

  public void setEventControl() {
    //TODO
  }

  public void setNotifyOnRelease() {
    //TODO
  }

  public void setReleaseAgent() {
    //TODO
  }
}
