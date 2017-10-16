package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.SysUser;
import java.util.List;
import java.util.Map;

public interface SysUserDao {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    SysUser login(SysUser user);

    /**
     * 查找用户列表
     * map中参数：userName，start, size
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
