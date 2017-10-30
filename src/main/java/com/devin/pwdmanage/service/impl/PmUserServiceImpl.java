package com.devin.pwdmanage.service.impl;

import com.devin.pwdmanage.dao.PmUserDao;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.service.PmUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PmUserServiceImpl implements PmUserService {

    @Resource
    private PmUserDao pmUserDao;

    public List<PmUser> findUsers(Map<String, Object> map) {
        return pmUserDao.findUsers(map);
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
