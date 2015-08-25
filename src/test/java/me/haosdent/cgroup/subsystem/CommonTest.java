package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import me.haosdent.cgroup.util.Threads;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class CommonTest {

  private static final Logger LOG = LoggerFactory.getLogger(CommonTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_CPU);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_CPU);
      two = admin.createGroup("two", Constants.SUBSYS_CPU);
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
  public void testAddTask() {
    try {
      int tid = Threads.getThreadId();
      one.getCpu().addTask(tid);
    } catch (IOException e) {
      LOG.error("Add task failed.", e);
      assertTrue(false);
    }
  }

  //FIXME
  //@Test
  public void testSetEventControl() {
  }

  //FIXME
  //@Test
  public void testSetNotifyOnRelease() {
    try {
      one.getCpu().setNotifyOnRelease(true);
      assertTrue(one.getCpu().isNotifyOnRelease());
    } catch (IOException e) {
      LOG.error("Set notify_on_release failed.", e);
      assertTrue(false);
    }
  }

  //FIXME
  //@Test
  public void testSetReleaseAgent() {
    try {
      String excepted = "echo 0";
      one.getCpu().setReleaseAgent(excepted);
      String actual = one.getCpu().getReleaseAgent();
      assertEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set release_agent failed.", e);
      assertTrue(false);
    }
  }
}
