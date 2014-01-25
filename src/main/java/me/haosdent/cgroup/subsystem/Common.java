package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import me.haosdent.cgroup.util.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Common {

  public static final int SUBSYS = Constants.SUBSYS_COMMON;
  public static final String PROP_COMMON_EVENT_CONTROL = "cgroup.event_control";
  public static final String PROP_COMMON_NOTIFY_ON_RELEASE =
      "notify_on_release";
  public static final String PROP_COMMON_RELEASE_AGENT = "release_agent";
  private static final Logger LOG = LoggerFactory.getLogger(Common.class);
  public List<Integer> taskList = new LinkedList<Integer>();
  protected Shell shell;
  protected Group group;

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

  public void setEventControl(String eventFd, String controlFd, String... args)
      throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(eventFd);
    sb.append(' ');
    sb.append(controlFd);
    for (String arg : args) {
      sb.append(' ');
      sb.append(arg);
    }

    shell.cgset(group.getName(), PROP_COMMON_EVENT_CONTROL, sb.toString());
  }

  public void setNotifyOnRelease(boolean flag) throws IOException {
    int value = flag? 1 : 0;
    shell.cgset(group.getName(), PROP_COMMON_NOTIFY_ON_RELEASE, value + "");
  }

  public boolean isNotifyOnRelease() throws IOException {
    int output = Integer.getInteger(shell.cgget(group.getName(), PROP_COMMON_NOTIFY_ON_RELEASE));
    return output > 0;
  }

  public void setReleaseAgent(String cmd) throws IOException {
    shell.cgset(group.getName(), PROP_COMMON_RELEASE_AGENT, cmd);
  }

  public String getReleaseAgent() throws IOException {
    String cmd = shell.cgget(group.getName(), PROP_COMMON_RELEASE_AGENT);
    return cmd;
  }
}
