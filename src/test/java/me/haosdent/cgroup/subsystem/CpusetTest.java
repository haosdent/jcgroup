package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class CpusetTest {

  private static final Logger LOG = LoggerFactory.getLogger(CpuacctTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_CPUSET);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_CPUSET);
      two = admin.createGroup("two", Constants.SUBSYS_CPUSET);
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
      assertTrue(false);
    }
  }

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test
  public void testSetCpus() {
    try {
      int[] actual = {0,1};
      one.getCpuset().setCpus(actual);
      int[] excepted = one.getCpuset().getCpus();
      assertArrayEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set cpus failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetMems() {
    try {
      int[] actual = {0};
      one.getCpuset().setMems(actual);
      int[] excepted = one.getCpuset().getMems();
      assertArrayEquals(actual, excepted);
    } catch (IOException e) {
      LOG.error("Set mems failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetMemMigrate() {
    try {
      one.getCpuset().setMemMigrate(true);
      boolean flag = one.getCpuset().isMemMigrate();
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set memory_migrate failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetCpuExclusive() {
    try {
      one.getCpuset().setCpuExclusive(true);
      boolean flag = one.getCpuset().isCpuExclusive();
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set cpu_exclusive failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetMemExclusive() {
    try {
      one.getCpuset().setMemExclusive(true);
      boolean flag = one.getCpuset().isMemExclusive();
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set mem_exclusive failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetMemHardwall() {
    try {
      one.getCpuset().setMemHardwall(true);
      boolean flag = one.getCpuset().isMemHardwall();
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set mem_hardwall failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetMemPressure() {
    try {
      int pressure = one.getCpuset().getMemPressure();
      assertTrue(pressure >= 0);
    } catch (IOException e) {
      LOG.error("Get memory_pressure failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetMemPressureEnabled() {
    try {
      root.getCpuset().setMemPressureEnabled(true);
      boolean flag = root.getCpuset().isMemPressureEnabled();
      LOG.error(flag + "");
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set memory_pressure_enabled failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetMemSpreadPage() {
    try {
      one.getCpuset().setMemSpreadPage(true);
      boolean flag = one.getCpuset().isMemSpreadPage();
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set memory_spread_page failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetMemSpreadSlab() {
    try {
      one.getCpuset().setMemSpreadSlab(true);
      boolean flag = one.getCpuset().isMemSpreadSlab();
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set memory_spread_page failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetSchedLoadBlance() {
    try {
      one.getCpuset().setSchedLoadBlance(true);
      boolean flag = one.getCpuset().isSchedLoadBlance();
      assertTrue(flag);
    } catch (IOException e) {
      LOG.error("Set sched_load_balance failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetSchedRelaxDomainLevel() {
    try {
      one.getCpuset().setSchedRelaxDomainLevel(0);
      int level = one.getCpuset().getSchedRelaxDomainLevel();
      assertEquals(level, 0);
    } catch (IOException e) {
      LOG.error("Set sched_relax_domain_level failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testParseNums() {
    String output = "0-1,3";
    int[] actual = Cpuset.parseNums(output);
    int[] excepted = {0, 1, 3};
    assertArrayEquals(actual, excepted);
  }
}
