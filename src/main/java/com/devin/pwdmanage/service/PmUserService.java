package com.devin.pwdmanage.service;

import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.util.PmUsersForShow;

import java.util.List;
import java.util.Map;

public interface PmUserService
{

    /**
     * 查找用户列表，参数可为用户id，主机id，数据库id
     *
     * @param map
     * @return
     */
    List<PmUsersForShow> findUsers(Map<String, Object> map);

    /** 用户数量,map可为空，也可以为具体用户名，主机id，数据库id
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
    int updateUser(PmUsersForShow user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(PmUsersForShow user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUser(String id);

    /**
     * 导入用户
     *
     * @param users
     * @return
     */
    List<Boolean> importUser(List<PmUsersForShow> users);

    /**
     * 导出用户
     *
     * @param idList
     * @return
     */
    List<PmUsersForShow> exportUser(List<String> idList);

    /**
     * 验证用户
     *
     * @param users
     * @return
     */
    int verifyUser(List<PmUsersForShow> users);

}
