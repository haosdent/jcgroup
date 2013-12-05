package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Shell;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Common {

  private Shell shell;
  private Group group;

  public final int subsystem = 0;
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

  public void addThreadId(int task) throws IOException {
    shell.cgclassify(group.getName(), subsystem, task);
    taskList.add(task);
  }

  public void addThreadGroupId() {
    //TODO
  }

  public void setEventControl() {}

  public void setNotifyOnRelease() {}

  public void setReleaseAgent() {}
}
