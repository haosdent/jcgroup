package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class BlkioTest {

  private static final Logger LOG = LoggerFactory.getLogger(BlkioTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_BLKIO);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_BLKIO);
      two = admin.createGroup("two", Constants.SUBSYS_BLKIO);
    } catch (IOException e) {
      LOG.error("Create cgroup Failed.", e);
      assertTrue(false);
    }
  }

  @AfterClass
  public static void tearDownClass() {
    try {
      admin.umount();
    } catch (IOException e) {
      LOG.error("Umount cgroup failed.", e);
    }
  }

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test
  public void testSetReadBpsThrottle() {
    try {
      Blkio.Record excepted = new Blkio.Record(8, 0, null, 200);
      one.getBlkio().setReadBpsThrottle(excepted.major, excepted.minor, (int) excepted.value);
      Blkio.Record actual = one.getBlkio().getReadBpsThrottle()[0];
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set throttle.read_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetWriteBpsThrottle() {
    try {
      Blkio.Record excepted = new Blkio.Record(8, 0, null, 200);
      one.getBlkio().setWriteBpsThrottle(excepted.major, excepted.minor, (int) excepted.value);
      Blkio.Record actual = one.getBlkio().getWriteBpsThrottle()[0];
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set throttle.write_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetReadIopsThrottle() {
    try {
      Blkio.Record excepted = new Blkio.Record(8, 0, null, 200);
      one.getBlkio().setReadIopsThrottle(excepted.major, excepted.minor, (int) excepted.value);
      Blkio.Record actual = one.getBlkio().getReadIopsThrottle()[0];
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set throttle.read_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetWriteIopsThrottle() {
    try {
      Blkio.Record excepted = new Blkio.Record(8, 0, null, 200);
      one.getBlkio().setWriteIopsThrottle(excepted.major, excepted.minor, (int) excepted.value);
      Blkio.Record actual = one.getBlkio().getWriteIopsThrottle()[0];
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set throttle.read_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoQueueCountThrottle() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoQueueCountThrottle();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get throttle.io_queued failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoServiceCountThrottle() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoServiceCountThrottle();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get throttle.io_serviced failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoServiceBytesThrottle() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoServiceBytesThrottle();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get throttle.io_service_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testResetStats() {
    try {
      root.getBlkio().resetStats(0);
    } catch (IOException e) {
      LOG.error("Reset reset_stats failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoTime() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoTime();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get time failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetSectors() {
    try {
      Blkio.Record[] records = root.getBlkio().getSectors();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get sectors failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetAvgQueueSize() {}

  @Test
  public void testGetGroupWaitTime() {}

  @Test
  public void testGetEmptyTime() {}

  @Test
  public void testGetIdleTime() {}

  @Test
  public void testGetDequeueCount() {}

  @Test
  public void testGetIoServiceCount() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoServiceCount();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get io_serviced failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoServiceBytes() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoServiceBytes();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get io_service_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoServiceTime() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoServiceTime();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get io_service_time failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoWaitTime() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoWaitTime();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get io_wait_time failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoMergeCount() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoMergeCount();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get io_merged failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetIoQueueCount() {
    try {
      Blkio.Record[] records = root.getBlkio().getIoQueueCount();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get io_queued failed.", e);
      assertTrue(false);
    }
  }
}
