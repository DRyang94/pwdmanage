package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.SysFunction;
import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysRFRelationDao {
    /**
     * 查询角色可访问的功能
     *
     * @param role
     * @return
     */
    List<SysFunction> getFunByRole(SysRole role);

}
