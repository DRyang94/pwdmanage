package com.devin.pwdmanage.service.impl;

import com.devin.pwdmanage.dao.PmDatabaseDao;
import com.devin.pwdmanage.dao.PmMainframeDao;
import com.devin.pwdmanage.dao.PmUserDao;
import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmMainframe;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.service.PmUserService;
import com.devin.pwdmanage.util.ColumnGenerator;
import com.devin.pwdmanage.util.DatabaseHelper;
import com.devin.pwdmanage.util.MainframeHelper;
import com.devin.pwdmanage.util.PmUsersForShow;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PmUserServiceImpl implements PmUserService {

    @Resource
    private PmUserDao pmUserDao;
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private PmMainframeDao pmMainframeDao;

    @Resource
    private PmDatabaseDao pmDatabaseDao;

    public List<PmUsersForShow> findUsers(Map<String, Object> map) {
        List<PmUsersForShow> showList = new ArrayList<PmUsersForShow>();
        List<PmUser> userList = pmUserDao.findUsers(map);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ip", map.get("ip"));
        param.put("state", map.get("state"));
        if(map.get("category").equals("mainframe")) {
            for(PmUser user: userList) {
                if(user.getMainframeID()!=null) {
                    param.put("mainframeID", user.getMainframeID());
                    param.put("mainframeName", map.get("mainframeName"));
//                    user.setPwd(user.getPwd().charAt(0) + "******");
                    List<PmMainframe> mf = pmMainframeDao.findMainframe(param);
                    if(mf.size() > 0) {
                        showList.add(new PmUsersForShow(user, null, mf.get(0)));
                    }
                    param.remove("mainframeID");
                    param.remove("mainframeName");
                }
            }
        } else {
            for(PmUser user: userList) {
                if(user.getDbID()!=null) {
                    param.put("dbID", user.getDbID());
                    param.put("dbName", map.get("dbName"));
//                    user.setPwd(user.getPwd().charAt(0) + "******");
                    List<PmDatabase> db = pmDatabaseDao.findDB(param);
                    if(db.size() > 0) {
                        showList.add(new PmUsersForShow(user, db.get(0), null));
                    }
                    param.remove("dbID");
                    param.remove("dbName");
                }
            }
        }
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "getAll");
        logger.info("");
        return showList;
    }

    public int updateUser(PmUsersForShow user) {
        //跟add操作差不多，要先看输入的主机或者数据库信息是否有效
        if (user.getUserID() == null ) {
            return 0;
        }
        PmUser pmUser = checkMfDBExist(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userID", pmUser.getUserID());
        if(pmUser.getMainframeID() != null) {
            map.put("category", "mainframe");
        }else {
            map.put("category", "database");
        }
        List<PmUsersForShow> userList = findUsers(map);
        if(userList.size() == 0) {
            return 0;
        }
        if(pmUser.getPwd() == null) {
            pmUser.setPwd(userList.get(0).getPwd());
            user.setPwd(userList.get(0).getPwd());
        }
        int result = 0;
        String oldName = null;
        if(user.getUserName() != userList.get(0).getUserName()) {
            oldName = userList.get(0).getUserName();
        }
        if(user.getMainframeID() != null) {
            //如果只是状态改变，不需要连接到主机
            if (!(userList.get(0).getState() != null && (userList.get(0).getState().equals(user.getState()))) ||
                    MainframeHelper.updateUser(user, oldName)) {
                result = pmUserDao.updateUser(pmUser);
            }
        }
        else {
            //如果只是状态改变，不需要连接到主机
            if (!(userList.get(0).getState() != null && (userList.get(0).getState().equals(user.getState()))) ||
                    DatabaseHelper.updateUser(user, oldName) != -1) {
                result = pmUserDao.updateUser(pmUser);
            }
        }
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "update " + user.getUserName());
        logger.info("");
        return result;
    }

    public Long getTotalUser(Map<String, Object> map) {
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "get the num of users ");
        logger.info("");
        return pmUserDao.getTotalUser(map);
    }

    public int addUser(PmUsersForShow user) {
        if (user.getUserName() == null || user.getPwd() == null) {
            return 0;
        }
        PmUser pmUser = checkMfDBExist(user);
        int result = 0;
        if(user.getMainframeID() != null) {
            if(MainframeHelper.addUser(user)) {
                result = pmUserDao.addUser(pmUser);
            }
        }
        else {
            if (DatabaseHelper.addUser(user) != -1) {
                result = pmUserDao.addUser(pmUser);
            }
        }
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "add " + user.getUserName());
        logger.info("");
        return result;
    }

    public int deleteUser(String id, String category) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userID", id);
        map.put("category", category);
        List<PmUsersForShow> userList = findUsers(map);
        if (userList.size() == 0) {
            return 0;
        }
        int result = 0;
        if(userList.get(0).getMainframeID() != null) {
            if (MainframeHelper.deleteUser(userList.get(0))) {
                result = pmUserDao.deleteUser(id);
            }
        }
        else {
            if (DatabaseHelper.deleteUser(userList.get(0)) != -1) {
                result = pmUserDao.deleteUser(id);
            }
        }
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "delete " + id);
        logger.info("");
        return result;
    }

    public List<Boolean> importUser(List<PmUsersForShow> users) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for(int i = 0; i < users.size(); i++) {
            if(addUser(users.get(i)) == 0){
                resultList.add(false);
            }
            resultList.add(true);
        }
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "import by excel" );
        logger.info("");
        return resultList;
    }

    public List<PmUsersForShow> exportUser(List<String> idList) {
        List<PmUser> userList = new ArrayList<PmUser>();
        HashMap<String, Object> param = new HashMap<String, Object>();
        for (String id: idList) {
            param.put("userID", id);
            userList.addAll(pmUserDao.findUsers(param));
            param.clear();
        }
        if(userList.size() == 0) return null;
        List<PmUsersForShow> showList = new ArrayList<PmUsersForShow>();
        if(userList.get(0).getMainframeID() != null) {
            for(PmUser user: userList) {
                param.put("mainframeID", user.getMainframeID());
                List<PmMainframe> mf = pmMainframeDao.findMainframe(param);
                if(mf.size() > 0) {
                    showList.add(new PmUsersForShow(user, null, mf.get(0)));
                }
                param.remove("mainframeID");
            }
        } else {
            for(PmUser user: userList) {
                    param.put("dbID", user.getDbID());
                    List<PmDatabase> db = pmDatabaseDao.findDB(param);
                    if(db.size() > 0) {
                        showList.add(new PmUsersForShow(user, db.get(0), null));
                    }
                    param.remove("dbID");
            }
        }
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "export to excel ");
        logger.info("");
        return showList;
    }

    public int verifyUser(String id, String category) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userID", id);
        map.put("category", category);
        List<PmUsersForShow> userList = findUsers(map);
        if (userList.size() == 0) {
            return 0;
        }
        if(userList.get(0).getMainframeID() != null) {
            if (MainframeHelper.verifyUser(userList.get(0))) {
                userList.get(0).setState("正常");
            } else {
                userList.get(0).setState("已过期");
            }
        } else {
            if (DatabaseHelper.verifyUser(userList.get(0))) {
                userList.get(0).setState("正常");
            } else {
                userList.get(0).setState("已过期");
            }

        }
        updateUser(userList.get(0));
        MDC.put("userId", "882b82d38a3111e7a501c45444fb4cc1");
        MDC.put("operation", "verify " + id);
        logger.info("");
        return 1;
    }

    //处理查询主机和数据库是否存在，并把PmUser分离出
    private PmUser checkMfDBExist(PmUsersForShow user) {
        //传进来的PmUserForShow的dbID和mainframeID都是为空，要先判断是否存在
        //不存在的话，还要先生成新的id
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ip", user.getIp());
        param.put("port", user.getPort());
        param.put("rootName", user.getRootName());
        param.put("rootPwd", user.getRootPwd());
        if(user.getMainframeName() != null ) {
            param.put("mainframeName", user.getMainframeName());
            param.put("systemInfo", user.getSystemInfo());
            List<PmMainframe> mfList = pmMainframeDao.findMainframe(param);
            if(mfList.size() > 0) {
                user.setMainframeID(mfList.get(0).getMainframeID());
                user.setRootName(mfList.get(0).getRootName());
                user.setRootPwd(mfList.get(0).getRootPwd());
            } else {
                user.setMainframeID(ColumnGenerator.getUUID());
                PmMainframe newMf = new PmMainframe(user.getMainframeID(),
                        user.getMainframeName(), user.getSystemInfo()
                        , user.getIp(),
                        user.getPort(), user.getRootName(), user.getRootPwd());
                if(pmMainframeDao.addMainframe(newMf) < 0)
                    return null;
            }
        } else {
            param.put("dbName", user.getDbName());
            param.put("dbInfo", user.getDbInfo());
            List<PmDatabase> dbList = pmDatabaseDao.findDB(param);
            if(dbList.size() > 0) {
                user.setDbID(dbList.get(0).getDbID());
                user.setRootName(dbList.get(0).getRootName());
                user.setRootPwd(dbList.get(0).getRootPwd());
            } else {
                user.setDbID(ColumnGenerator.getUUID());
                PmDatabase newDb = new PmDatabase(user.getDbID(),
                        user.getDbName(), user.getDbInfo(),
                        user.getIp(),
                        user.getPort(), user.getRootName(), user.getRootPwd());
                if(pmDatabaseDao.addDB(newDb) < 0)
                    return null;
            }
        }
        PmUser pmUser = new PmUser(user.getUserID(), user.getUserName(),
                user.getPwd(), user.getMainframeID(), user.getDbID(),
                user.getRemark(), user.getState());
        return pmUser;
    }

}
