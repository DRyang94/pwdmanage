package com.devin.pwdmanage.service.impl;

import com.devin.pwdmanage.dao.PmMainframeDao;
import com.devin.pwdmanage.entity.PmMainframe;
import com.devin.pwdmanage.service.PmMainframeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PmMainframeServiceImpl implements PmMainframeService {

    @Resource
    private PmMainframeDao pmMainframeDao;

    public List<PmMainframe> findMainframe(Map<String, Object> map) {
        return pmMainframeDao.findMainframe(map);
    }

    public Long getTotalMainframe(Map<String, Object> map) {
        return pmMainframeDao.getTotalMainframe(map);
    }

    public int updateMainframe(PmMainframe mf) {
        return pmMainframeDao.updateMainframe(mf);
    }

    public int addMainframe(PmMainframe mf) {
        return pmMainframeDao.addMainframe(mf);
    }

    public int deleteMainframe(String id) {
        return pmMainframeDao.deleteMainframe(id);
    }
    
}
