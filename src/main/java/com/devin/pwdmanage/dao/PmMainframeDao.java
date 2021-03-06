package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.PmMainframe;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PmMainframeDao {

    /**
     * 查找主机,参数可为数据库id，limit或其他属性
     * 若为空，返回所有
     *
     * @param map
     * @return
     */
    List<PmMainframe> findMainframe(Map<String, Object> map);

    /**
     *  数量,map可为空，也可以为数据库id
     * @param map
     * @return
     */
    Long getTotalMainframe(Map<String, Object> map);

    /**
     * 实体修改
     *
     * @param mainframe
     * @return
     */
    int updateMainframe(PmMainframe mainframe);

    /**
     * 添加数据库
     *
     * @param mainframe
     * @return
     */
    int addMainframe(PmMainframe mainframe);

    /**
     * 删除主机
     *
     * @param id
     * @return
     */
    int deleteMainframe(String id);




}
