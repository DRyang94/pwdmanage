package com.devin.pwdmanage.service;

import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    SysUser login(SysUser user, String ip);

    /**
     * 查询用户角色
     *
     * @param user
     * @return
     */
    SysRole getRoleByUser(SysUser user);

    /**
     * 查找用户列表
     *
     * @param map
     * @return
     */
    List<SysUser> findUsers(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    Long getTotalUser(Map<String, Object> map);

    /**
     * 实体修改
     *
     * @param user
     * @return
     */
    int updateUser(SysUser user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(SysUser user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUser(String id);
}
