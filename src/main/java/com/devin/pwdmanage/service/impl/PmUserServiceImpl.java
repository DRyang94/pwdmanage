package com.devin.pwdmanage.service.impl;

import com.devin.pwdmanage.dao.PmDatabaseDao;
import com.devin.pwdmanage.dao.PmMainframeDao;
import com.devin.pwdmanage.dao.PmUserDao;
import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmMainframe;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.service.PmUserService;
import com.devin.pwdmanage.util.ColumnGenerator;
import com.devin.pwdmanage.util.PmUsersForShow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PmUserServiceImpl implements PmUserService {

    @Resource
    private PmUserDao pmUserDao;

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
                    user.setPwd(user.getPwd().charAt(0) + "******");
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
                    user.setPwd(user.getPwd().charAt(0) + "******");
                    List<PmDatabase> db = pmDatabaseDao.findDB(param);
                    if(db.size() > 0) {
                        showList.add(new PmUsersForShow(user, db.get(0), null));
                    }
                    param.remove("dbID");
                    param.remove("dbName");
                }
            }
        }
        return showList;
    }

    public int updateUser(PmUsersForShow user) {
        //跟add操作差不多，要先看输入的主机或者数据库信息是否有效
        if (user.getUserName() == null || user.getPwd() == null) {
            return 0;
        }
        PmUser pmUser = checkMfDBExist(user);
        return pmUserDao.updateUser(pmUser);
    }

    public Long getTotalUser(Map<String, Object> map) {
        return pmUserDao.getTotalUser(map);
    }

    public int addUser(PmUsersForShow user) {
        if (user.getUserName() == null || user.getPwd() == null) {
            return 0;
        }
        PmUser pmUser = checkMfDBExist(user);
        return pmUserDao.addUser(pmUser);
    }

    public int deleteUser(String id) {
        return pmUserDao.deleteUser(id);
    }

    public List<Boolean> importUser(List<PmUsersForShow> users) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for(int i = 0; i < users.size(); i++) {
            if(addUser(users.get(i)) == 0){
                resultList.add(false);
            }
            resultList.add(true);
        }
        return resultList;
    }

    public int verifyUser(List<PmUsersForShow> users) {
        if(users.get(0).getMainframeID() != null) {

        } else {


        }
        return 0;
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
