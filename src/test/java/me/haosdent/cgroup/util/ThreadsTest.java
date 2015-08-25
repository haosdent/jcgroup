package me.haosdent.cgroup.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class ThreadsTest {

  private static final Logger LOG = LoggerFactory.getLogger(ThreadsTest.class);

  @Test
  public void testGetThreadId() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        int id = Threads.getThreadId();
        LOG.info("Thread Id: " + id);
        assertTrue(id != 0);
      }
    };
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      LOG.error("Thread Error.", e);
      assertTrue(false);
    }
  }
}
