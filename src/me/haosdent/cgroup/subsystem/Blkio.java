package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

public class Blkio extends Common {

  public static final int subsystem = Constants.SUBSYS_BLKIO;

  public Blkio(Group group) {
    super(group);
  }

  public void setReadBpsThrottle() {}

  public void setWriteBpsThrottle() {}

  public void setReadIopsThrottle() {}

  public void setWriteIopsThrottle() {}

  public void resetStats() {}

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
