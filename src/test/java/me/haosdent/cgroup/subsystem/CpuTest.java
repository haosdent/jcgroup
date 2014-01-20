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
      Admin admin = new Admin(Constants.SUBSYS_CPU);
      Group one = admin.createGroup("one", Constants.SUBSYS_CPU);
      Group two = admin.createGroup("two", Constants.SUBSYS_CPU);
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
  }

  @Test
  public void testSetCfsQuotaTime() {}

  @Test
  public void testSetRtPeriodTime() {}

  @Test
  public void testSetRtRuntimeTime() {}

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
