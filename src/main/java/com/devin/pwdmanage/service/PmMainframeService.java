package com.devin.pwdmanage.service;

import com.devin.pwdmanage.entity.PmMainframe;

import java.util.List;
import java.util.Map;

public interface PmMainframeService
{

    /**
     * 查找主机,参数可为主机id，limit或其他
     * 若为空，返回所有
     *
     * @param map
     * @return
     */
    List<PmMainframe> findMainframe(Map<String, Object> map);

    /**
     *  数量,map可为空，也可以为主机id
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
     * 添加主机
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
