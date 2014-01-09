package me.haosdent.cgroup.util;

import me.haosdent.cgroup.manage.Admin;
import me.haosdent.cgroup.subsystem.Cpu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class ShellTest {

  private static final Logger LOG = LoggerFactory.getLogger(ShellTest.class);

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testExecWithPrivilege() {
    try {
      Admin admin = new Admin(Constants.SUBSYS_CPU);
      Shell shell = admin.getShell();
      String output = shell.exec("echo 0", true);
      assertTrue(output.equals("0\n"));
      admin.umount();
    } catch (IOException e) {
      LOG.error("Error", e);
      assertTrue(false);
    }
  }

  @Test
  public void testExec() {
    try {
      Shell shell = new Shell();
      String output = shell.exec("echo 0");
      assertTrue(output.equals("0\n"));
    } catch (IOException e) {
      LOG.error("Error", e);
      assertTrue(false);
    }
  }

  @Test
  public void testFilterCggetOutput() {
    Shell shell = new Shell();

    String str = "one:\ncpu.stat: nr_periods 0\n        nr_throttled 0\n        throttled_time 0\n\n";
    String actual = shell.filterCggetOutput(Cpu.PROP_CPU_STAT, str);
    String excepted = "nr_periods 0\nnr_throttled 0\nthrottled_time 0";
    assertEquals(actual, excepted);

    str = "one:\ncpu.shares: 100\n\n";
    actual = shell.filterCggetOutput(Cpu.PROP_CPU_SHARES, str);
    LOG.error(actual);
    excepted = "100";
    assertEquals(actual, excepted);
  }
}
