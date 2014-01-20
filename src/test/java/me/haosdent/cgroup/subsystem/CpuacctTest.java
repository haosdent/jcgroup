package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class CpuacctTest {

  private static final Logger LOG = LoggerFactory.getLogger(CpuacctTest.class);
  private static Admin admin;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_CPUACCT);
      one = admin.createGroup("one", Constants.SUBSYS_CPUACCT);
      two = admin.createGroup("two", Constants.SUBSYS_CPUACCT);
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
  public void testGetUsage() {
    try {
      long usage = one.getCpuacct().getUsage();
      assertTrue(usage >= 0);
    } catch (IOException e) {
      LOG.error("Get usage failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testResetUsage() {
    try {
      one.getCpuacct().resetUsage();
      long usage = one.getCpuacct().getUsage();
      assertEquals(usage, 0);
    } catch (IOException e) {
      LOG.error("Get usage failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetStat() {
    try {
      Cpuacct.Stat stat = one.getCpuacct().getStat();
      assertTrue(stat.userTime >= 0);
      assertTrue(stat.systemTime >= 0);
    } catch (IOException e) {
      LOG.error("Get stat failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetPerUsage() {
    try {
      long[] usages = one.getCpuacct().getPerUsage();
      assertTrue(usages.length > 0);
      for (long usage: usages) {
        assertTrue(usage > 0);
      }
    } catch (IOException e) {
      LOG.error("Get usage_percpu failed.", e);
      assertTrue(false);
    }
  }
}
