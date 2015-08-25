package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

public class NetPrioTest {

  private static final Logger LOG = LoggerFactory.getLogger(NetPrioTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_NET_PRIO);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_NET_PRIO);
      two = admin.createGroup("two", Constants.SUBSYS_NET_PRIO);
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
  public void testGetPrioId() {
    try {
      assertNotNull(one.getNetPrio().getPrioId());
    } catch (IOException e) {
      LOG.error("Get prioidx failed.", e);
    }
  }

  @Test
  public void testAddIfPrioMap() {
    try {
      one.getNetPrio().addIfPrioMap("eth0", 2);
      Map ifPrioMap = one.getNetPrio().getIfPrioMap();
      LOG.error(ifPrioMap + "");
      assertEquals(ifPrioMap.get("eth0"), 2);
    } catch (IOException e) {
      LOG.error("Add ifpriomap failed.", e);
      assertTrue(false);
    }
  }
}
