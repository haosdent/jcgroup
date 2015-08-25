package me.haosdent.cgroup;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;
import me.haosdent.cgroup.util.Threads;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.io.IOException;

public class ExampleTest {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleTest.class);
  private static Admin admin;
  private static Group root;
  private static Group one;
  private static Group two;

  @BeforeClass
  public static void setUpClass() {
    try {
      admin = new Admin(Constants.SUBSYS_CPUSET | Constants.SUBSYS_CPU);
      root = admin.getRootGroup();
      one = admin.createGroup("one", Constants.SUBSYS_CPUSET | Constants.SUBSYS_CPU);
      two = admin.createGroup("two", Constants.SUBSYS_CPUSET | Constants.SUBSYS_CPU);
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

  @Test
  public void testCpu() {
    try {
      one.getCpuset().setCpus(new int[]{0});
      two.getCpuset().setCpus(new int[]{0});
      one.getCpuset().setMems(new int[]{0});
      two.getCpuset().setMems(new int[]{0});
      one.getCpu().setShares(512);
      two.getCpu().setShares(2048);
      final Group oneTmp = one;
      final Group twoTmp = two;
      new Thread(){
        @Override
        public void run() {
          int id = Threads.getThreadId();
          LOG.info("Thread id:" + id);
          try {
            oneTmp.getCpu().addTask(id);
            while (true);
          } catch (IOException e) {
            LOG.error("Test cpu failed.", e);
            assertTrue(false);
          }
        }
      }.start();
      new Thread(){
        @Override
        public void run() {
          int id = Threads.getThreadId();
          LOG.info("Thread id:" + id);
          try {
            twoTmp.getCpu().addTask(id);
            while (true);
          } catch (IOException e) {
            LOG.error("Test cpu failed.", e);
            assertTrue(false);
          }
        }
      }.start();
      Thread.sleep(60000l);
    } catch (Exception e) {
      LOG.error("Test cpu failed.", e);
      assertTrue(false);
    }
  }
}
