#jcgroup

[![Build Status](https://travis-ci.org/haosdent/jcgroup.png?branch=master)](https://travis-ci.org/haosdent/jcgroup) [![Coverage Status](https://coveralls.io/repos/haosdent/jcgroup/badge.png?branch=master)](https://coveralls.io/r/haosdent/jcgroup?branch=master)

jcgroup is a cgroup wrapper on JVM. You could use this library to limit the CPU shares, Disk I/O speed, Network bandwidth and etc of a thread.

## Subsystems

* ✓ blkio
* ✓ common
* ✓ cpu
* ✓ cpuacct
* ✓ cpuset
* ✓ devices
* ✓ freezer
* ✓ memory
* ✓ net_cls
* ✓ net_prio

## Example

>This code snippet create two threads and set different cpu shares of them. One is 512 while another is 2048.

![jcgroup_example_cpu][1]

```java
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
```



## Requirements

* Linux version (>= 2.6.18)


  [1]: https://lh3.googleusercontent.com/gt4aAPISzym6ayNSCI1DAJppgxUwibPOSbp3sRvdhlA=s600 "jcgroup_example_cpu"