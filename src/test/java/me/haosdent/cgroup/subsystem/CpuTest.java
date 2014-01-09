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

  @Before
  public void setUpClass() {}

  @After
  public void tearDown() {}

  @Test
  public void testSetShares() {
    try {
      Admin admin = new Admin(Constants.SUBSYS_CPU);
      Group one = admin.createGroup("one", Constants.SUBSYS_CPU);
      Group two = admin.createGroup("two", Constants.SUBSYS_CPU);
      one.getCpu().setShares(100);
      assertEquals(one.getCpu().getShares(), 100);
      two.getCpu().setShares(200);
      assertEquals(two.getCpu().getShares(), 200);
      admin.umount();
    } catch (IOException e) {
      LOG.error("Create Admin Failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetCfsPeriodTime() {}

  @Test
  public void testSetCfsQuotaTime() {}

  @Test
  public void testSetRtPeriodTime() {}

  @Test
  public void testSetRtRuntimeTime() {}

  @Test
  public void testGetStat() {
    try {
      Admin admin = new Admin(Constants.SUBSYS_CPU);
      Group one = admin.createGroup("one", Constants.SUBSYS_CPU);
      Cpu.Stat actual = one.getCpu().getStat();
      Cpu.Stat expected = new Cpu.Stat("nr_periods 0\nnr_throttled 0\nthrottled_time 0");
      assertEquals(actual, expected);
      admin.umount();
    } catch (IOException e) {
      LOG.error("Create Admin Failed.", e);
      assertTrue(false);
    }
  }
}
