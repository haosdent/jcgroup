package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Freezer extends Common {

  public static final int SUBSYS = Constants.SUBSYS_FREEZER;
  public static final String PROP_FREEZER_STATE = "freezer.state";

  public static final String STATE_FROZEN = "FROZEN";
  public static final String STATE_FREEZING = "FREEZING";
  public static final String STATE_THAWED = "THAWED";

  public Freezer(Group group) {
    super(group);
  }

  public void setState(String state) throws IOException {
    shell.cgset(group.getName(), PROP_FREEZER_STATE, state);
  }

  public String getState() throws IOException {
    String state = shell.cgget(group.getName(), PROP_FREEZER_STATE);
    return state;
  }
}
