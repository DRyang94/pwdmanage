package com.devin.pwdmanage.service.impl;

import com.devin.pwdmanage.dao.PmDatabaseDao;
import com.devin.pwdmanage.dao.PmUserDao;
import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.service.PmDatabaseService;
import com.devin.pwdmanage.service.PmUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PmDatabaseServiceImpl implements PmDatabaseService {

    @Resource
    private PmDatabaseDao pmDatabaseDao;

    public List<PmDatabase> findUsers(Map<String, Object> map) {
        return pmDatabaseDao.findDB(map);
    }

    public List<PmDatabase> findDB(Map<String, Object> map) {
        return pmDatabaseDao.findDB(map);
    }

    public Long getTotalDB(Map<String, Object> map) {
        return pmDatabaseDao.getTotalDB(map);
    }

    public int updateDB(PmDatabase db) {
        return pmDatabaseDao.updateDB(db);
    }

    public int addDB(PmDatabase db) {
        return pmDatabaseDao.addDB(db);
    }

    public int deleteDB(String id) {
        return pmDatabaseDao.deleteDB(id);
    }

}
