package com.devin.pwdmanage.web;


import com.devin.pwdmanage.dto.Result;
import com.devin.pwdmanage.entity.PageBean;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.service.PmUserService;
import com.devin.pwdmanage.util.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


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
        map.put("userName", StringUtil.formatLike(p_user.getUserName()));
        map.put("mainframeName", StringUtil.formatLike(p_user.getMainframeName()));
        map.put("dbName", StringUtil.formatLike(p_user.getDbName()));
        map.put("ip", p_user.getIp());
        map.put("state", p_user.getState());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        if(request.getHeader("Referer").matches(".*mainframe.*")) {
            map.put("category", "mainframe");
        } else {
            map.put("category", "database");
        }
        List<PmUsersForShow> userList = pmUserService.findUsers(map);
        Long total = pmUserService.getTotalUser(map);
        for(PmUsersForShow user: userList) {
            user.setPwd(user.getPwd().charAt(0) + "*****");
        }
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
    public Result delete(@PathVariable(value = "ids") String ids, HttpServletRequest request) throws Exception {
        String category;
        if(request.getHeader("Referer").matches(".*mainframe.*")) {
            category = new String("mainframe");
        } else {
            category = new String("database");
        }
    //同时删除20位以上的用户（超过最大值了）
        if (ids.length() > 20 * 32) {
            return ResultGenerator.genFailResult("ERROR");
        }
        int result = 0;
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            result += pmUserService.deleteUser((idsStr[i]), category);
        }
        if(result > 0) {
            return ResultGenerator.genSuccessResult();
        }
        else{
            return ResultGenerator.genFailResult("fail");
        }
    }

    /**
     * 验证
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/verify/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Result verify(@PathVariable(value = "ids") String ids,HttpServletRequest request) throws Exception {
        String category;
        if(request.getHeader("Referer").matches(".*mainframe.*")) {
            category = "mainframe";
        } else {
            category = "database";
        }
        int result = 0;
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            result += pmUserService.verifyUser((idsStr[i]), category);
        }
        if(result > 0) {
            return ResultGenerator.genSuccessResult();
        }
        else{
            return ResultGenerator.genFailResult("fail");
        }
    }

    /**
     * 上传用户列表
     * @param uploadExcel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result importUser(@RequestParam("file") CommonsMultipartFile uploadExcel,
                             HttpServletRequest request) throws Exception {
        InputStream fileContent;
        fileContent = uploadExcel.getInputStream();
        Workbook workbook = Workbook.getWorkbook(fileContent);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        int columns = sheet.getColumns();
        if(rows < 2 || columns != 9) {
            return ResultGenerator.genFailResult("Wrong File");
        }
        List<PmUsersForShow> userList = new ArrayList<PmUsersForShow>();
        //line 0 has not data
        for(int i = 1; i < rows;i++) {
            PmUsersForShow userForShow = new PmUsersForShow(
                    ColumnGenerator.getUUID(),
                    sheet.getCell(3, i).getContents(), sheet.getCell(4, i).getContents(),
                    sheet.getCell(5, i).getContents(), null, null,
                    null, null, null, null, null,
                    sheet.getCell(1, i).getContents(), Integer.valueOf(sheet.getCell(2, i).getContents()),
                    sheet.getCell(7, i).getContents(), sheet.getCell(8, i).getContents()
            );
            if(request.getHeader("Referer").matches(".*mainframe.*")) {
                userForShow.setMainframeName(sheet.getCell(6, i).getContents());
                userForShow.setSystemInfo(sheet.getCell(0, i).getContents());
            } else {
                userForShow.setDbName(sheet.getCell(6, i).getContents());
                userForShow.setDbInfo(sheet.getCell(0, i).getContents());
            }
            userList.add(userForShow);
       }
        workbook.close();
        fileContent.close();
        List<Boolean> resultList = pmUserService.importUser(userList);
        if(resultList.contains(true)){
            //根据这个结果列表来提示哪个出问题
            JSONArray jsonArray = JSONArray.fromObject(resultList);
            Result result = ResultGenerator.genSuccessResult(jsonArray);
            return result;
        }
        return ResultGenerator.genFailResult("No one was added");
    }

    /**
     * 下载导入需要的模板
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadTem(HttpServletRequest request) throws IOException {
        File file = new File(request.getServletContext().getRealPath("/") +
                "resources/files/template1.xls");
        byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        is.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }

    /**
     * 导出
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/export/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> exportUser(@PathVariable(value = "ids") String ids,
                                             HttpServletRequest request) throws Exception {
        List<String> idList = Arrays.asList( ids.split(","));
        List<PmUsersForShow> userList = pmUserService.exportUser((idList));
        File templateFile;
        File exportFile = new File(request.getServletContext().getRealPath("/") +
                "resources/files/export.xls");
        int flag;
        if(request.getHeader("Referer").matches(".*mainframe.*")) {
            templateFile = new File(request.getServletContext().getRealPath("/") +
                    "resources/files/template1.xls");
            flag = 0;
        } else {
            templateFile = new File(request.getServletContext().getRealPath("/") +
                    "resources/files/template2.xls");
            flag = 1;
        }
        FileUtil.CopyByChannel(templateFile, exportFile);
        InputStream templateInput = new FileInputStream(exportFile);
        Workbook book = Workbook.getWorkbook(templateInput);
        WritableWorkbook workbook = Workbook.createWorkbook(exportFile, book);
        book.close();
        WritableSheet sheet = workbook.getSheet(0);
        for(int i = 1; i < userList.size() + 1; i++){
            int j = i - 1;
            sheet.addCell(new Label(1,i,userList.get(j).getIp()));
            sheet.addCell(new Label(2,i,String.valueOf(userList.get(j).getPort())));
            sheet.addCell(new Label(3,i,userList.get(j).getUserName()));
            sheet.addCell(new Label(4,i,userList.get(j).getPwd()));
            sheet.addCell(new Label(5,i,userList.get(j).getRemark()));
            sheet.addCell(new Label(7,i,userList.get(j).getRootName()));
            sheet.addCell(new Label(8,i,userList.get(j).getRootPwd()));
            if(flag == 0) {
                sheet.addCell(new Label(0,i,userList.get(j).getSystemInfo()));
                sheet.addCell(new Label(6,i,userList.get(j).getMainframeName()));
            } else {
                sheet.addCell(new Label(0,i,userList.get(j).getDbInfo()));
                sheet.addCell(new Label(6,i,userList.get(j).getDbName()));
            }
        }
        workbook.write();
        workbook.close();
        InputStream fileInput = new FileInputStream(exportFile);
        byte[] body = null;
        body = new byte[fileInput.available()];
        fileInput.read(body);
        fileInput.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + exportFile.getName());
        exportFile.delete();
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }
}
