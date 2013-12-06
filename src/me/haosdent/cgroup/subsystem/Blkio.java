package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Blkio extends Common {

  public static final int SUBSYS = Constants.SUBSYS_BLKIO;
  public static final String PROP_BLKIO_THROTTLE_READ_BPS_DEVICE = "blkio.throttle.read_bps_device";
  public static final String PROP_BLKIO_THROTTLE_WRITE_BPS_DEVICE = "blkio.throttle.write_bps_device";
  public static final String PROP_BLKIO_THROTTLE_READ_IOPS_DEVICE = "blkio.throttle.read_iops_device";
  public static final String PROP_BLKIO_THROTTLE_WRITE_IOPS_DEVICE = "blkio.throttle.write_iops_device";

  public static final String PROP_BLKIO_RESET_STATS = "blkio.reset_stats";
  public static final String PROP_BLKIO_TIME = "blkio.time";

  public Blkio(Group group) {
    super(group);
  }

  private void setThrottle(String prop, int major, int minor, int speed) throws IOException {
    StringBuffer sb = new StringBuffer();
    sb.append(major);
    sb.append(':');
    sb.append(minor);
    sb.append(' ');
    sb.append(speed);
    shell.cgset(group.getName(), prop, sb.toString());
  }

  public void setReadBpsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_READ_BPS_DEVICE, major, minor, speed);
  }

  public void setWriteBpsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_WRITE_BPS_DEVICE, major, minor, speed);
  }

  public void setReadIopsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_READ_IOPS_DEVICE, major, minor, speed);
  }

  public void setWriteIopsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_WRITE_IOPS_DEVICE, major, minor, speed);
  }

  public void resetStats(int value) throws IOException {
    shell.cgset(group.getName(), PROP_BLKIO_RESET_STATS, value + "");
  }

  public void getIoTime() {}

  public void getSectors() {}

  public void getAvgQueueSize() {}

  public void getGroupWaitTime() {}

  public void getEmptyTime() {}

  public void getIdleTime() {}

  public void getDequeueCount() {}

  public void getIoServiceCount() {}

  public void getIoServiceBytes() {}

  public void getIoServiceTime() {}

  public void getIoWaitTime() {}

  public void getIoMergeCount() {}

  public void getIoQueueCount() {}
}
