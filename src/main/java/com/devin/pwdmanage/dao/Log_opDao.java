package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.LogOp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Log_opDao {

    /**
     * map中参数：userId，start, size
     * @param map
     * @return
     */
    List<LogOp> findLogs(Map<String, Object> map);

    /**
     *
     * @param id
     * @return
     */
    int deleteLog(String id);

    /**
     * @param map
     * @return
     */
    Long getTotalLog(Map<String, Object> map);
}
