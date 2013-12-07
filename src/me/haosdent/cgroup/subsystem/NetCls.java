package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class NetCls extends Common {

  public static final int SUBSYS = Constants.SUBSYS_NET_CLS;
  public static final String PROP_NET_CLS_CLASSID = "net_cls.classid";

  public NetCls(Group group) {
    super(group);
  }

  private StringBuilder toHex(int num) {
    String hex = Integer.toHexString(num);
    StringBuilder sb = new StringBuilder();
    int l = hex.length();
    if (l > 4) {
      hex = hex.substring(l - 4 - 1, l);
    }
    for (; l < 4; l++) {
      sb.append('0');
    }
    sb.append(hex);
    return sb;
  }

  public void setClassId(int major, int minor) throws IOException {
    StringBuilder sb = toHex(major);
    sb.append(toHex(minor));
    shell.cgset(group.getName(), PROP_NET_CLS_CLASSID, sb.toString());
  }

  public int[] getClassId() throws IOException {
    String result = shell.cgget(group.getName(), PROP_NET_CLS_CLASSID);
    int major = Integer.getInteger(result.substring(2, 2 + 4), 16);
    int minor = Integer.getInteger(result.substring(6, 6 + 4), 16);
    int[] classId = {major, minor};
    return classId;
  }
}
