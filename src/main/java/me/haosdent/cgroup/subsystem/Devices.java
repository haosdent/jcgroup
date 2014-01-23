package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Devices extends Common {

  public static final int SUBSYS = Constants.SUBSYS_DEVICE;
  public static final String PROP_DEVICES_ALLOW = "devices.allow";
  public static final String PROP_DEVICES_DENY = "devices.deny";
  public static final String PROP_DEVICES_LIST = "devices.list";

  public static final int TYPE_ALL = 1;
  public static final int TYPE_BLOCK = 2;
  public static final int TYPE_CHAR = 4;

  public static final char TYPE_ALL_CH = 'a';
  public static final char TYPE_BLOCK_CH = 'b';
  public static final char TYPE_CHAR_CH = 'c';

  public static final int ACCESS_READ = 1;
  public static final int ACCESS_WRITE = 2;
  public static final int ACCESS_CREATE = 4;

  public static final char ACCESS_READ_CH = 'r';
  public static final char ACCESS_WRITE_CH = 'w';
  public static final char ACCESS_CREATE_CH = 'm';

  public Devices(Group group) {
    super(group);
  }

  public static class Record {

  }

  public static StringBuilder getTypesFlag(int types) {
    StringBuilder sb = new StringBuilder();
    if ((types & TYPE_ALL) != 0) {
      sb.append(TYPE_ALL_CH);
    }
    if ((types & TYPE_BLOCK) != 0) {
      sb.append(TYPE_BLOCK_CH);
    }
    if ((types & TYPE_CHAR) != 0) {
      sb.append(TYPE_CHAR_CH);
    }
    return sb;
  }

  public static StringBuilder getAccessesFlag(int accesses) {
    StringBuilder sb = new StringBuilder();
    if ((accesses & ACCESS_READ) != 0) {
      sb.append(ACCESS_READ_CH);
    }
    if ((accesses & ACCESS_WRITE) != 0) {
      sb.append(ACCESS_WRITE_CH);
    }
    if ((accesses & ACCESS_CREATE) != 0) {
      sb.append(ACCESS_CREATE_CH);
    }
    return sb;
  }

  private void setPermission(String prop, int types, int major, int minor, int accesses) throws IOException {
    StringBuilder sb = getTypesFlag(types);
    sb.append(' ');
    sb.append(major);
    sb.append(':');
    sb.append(minor);
    sb.append(' ');
    sb.append(getAccessesFlag(accesses));

    shell.cgset(group.getName(), prop, sb.toString());
  }

  public void setAllow(int types, int major, int minor, int accesses) throws IOException {
    setPermission(PROP_DEVICES_ALLOW, types, major, minor, accesses);
  }

  public void setDeny(int types, int major, int minor, int accesses) throws IOException {
    setPermission(PROP_DEVICES_DENY, types, major, minor, accesses);
  }

  public String getList() throws IOException {
    //TODO
    String result = shell.cgget(group.getName(), PROP_DEVICES_LIST);
    return result;
  }
}
