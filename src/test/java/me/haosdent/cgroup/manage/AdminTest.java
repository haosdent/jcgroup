package me.haosdent.cgroup.manage;

import static me.haosdent.cgroup.util.Constants.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class AdminTest {

  private static final Logger LOG = LoggerFactory
          .getLogger(AdminTest.class);

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {}

  @Test
  public void testUmount() {
    try {
      Admin admin = new Admin(SUBSYS_CPU);
      admin.umount();
    } catch (IOException e) {
      LOG.error("Create Admin error.", e);
      assertTrue(false);
    }
  }

  @Test
  public void testCreateGroup() {
    try {
      Admin admin = new Admin(SUBSYS_CPU);
      admin.createGroup("one", SUBSYS_CPU);
      admin.umount();
    } catch (IOException e) {
      LOG.error("Create Admin error.", e);
      assertTrue(false);
    }
  }
}
