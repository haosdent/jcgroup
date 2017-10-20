package me.haosdent.cgroup.manage;

import static me.haosdent.cgroup.util.Constants.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.haosdent.cgroup.util.Shell;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Admin {

  public static final String userConfName = "user_conf";
  public static final String USER_CONF_KEY_NAME = "name";
  public static final String USER_CONF_KEY_PASSWORD = "password";
  public static final String PATH_ROOT = ".";
  private Shell shell;
  private String name;
  private int subsystems;
  private String password;
  private Group rootGroup;
  private List<Group> groupList = new LinkedList<Group>();

  private static final Logger LOG = LoggerFactory.getLogger(Admin.class);

  public Admin(String name, String password, int subsystems) throws IOException {
    init(name, password, subsystems);
  }

  public Admin(int subsystems) throws IOException {
    String content =
        IOUtils.toString(this.getClass().getClassLoader()
            .getResourceAsStream(userConfName));
    JSONObject json = JSON.parseObject(content);
    String name = json.getString(USER_CONF_KEY_NAME);
    String password = json.getString(USER_CONF_KEY_PASSWORD);

    init(name, password, subsystems);
  }

  private void init(String name, String password, int subsystems) throws IOException {
    this.name = name;
    this.password = password;
    this.subsystems = subsystems;
    this.shell = new Shell(this);
    this.rootGroup = new Group(this, PATH_ROOT, subsystems, true);
  }

  public void umount() throws IOException {
    Object[] groups = groupList.toArray();
    for (Object group : groups) {
      ((Group) group).delete();
    }
  }

  public Shell getShell() {
    return shell;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public Group getRootGroup() {
    return rootGroup;
  }

  public List<Group> getGroupList() {
    return groupList;
  }

  public Group createGroup(String name, int subsystems) throws IOException {
    Group group = new Group(this, name, subsystems);
    return group;
  }
}
