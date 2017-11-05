package com.devin.pwdmanage.service.impl;

import com.devin.pwdmanage.dao.PmDatabaseDao;
import com.devin.pwdmanage.dao.PmMainframeDao;
import com.devin.pwdmanage.dao.PmUserDao;
import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.service.PmUserService;
import com.devin.pwdmanage.util.PmUsersForShow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
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
        if(map.get("category").equals("mainframe")) {
            for(PmUser user: userList) {
                param.put("mainframeID", user.getMainframeID());
                showList.add(new PmUsersForShow(user, null, pmMainframeDao.findMainframe(param).get(0)));
                param.clear();
            }
        } else {
            for(PmUser user: userList) {
                param.put("dbID", user.getDbID());
                showList.add(new PmUsersForShow(user, pmDatabaseDao.findDB(param).get(0), null));
                param.clear();
            }
        }
        return showList;
    }

    public int updateUser(PmUser user) {
        //防止有人胡乱修改导致其他人无法正常登陆
        return pmUserDao.updateUser(user);
    }

    public Long getTotalUser(Map<String, Object> map) {
        return pmUserDao.getTotalUser(map);
    }

    public int addUser(PmUser user) {
        if (user.getUserName() == null || user.getPwd() == null || getTotalUser(null) > 90) {
            return 0;
        }
        return pmUserDao.addUser(user);
    }

    public int deleteUser(String id) {
        return pmUserDao.deleteUser(id);
    }

}
