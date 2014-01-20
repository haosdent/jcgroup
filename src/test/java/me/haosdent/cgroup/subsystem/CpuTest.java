package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class CpuTest {

  private static final Logger LOG = LoggerFactory.getLogger(CpuTest.class);
  private static Admin admin;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_CPU);
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
  public void testSetShares() {
    try {
      one.getCpu().setShares(100);
      assertEquals(one.getCpu().getShares(), 100);
      two.getCpu().setShares(200);
      assertEquals(two.getCpu().getShares(), 200);
    } catch (IOException e) {
      LOG.error("Set shares failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetCfsPeriodTime() {
    try {
      one.getCpu().setCfsPeriodTime(1000);
      assertEquals(one.getCpu().getCfsPeriodTime(), 1000);
      two.getCpu().setCfsPeriodTime(2000);
      assertEquals(two.getCpu().getCfsPeriodTime(), 2000);
    } catch (IOException e) {
      LOG.error("Set cfs_period_us failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetCfsQuotaTime() {
    try {
      one.getCpu().setCfsQuotaTime(1000);
      assertEquals(one.getCpu().getCfsQuotaTime(), 1000);
      two.getCpu().setCfsQuotaTime(2000);
      assertEquals(two.getCpu().getCfsQuotaTime(), 2000);
    } catch (IOException e) {
      LOG.error("Set cfs_quota_us failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetRtPeriodTime() {
    try {
      one.getCpu().setRtPeriodTime(1000);
      assertEquals(one.getCpu().getRtPeriodTime(), 1000);
      two.getCpu().setRtPeriodTime(2000);
      assertEquals(two.getCpu().getRtPeriodTime(), 2000);
    } catch (IOException e) {
      LOG.error("Set rt_period_us failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetRtRuntimeTime() {
    try {
      one.getCpu().setRtRuntimeTime(1000);
      assertEquals(one.getCpu().getRtRuntimeTime(), 1000);
      two.getCpu().setRtRuntimeTime(2000);
      assertEquals(two.getCpu().getRtRuntimeTime(), 2000);
    } catch (IOException e) {
      LOG.error("Set rt_runtime_us failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetStat() {
    try {
      Cpu.Stat actual = one.getCpu().getStat();
      Cpu.Stat expected = new Cpu.Stat("nr_periods 0\nnr_throttled 0\nthrottled_time 0");
      assertEquals(actual, expected);
    } catch (IOException e) {
      LOG.error("Get stat failed.", e);
      assertTrue(false);
    }
  }
}
