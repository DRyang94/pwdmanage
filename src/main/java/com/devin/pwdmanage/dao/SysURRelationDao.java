package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysURRelation;
import com.devin.pwdmanage.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SysURRelationDao {
    /**
     * 查询用户的角色
     *
     * @param user
     * @return
     */
    SysRole getRoleByUser(SysUser user);

    /**
     * 查询角色的用户
     *
     * @param role
     * @return
     */
    List<SysUser> getUsersByRole(SysRole role);

    /**
     * 新增用户角色关系
     * map里需要包含urID, userID, roleID
     * @param relation
     * @return
     */
    int addRelation(SysURRelation relation);

    /**
     * 删除用户角色关系
     *
     * @param user
     * @return
     */
    int deleteRelation(SysUser user);

    /**
     * 更改用户角色关系
     *
     * @param relation
     * @return
     */
    int updateRelation(SysURRelation relation);
}
