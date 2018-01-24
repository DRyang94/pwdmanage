package com.devin.pwdmanage.service.impl;

import com.devin.pwdmanage.dao.SysURRelationDao;
import com.devin.pwdmanage.dao.SysUserDao;
import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import org.apache.log4j.MDC;


@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysURRelationDao sysURRelationDao;

    public SysUser login(SysUser user, String ip) {
        SysUser result = sysUserDao.login(user);

        return result;
    }

    public SysRole getRoleByUser(SysUser user) {return sysURRelationDao.getRoleByUser(user);}

    public List<SysUser> findUsers(Map<String, Object> map) {
        return sysUserDao.findUsers(map);
    }

    public int updateUser(SysUser user) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if ("admin".equals(user.getUserName())) {
            return 0;
        }
        return sysUserDao.updateUser(user);
    }

    public Long getTotalUser(Map<String, Object> map) {
        return sysUserDao.getTotalUser(map);
    }

    public int addUser(SysUser user) {
        if (user.getUserName() == null || user.getPwd() == null || getTotalUser(null) > 90) {
            return 0;
        }
        return sysUserDao.addUser(user);
    }

    public int deleteUser(String id) {
        //不允许删admin的
        if(id == "882b82d38a3111e7a501c45444fb4cc1") {
            return 0;
        }
        return sysUserDao.deleteUser(id);
    }

}
