package com.devin.pwdmanage.web;


import com.devin.pwdmanage.dto.Result;
import com.devin.pwdmanage.entity.PageBean;
import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.service.SysUserService;
import com.devin.pwdmanage.util.MD5Util;
import com.devin.pwdmanage.util.ResponseUtil;
import com.devin.pwdmanage.util.ResultGenerator;
import com.devin.pwdmanage.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sysusers")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/cookie", method = RequestMethod.POST)
    @ResponseBody
    public Result login(SysUser user) {
        try {
            String MD5pwd = MD5Util.MD5Encode(user.getPwd(), "UTF-8");
            user.setPwd(MD5pwd);
        } catch (Exception e) {
            user.setPwd("");
        }
        SysUser resultUser = sysUserService.login(user);
        SysRole resultRole = sysUserService.getRoleByUser(resultUser);
        if (resultUser == null) {
            return ResultGenerator.genFailResult("请认真核对账号、密码！");
        } else {
            resultUser.setPwd("PASSWORD");
            Map data = new HashMap();
            data.put("currentUser", resultUser);
            data.put("currentRole", resultRole);
            return ResultGenerator.genSuccessResult(data);
        }
    }

    /**
     * @param page
     * @param rows
     * @param s_user
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       SysUser s_user, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", StringUtil.formatLike(s_user.getUserName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<SysUser> userList = sysUserService.findUsers(map);
        Long total = sysUserService.getTotalUser(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(userList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 添加或修改管理员
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody SysUser user) throws Exception {
        int resultTotal = 0;
        String MD5pwd = MD5Util.MD5Encode(user.getPwd(), "UTF-8");
        user.setPwd(MD5pwd);
        resultTotal = sysUserService.addUser(user);
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
    public Result update(@RequestBody SysUser user) throws Exception {
        String MD5pwd = MD5Util.MD5Encode(user.getPwd(), "UTF-8");
        user.setPwd(MD5pwd);
        int resultTotal = sysUserService.updateUser(user);
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
        if (ids.length() > 20) {
            return ResultGenerator.genFailResult("ERROR");
        }
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            sysUserService.deleteUser((idsStr[i]));
        }
        return ResultGenerator.genSuccessResult();
    }


}
