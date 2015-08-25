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

  @Override
  public int getSubsys() {
    return SUBSYS;
  }

  private StringBuilder toHex(int num) {
    String hex = num + "";
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
    StringBuilder sb = new StringBuilder("0x");
    sb.append(toHex(major));
    sb.append(toHex(minor));
    shell.cgset(group.getName(), PROP_NET_CLS_CLASSID, sb.toString());
  }

  public int[] getClassId() throws IOException {
    String output = shell.cgget(group.getName(), PROP_NET_CLS_CLASSID);
    output = Integer.toHexString(Integer.parseInt(output));
    int major = Integer.parseInt(output.substring(0, output.length() - 4));
    int minor = Integer.parseInt(output.substring(output.length() - 4));
    int[] classId = {major, minor};
    return classId;
  }
}
