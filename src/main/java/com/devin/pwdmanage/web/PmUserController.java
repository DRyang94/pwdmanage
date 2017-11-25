package com.devin.pwdmanage.web;


import com.devin.pwdmanage.dto.Result;
import com.devin.pwdmanage.entity.PageBean;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.service.PmMainframeService;
import com.devin.pwdmanage.service.PmUserService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pmusers")
public class PmUserController {

    @Resource
    private PmUserService pmUserService;

    /**
     * 获取用户列表
     * @param page
     * @param rows
     * @param p_user
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       PmUsersForShow p_user, HttpServletResponse response,
                       HttpServletRequest request) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
//        if(p_user.getUserName() != null)
        map.put("userName", StringUtil.formatLike(p_user.getUserName()));
//        if(p_user.getMainframeName() != null)
        map.put("mainframeName", StringUtil.formatLike(p_user.getMainframeName()));
//        if(p_user.getDbName() != null)
        map.put("dbName", StringUtil.formatLike(p_user.getDbName()));
        map.put("ip", p_user.getIp());
        map.put("state", p_user.getState());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
//
        if(request.getHeader("Referer").matches(".*mainframe.*")) {
            map.put("category", "mainframe");
        } else {
            map.put("category", "database");
        }
        List<PmUsersForShow> userList = pmUserService.findUsers(map);
        Long total = pmUserService.getTotalUser(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class,
                new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(userList, config);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 添加用户,同时添加主机/数据库（若主机/数据库不存在）
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody PmUsersForShow user) throws Exception {
        int resultTotal = 0;
        user.setUserID(ColumnGenerator.getUUID());
        resultTotal = pmUserService.addUser(user);
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }

    /**
     * 修改
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Result update(@RequestBody PmUsersForShow user) throws Exception {
        int resultTotal = pmUserService.updateUser(user);
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }

    /**
     * 删除管理员
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable(value = "ids") String ids) throws Exception {
    //同时删除20位以上的用户（超过最大值了）
        if (ids.length() > 20 * 32) {
            return ResultGenerator.genFailResult("ERROR");
        }
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            pmUserService.deleteUser((idsStr[i]));
        }
        return ResultGenerator.genSuccessResult();
    }
}
