package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.io.IOException;

public class NetClsTest {

  private static final Logger LOG = LoggerFactory.getLogger(NetClsTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_NET_CLS);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_NET_CLS);
      two = admin.createGroup("two", Constants.SUBSYS_NET_CLS);
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
  public void testSetClassId() {
    try {
      int[] excepted = {10, 1};
      one.getNetCls().setClassId(excepted[0], excepted[1]);
      int[] actual = one.getNetCls().getClassId();
      assertArrayEquals(excepted, actual);
    } catch (IOException e) {
      LOG.error("Set classid failed.", e);
      assertTrue(false);
    }
  }
}
