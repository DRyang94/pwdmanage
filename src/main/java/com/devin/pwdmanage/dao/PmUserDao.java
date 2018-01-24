package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PmUserDao {

    /**
     * 查找用户列表,参数可为用户id，主机id，数据库id
     *
     * @param map
     * @return
     */
    List<PmUser> findUsers(Map<String, Object> map);

    /**
     *  用户数量,map可为空，也可以为具体用户名，主机id，数据库id
     * @param map
     * @return
     */
    Long getTotalUser(Map<String, Object> map);

    /*
     * 实体修改
     *
     * @param user
     * @return
     */
    int updateUser(PmUser user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(PmUser user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUser(String id);
}
