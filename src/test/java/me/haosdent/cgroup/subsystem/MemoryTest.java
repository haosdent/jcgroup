package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;


public class MemoryTest {

  private static final Logger LOG = LoggerFactory.getLogger(MemoryTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_MEMORY);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_MEMORY);
      two = admin.createGroup("two", Constants.SUBSYS_MEMORY);
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
  public void testGetStat() {
    try {
      one.getMemory().getPhysicalUsage();
    } catch (IOException e) {
      LOG.error("Set usage_in_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetPhysicalUsage() {
    try {
      one.getMemory().getPhysicalUsage();
    } catch (IOException e) {
      LOG.error("Set usage_in_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetWithSwapUsage() {
    try {
      one.getMemory().getWithSwapUsage();
    } catch (IOException e) {
      LOG.error("Set memsw.usage_in_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetMaxPhysicalUsage() {
    try {
      one.getMemory().getMaxPhysicalUsage();
    } catch (IOException e) {
      LOG.error("Set max_usage_in_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetMaxWithSwapUsage() {
    try {
      one.getMemory().getMaxWithSwapUsage();
    } catch (IOException e) {
      LOG.error("Set memsw.max_usage_in_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetPhysicalUsageLimit() {
    try {
      long excepted = 200704l;
      one.getMemory().setPhysicalUsageLimit(excepted);
      long actual = one.getMemory().getPhysicalUsageLimit();
      assertEquals(excepted, actual);
    } catch (IOException e) {
      LOG.error("Set limit_in_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetWithSwapUsageLimit() {
    try {
      long excepted = 200704l;
      one.getMemory().setWithSwapUsageLimit(excepted);
      long actual = one.getMemory().getWithSwapUsageLimit();
      assertEquals(excepted, actual);
    } catch (IOException e) {
      LOG.error("Set memsw.limit_in_bytes failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetPhysicalFailCount() {
    try {
      one.getMemory().getPhysicalFailCount();
    } catch (IOException e) {
      LOG.error("Set failcnt failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testGetWithSwapFailCount() {
    try {
      one.getMemory().getWithSwapFailCount();
    } catch (IOException e) {
      LOG.error("Set memsw.failcnt failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testClearForceEmpty() {
    try {
      one.getMemory().clearForceEmpty();
    } catch (IOException e) {
      LOG.error("Clear force_empty failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetSwappiness() {
    try {
      int excepted = 60;
      one.getMemory().setSwappiness(excepted);
      int actual = one.getMemory().getSwappiness();
      assertEquals(excepted, actual);
    } catch (IOException e) {
      LOG.error("Set swappiness failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetUseHierarchy() {
    try {
      one.getMemory().setUseHierarchy(true);
      assertTrue(one.getMemory().isUseHierarchy());
    } catch (IOException e) {
      LOG.error("Set use_hierarchy failed.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testSetOomControl() {
    try {
      one.getMemory().setOomControl(true);
      assertTrue(one.getMemory().isOomControl());
    } catch (IOException e) {
      LOG.error("Set oom_control failed.", e);
      assertTrue(false);
    }
  }
}
