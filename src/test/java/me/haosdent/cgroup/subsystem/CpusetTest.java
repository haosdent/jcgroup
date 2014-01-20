package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;


public class CpusetTest {

  private static final Logger LOG = LoggerFactory.getLogger(CpuacctTest.class);
  private static Admin admin;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      Admin admin = new Admin(Constants.SUBSYS_CPUSET);
      Group one = admin.createGroup("one", Constants.SUBSYS_CPUSET);
      Group two = admin.createGroup("two", Constants.SUBSYS_CPUSET);
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
      int[] actual = {0,1};
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

  }

  @Test
  public void testSetCpuExclusive() {}

  @Test
  public void testSetMemExclusive() {}

  @Test
  public void testSetMemHardwall() {}

  @Test
  public void testGetMemPressure() {}

  @Test
  public void testSetMemPressureEnabled() {}

  @Test
  public void testSetMemSpreadPage() {}

  @Test
  public void testSetMemSpreadSlab() {}

  @Test
  public void testSetSchedLoadBlance() {}

  @Test
  public void testSetSchedRelaxDomainLevel() {}
}
