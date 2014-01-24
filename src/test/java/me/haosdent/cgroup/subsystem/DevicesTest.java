package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.io.IOException;

public class DevicesTest {

  private static final Logger LOG = LoggerFactory.getLogger(DevicesTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_DEVICE);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_DEVICE);
      two = admin.createGroup("two", Constants.SUBSYS_DEVICE);
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
  public void testSetAllow() {
    try {
      Devices.Record record = new Devices.Record('a', 8, 0, 2);
      one.getDevices().setAllow(record.type, record.major, record.minor, record.accesses);
    } catch (IOException e) {
      LOG.error("Set allow failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetDeny() {
    try {
      Devices.Record record = new Devices.Record('a', 8, 0, 2);
      one.getDevices().setDeny(record.type, record.major, record.minor, record.accesses);
    } catch (IOException e) {
      LOG.error("Set deny failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetList() {
    try {
      Devices.Record[] records = root.getDevices().getList();
      assertTrue(records.length > 0);
    } catch (IOException e) {
      LOG.error("Get list failed.", e);
      assertTrue(false);
    }
  }
}
