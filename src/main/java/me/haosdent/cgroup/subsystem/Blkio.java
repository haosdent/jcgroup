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
  public static final String PROP_BLKIO_THROTTLE_IO_SERIVICED = "blkio.throttle.io_serviced";
  public static final String PROP_BLKIO_THROTTLE_IO_SERIVICE_BYTES = "blkio.throttle.io_service_bytes";
  public static final String PROP_BLKIO_THROTTLE_IO_QUEUED = "blkio.throttle.io_queued";

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
    Record record = new Record(major, minor, null, speed);
    shell.cgset(group.getName(), prop, record.toString());
  }

  private Record[] getThrottle(String prop) throws IOException {
    String output = shell.cgget(group.getName(), prop);
    return Record.parseRecordList(output);
  }

  public static class Record {
    int major;
    int minor;
    String operation;
    long value;

    public Record(int major, int minor, String operation, long value) {
      this.major = major;
      this.minor = minor;
      this.operation = operation;
      this.value = value;
    }

    public Record(String output) {
      String[] splits = output.split("[: ]");
      major = Integer.parseInt(splits[0]);
      minor = Integer.parseInt(splits[1]);
      if (splits.length > 3) {
        operation = splits[2];
        value = Long.parseLong(splits[3]);
      } else {
        value = Long.parseLong(splits[2]);
      }
    }

    public static Record[] parseRecordList(String output) {
      output = output.substring(0, output.indexOf("Total"));

      String[] splits = output.split("/n");
      Record[] records = new Record[splits.length];
      for (int i = 0, l = splits.length; i < l; i++) {
        records[i] = new Record(splits[i]);
      }

      return records;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(major);
      sb.append(':');
      sb.append(minor);
      if (operation != null) {
        sb.append(' ');
        sb.append(operation);
      }
      sb.append(' ');
      sb.append(value);

      return  sb.toString();
    }

    @Override
    public boolean equals(Object o) {
      Record excepted = (Record) o;
      boolean result = false;
      if (major == excepted.major
              && minor == excepted.minor
              && value == excepted.value) {
        if ((operation == null && excepted.operation == null)
                || (operation != null && excepted.operation != null && operation.equals(excepted.operation))) {
          result = true;
        }
      }

      return result;
    }
  }

  public void setReadBpsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_READ_BPS_DEVICE, major, minor, speed);
  }

  public Record[] getReadBpsThrottle() throws IOException {
    return getThrottle(PROP_BLKIO_THROTTLE_READ_BPS_DEVICE);
  }

  public void setWriteBpsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_WRITE_BPS_DEVICE, major, minor, speed);
  }

  public Record[] getWriteBpsThrottle() throws IOException {
    return getThrottle(PROP_BLKIO_THROTTLE_WRITE_BPS_DEVICE);
  }

  public void setReadIopsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_READ_IOPS_DEVICE, major, minor, speed);
  }

  public Record[] getReadIopsThrottle() throws IOException {
    return getThrottle(PROP_BLKIO_THROTTLE_READ_IOPS_DEVICE);
  }

  public void setWriteIopsThrottle(int major, int minor, int speed) throws IOException {
    setThrottle(PROP_BLKIO_THROTTLE_WRITE_IOPS_DEVICE, major, minor, speed);
  }

  public Record[] getWriteIopsThrottle() throws IOException {
    return getThrottle(PROP_BLKIO_THROTTLE_WRITE_IOPS_DEVICE);
  }

  public Record[] getIoQueueCountThrottle() throws IOException {
    return getThrottle(PROP_BLKIO_THROTTLE_IO_QUEUED);
  }

  public Record[] getIoServiceCountThrottle() throws IOException {
    return getThrottle(PROP_BLKIO_THROTTLE_IO_SERIVICED);
  }

  public Record[] getIoServiceBytesThrottle() throws IOException {
    return getThrottle(PROP_BLKIO_THROTTLE_IO_SERIVICE_BYTES);
  }

  public void resetStats(int value) throws IOException {
    shell.cgset(group.getName(), PROP_BLKIO_RESET_STATS, value + "");
  }

  public Record[] getIoTime() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_TIME);
    return Record.parseRecordList(output);
  }

  public Record[] getSectors() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_SECTORS);
    return Record.parseRecordList(output);
  }

  public int getAvgQueueSize() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_AVG_QUEUE_SIZE);
    return Integer.parseInt(output);
  }

  public long getGroupWaitTime() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_GROUP_WAIT_TIME);
    return Long.parseLong(output);
  }

  public long getEmptyTime() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_EMPTY_TIME);
    return Long.parseLong(output);
  }

  public long getIdleTime() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_IDLE_TIME);
    return Long.parseLong(output);
  }

  public Record getDequeueCount() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_DEQUEUE);
    return new Record(output);
  }

  public Record[] getIoServiceCount() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_IO_SERIVICED);
    return Record.parseRecordList(output);
  }

  public Record[] getIoServiceBytes() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_IO_SERIVICE_BYTES);
    return Record.parseRecordList(output);
  }

  public Record[] getIoServiceTime() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_IO_SERIVICE_TIME);
    return Record.parseRecordList(output);
  }

  public Record[] getIoWaitTime() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_IO_WAIT_TIME);
    return Record.parseRecordList(output);
  }

  public Record[] getIoMergeCount() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_IO_MERGED);
    return Record.parseRecordList(output);
  }

  public Record[] getIoQueueCount() throws IOException {
    String output = shell.cgget(group.getName(), PROP_BLKIO_IO_QUEUED);
    return Record.parseRecordList(output);
  }
}
