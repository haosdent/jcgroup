package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class FreezerTest {

  private static final Logger LOG = LoggerFactory.getLogger(FreezerTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_FREEZER);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_FREEZER);
      two = admin.createGroup("two", Constants.SUBSYS_FREEZER);
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
  public void testSetState() {
    try {
      one.getFreezer().setState(Freezer.STATE_FROZEN);
      assertEquals(one.getFreezer().getState(), Freezer.STATE_FROZEN);
    } catch (IOException e) {
      LOG.error("Set state failed.", e);
      assertTrue(false);
    }
  }
}
