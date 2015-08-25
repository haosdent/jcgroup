package me.haosdent.cgroup.manage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;
import static me.haosdent.cgroup.util.Constants.*;

public class GroupTest {

  private static final Logger LOG = LoggerFactory.getLogger(GroupTest.class);

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testDelete() {
    try {
      Admin admin = new Admin(SUBSYS_CPU);
      Group one = admin.createGroup("one", SUBSYS_CPU);
      one.delete();
      admin.umount();
    } catch (IOException e) {
      LOG.error("Create Admin error.", e);
      assertTrue(false);
    }
  }
}
