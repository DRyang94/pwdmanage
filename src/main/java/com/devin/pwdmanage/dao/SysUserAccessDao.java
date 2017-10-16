package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.entity.SysUserAccess;

import java.util.List;

public interface SysUserAccessDao {
    /**
     * 查询用户可访问的user列表
     *
     * @param user
     * @return
     */
    List<SysUserAccess> getAccessByUser(SysUser user);

}
