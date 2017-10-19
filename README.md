# jcgroup

[![Build Status](https://travis-ci.org/haosdent/jcgroup.png?branch=master)](https://travis-ci.org/haosdent/jcgroup) [![Coverage Status](https://coveralls.io/repos/haosdent/jcgroup/badge.png?branch=master)](https://coveralls.io/r/haosdent/jcgroup?branch=master)

jcgroup is a cgroup wrapper on JVM. You could use this library to limit the CPU shares, Disk I/O speed, Network bandwidth and etc of a thread.

## Subsystems

☑ blkio<br/>
☑ common<br/>
☑ cpu<br/>
☑ cpuacct<br/>
☑ cpuset<br/>
☑ devices<br/>
☑ freezer<br/>
☑ memory<br/>
☑ net_cls<br/>
☑ net_prio<br/>


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
* cgroups management tools. In Ubuntu or Debian, you may install the tools using:
``sudo apt-get install cgroup-bin``     

  [1]: https://raw.github.com/haosdent/jcgroup/master/img/jcgroup_example_cpu.jpg

## License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

(http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License. See accompanying LICENSE file.
