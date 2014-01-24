package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Devices extends Common {

  public static final int SUBSYS = Constants.SUBSYS_DEVICE;
  public static final String PROP_DEVICES_ALLOW = "devices.allow";
  public static final String PROP_DEVICES_DENY = "devices.deny";
  public static final String PROP_DEVICES_LIST = "devices.list";

  public static final char TYPE_ALL = 'a';
  public static final char TYPE_BLOCK = 'b';
  public static final char TYPE_CHAR = 'c';

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
    char type;
    int major;
    int minor;
    int accesses;

    public Record(char type, int major, int minor, int accesses) {
      this.type = type;
      this.major = major;
      this.minor = minor;
      this.accesses = accesses;
    }

    public Record(String output) {
      String[] splits = output.split("[: ]");
      type = splits[0].charAt(0);
      major = Integer.parseInt(splits[1]);
      minor = Integer.parseInt(splits[2]);
      accesses = Integer.parseInt(splits[3]);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(type);
      sb.append(' ');
      sb.append(major);
      sb.append(':');
      sb.append(minor);
      sb.append(' ');
      sb.append(getAccessesFlag(accesses));

      return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
      Record actual = (Record) o;
      boolean result = false;
      if (type == actual.type
              && major == actual.major
              && minor == actual.minor
              && accesses == actual.accesses) {
        result = true;
      }
      return result;
    }

    public static Record[] parseRecordList(String output) {
      String[] splits = output.split("/n");
      Record[] records = new Record[splits.length];
      for (int i = 0, l = splits.length; i < l; i++) {
        records[i] = new Record(splits[i]);
      }

      return records;
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
  }

  private void setPermission(String prop, char type, int major, int minor, int accesses) throws IOException {
    Record record = new Record(type, major, minor, accesses);

    shell.cgset(group.getName(), prop, record.toString());
  }

  public void setAllow(char type, int major, int minor, int accesses) throws IOException {
    setPermission(PROP_DEVICES_ALLOW, type, major, minor, accesses);
  }

  public void setDeny(char type, int major, int minor, int accesses) throws IOException {
    setPermission(PROP_DEVICES_DENY, type, major, minor, accesses);
  }

  public Record[] getList() throws IOException {
    String output = shell.cgget(group.getName(), PROP_DEVICES_LIST);
    return Record.parseRecordList(output);
  }
}
