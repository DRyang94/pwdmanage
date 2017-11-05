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
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pmusers")
public class MainframeUserController {

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
                       PmUser p_user, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", StringUtil.formatLike(p_user.getUserName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("category", "mainframe");
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

//    /**
//     * 添加或修改管理员
//     *
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ResponseBody
//    public Result save(@RequestBody SysUser user) throws Exception {
//        int resultTotal = 0;
//        String MD5pwd = MD5Util.MD5Encode(user.getPwd(), "UTF-8");
//        user.setPwd(MD5pwd);
//        user.setUserID(ColumnGenerator.getUUID());
//        user.setCreateTime(ColumnGenerator.getTime());
//        resultTotal = pmUserService.addUser(user);
//        if (resultTotal > 0) {
//            return ResultGenerator.genSuccessResult();
//        } else {
//            return ResultGenerator.genFailResult("FAIL");
//        }
//    }
//
//    /**
//     * 修改
//     *
//     * @param user
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "", method = RequestMethod.PUT)
//    @ResponseBody
//    public Result update(@RequestBody SysUser user) throws Exception {
//        String MD5pwd = MD5Util.MD5Encode(user.getPwd(), "UTF-8");
//        user.setPwd(MD5pwd);
//        int resultTotal = sysUserService.updateUser(user);
//        if (resultTotal > 0) {
//            return ResultGenerator.genSuccessResult();
//        } else {
//            return ResultGenerator.genFailResult("FAIL");
//        }
//    }
//
//    /**
//     * 删除管理员
//     *
//     * @param ids
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public Result delete(@PathVariable(value = "ids") String ids) throws Exception {
//    //同时删除20位以上的用户（超过最大值了）
//        if (ids.length() > 20 * 32) {
//            return ResultGenerator.genFailResult("ERROR");
//        }
//        String[] idsStr = ids.split(",");
//        for (int i = 0; i < idsStr.length; i++) {
//            sysUserService.deleteUser((idsStr[i]));
//        }
//        return ResultGenerator.genSuccessResult();
//    }


}
