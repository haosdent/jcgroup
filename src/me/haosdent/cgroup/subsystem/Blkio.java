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
  public static final String PROP_BLKIO_SECTORS = "blkio.sectors";
  public static final String PROP_BLKIO_AVG_QUEUE_SIZE = "blkio.avg_queue_size";
  public static final String PROP_BLKIO_GROUP_WAIT_TIME = "blkio.group_wait_time";
  public static final String PROP_BLKIO_EMPTY_TIME = "blkio.empty_time";
  public static final String PROP_BLKIO_IDLE_TIME = "blkio.idle_time";
  public static final String PROP_BLKIO_DEQUEUE = "blkio.dequeue";
  public static final String PROP_BLKIO_IO_SERIVICED = "blkio.io_serviced";
  public static final String PROP_BLKIO_IO_SERIVICE_BYTES = "blkio.io_service_bytes";
  public static final String PROP_BLKIO_IO_SERIVICE_TIME = "blkio.io_service_time";
  public static final String PROP_BLKIO_IO_WAIT_TIME = "blkio.io_wait_time";
  public static final String PROP_BLKIO_IO_MERGED = "blkio.io_merged";
  public static final String PROP_BLKIO_IO_QUEUED = "blkio.io_queued";

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

  public long getIoTime() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_TIME);
    return Long.parseLong(result);
  }

  public long getSectors() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_SECTORS);
    return Long.parseLong(result);
  }

  public int getAvgQueueSize() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_AVG_QUEUE_SIZE);
    return Integer.parseInt(result);
  }

  public long getGroupWaitTime() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_GROUP_WAIT_TIME);
    return Long.parseLong(result);
  }

  public long getEmptyTime() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_EMPTY_TIME);
    return Long.parseLong(result);
  }

  public long getIdleTime() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_IDLE_TIME);
    return Long.parseLong(result);
  }

  public int getDequeueCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_DEQUEUE);
    return Integer.parseInt(result);
  }

  public int getIoServiceCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_IO_SERIVICED);
    return Integer.parseInt(result);
  }

  public int getIoServiceBytes() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_IO_SERIVICE_BYTES);
    return Integer.parseInt(result);
  }

  public long getIoServiceTime() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_IO_SERIVICE_TIME);
    return Long.parseLong(result);
  }

  public long getIoWaitTime() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_IO_WAIT_TIME);
    return Long.parseLong(result);
  }

  public int getIoMergeCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_IO_MERGED);
    return Integer.parseInt(result);
  }

  public int getIoQueueCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_BLKIO_IO_QUEUED);
    return Integer.parseInt(result);
  }
}
