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
      Blkio.Record actual = one.getBlkio().getReadBpsThrottle();
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set read_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetWriteBpsThrottle() {
    try {
      Blkio.Record excepted = new Blkio.Record(8, 0, null, 200);
      one.getBlkio().setWriteBpsThrottle(excepted.major, excepted.minor, (int) excepted.value);
      Blkio.Record actual = one.getBlkio().getWriteBpsThrottle();
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set write_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetReadIopsThrottle() {
    try {
      Blkio.Record excepted = new Blkio.Record(8, 0, null, 200);
      one.getBlkio().setReadIopsThrottle(excepted.major, excepted.minor, (int) excepted.value);
      Blkio.Record actual = one.getBlkio().getReadIopsThrottle();
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set read_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetWriteIopsThrottle() {
    try {
      Blkio.Record excepted = new Blkio.Record(8, 0, null, 200);
      one.getBlkio().setWriteIopsThrottle(excepted.major, excepted.minor, (int) excepted.value);
      Blkio.Record actual = one.getBlkio().getWriteIopsThrottle();
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set read_bps_device failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testResetStats() {}

  @Test
  public void testGetIoTime() {
    try {
      Blkio.Record[] records = one.getBlkio().getIoTime();
    } catch (IOException e) {
      LOG.error("");
    }
  }

  @Test
  public void testGetSectors() {}

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
  public void testGetIoServiceCount() {}

  @Test
  public void testGetIoServiceBytes() {}

  @Test
  public void testGetIoServiceTime() {}

  @Test
  public void testGetIoWaitTime() {}

  @Test
  public void testGetIoMergeCount() {}

  @Test
  public void testGetIoQueueCount() {}
}
