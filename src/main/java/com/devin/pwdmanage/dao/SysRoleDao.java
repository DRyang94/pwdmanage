package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.SysRole;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleDao {
    /**
     * 得到角色详细信息
     *
     * @param name
     * @return
     */
    SysRole getRole(String name);


}
