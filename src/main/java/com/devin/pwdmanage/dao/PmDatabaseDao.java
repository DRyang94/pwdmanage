package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.PmDatabase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PmDatabaseDao {

    /**
     * 查找数据库,参数可为数据库id，limit或其他属性
     * 若为空，返回所有
     *
     * @param map
     * @return
     */
    List<PmDatabase> findDB(Map<String, Object> map);

    /**
     *  数量,map可为空，也可以为数据库id
     * @param map
     * @return
     */
    Long getTotalDB(Map<String, Object> map);

    /**
     * 实体修改
     *
     * @param db
     * @return
     */
    int updateDB(PmDatabase db);

    /**
     * 添加数据库
     *
     * @param db
     * @return
     */
    int addDB(PmDatabase db);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteDB(String id);
}
