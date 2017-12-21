package com.devin.pwdmanage.web;


import com.devin.pwdmanage.dao.Log_opDao;
import com.devin.pwdmanage.dto.Result;
import com.devin.pwdmanage.entity.LogOp;
import com.devin.pwdmanage.entity.PageBean;
import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.service.SysUserService;
import com.devin.pwdmanage.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/log")
public class LogController {

    @Resource
    private Log_opDao log_opDao;

    /**
     * 获取列表
     * @param page
     * @param rows
     * @param userID
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       String userID, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userID", userID);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<LogOp> logList = log_opDao.findLogs(map);
        Long total = log_opDao.getTotalLog(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class,
                new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(logList, config);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable(value = "ids") String ids) throws Exception {

        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            log_opDao.deleteLog((idsStr[i]));
        }
        return ResultGenerator.genSuccessResult();
    }


}
