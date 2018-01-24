<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url = "/pwdmanage/pmusers";
        var method;

        function searchUser() {
            $("#dg").datagrid('load', {
                userName: $("#s_userName").val(),
                dbName: $("#s_dbName").val(),
                ip: $("#s_ip").val(),
                state: $("#s_state").val()
            });
        }

        function deleteUser() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].userID);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确认要删除这<font color=red>"
                + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.ajax({
                        type: "DELETE",//方法类型
                        dataType: "json",//预期服务器返回的数据类型
                        url: "/pwdmanage/pmusers/" + ids,//url
                        data: {},
                        success: function (result) {
                            console.log(result);//打印服务端返回的数据
                            if (result.resultCode == 200) {
                                $.messager.alert(
                                    "系统提示",
                                    "数据已成功删除！");
                                $("#dg").datagrid(
                                    "reload");
                            }
                            else {
                                $.messager.alert(
                                    "系统提示",
                                    "数据删除失败！");
                            }

                            ;
                        },
                        error: function () {
                            $.messager.alert("ERROR！");
                        }
                    });
                }
            });

        }

        function openUserAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加用户信息");
            method = "POST";
        }

        function saveUser() {
            var userName = $("#userName").val();
            var password = $("#password").val();
            var remark = $("#remark").val();
            var userID = $("#userId").val();
            var dbInfo = $("#dbInfo").val();
            var dbName = $("#dbName").val();
            var ip = $("#ip").val();
            var port = $("#port").val();
            var dbID = $("#dbID").val();
            var rootName = $("#rootName").val();
            var rootPwd = $("#rootPwd").val();

            if(password == '******') {
                password = null;
            }
            var data = {"userID": userID, "userName": userName,
                "pwd": password, "remark": remark, "state": null,
                "dbID": dbID, "dbName": dbName,
                "dbInfo": dbInfo, "ip": ip, "port": port,
                "rootName": rootName, "rootPwd": rootPwd,
                "mainframeID":null, "mainframeName":null, "mainframeInfo":null }
            $.ajax({
                type: method,//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: url,//url
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据
                    if (result.resultCode == 200) {
                        $.messager.alert("系统提示", "保存成功");
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                        resetValue();
                    }
                    else {
                        $.messager.alert("系统提示", "操作失败");
                        $("#dlg").dialog("close");
                        resetValue();
                    }
                    ;
                },
                error: function (result) {
                    console.log(arguments[0]);
                    console.log(result);
                    $.messager.alert("系统提示", "操作失败");
                }
            });
        }

        function openUserModifyDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            console.log(row);
            $("#dlg").dialog("open").dialog("setTitle", "编辑用户信息");
            $('#fm').form('load', row);
            $("#password").val("******");
            $("#userId").val(row.userID);
            $("#remark").val(row.remark);
            $("#rootName").hide();
            $("#rootPwd").hide();
            $("#rN").hide();
            $("#rP").hide();
            method = "PUT";
        }

        function resetValue() {
            $("#userName").val("");
            $("#password").val("");
            $("#remark").val("");
        }

        function closeUserDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function verifyUser() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要验证的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].userID);
            }
            var ids = strIds.join(",");
            $.ajax({
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/pwdmanage/pmusers/verify/" + ids,//url
                data: {},
                success: function (result) {
                    console.log(result);//打印服务端返回的数据
                    if (result.resultCode == 200) {
                        $.messager.alert(
                            "系统提示",
                            "验证完成，状态已更新！");
                        $("#dg").datagrid(
                            "reload");
                    }
                    else {
                        $.messager.alert(
                            "系统提示",
                            "验证失败！");
                    }
                    ;
                },
                error: function () {
                    $.messager.alert("ERROR！");
                }
            });
        }

        function openImportDialog() {
            $("#dlgForImport").dialog("open").dialog("setTitle", "导入用户信息");
        }

        function importUser() {
            if($("#uploadExcel")[0].files[0] == null) {
                $.messager.alert("系统提示", "请选择要上传的文件！");
                return;
            }
            var formData = new FormData();
            formData.append("file", $("#uploadExcel")[0].files[0]);
            $.ajax({
                url: url + "/upload",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function(result) {
                    console.log(result);//打印服务端返回的数据
                    if (result.resultCode == 200) {
                        $.messager.alert("系统提示", "上传成功");
                        $("#dlgForImport").dialog("close");
                        $("#dg").datagrid("reload");
                        $("#uploadExcel").val("");
                    }
                    else {
                        $.messager.alert("系统提示", "上传失败");
                        $("#dlgForImport").dialog("close");
                        $("#uploadExcel").val("");
                    }
                },
                error: function(result) {
                    $.messager.alert("系统提示", "上传失败");
                    $("#dlgForImport").dialog("close");
                    $("#uploadExcel").val("");
                }
            });
        }

        function exportUser() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要导出的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].userID);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确认要导出这<font color=red>"
                + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $('<form method="get" action="/pwdmanage/pmusers/export/' + ids + '"/>').appendTo('body').submit().remove();
                }
            });
        }

        function closeImportDialog() {
            $("#dlgForImport").dialog("close");
            $("#uploadExcel").val("");
        }
    </script>
</head>
<body style="margin:1px;">
<script type="application/javascript">

</script>
<table id="dg" title="用户管理" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/pmusers/datagrid" fit="true"
       toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="dbName" width="10" align="center">数据库名</th>
        <th field="dbInfo" width="10" align="center">数据库</th>
        <th field="ip" width="15" align="center">ip</th>
        <th field="userName" width="10" align="center">用户名</th>
        <th field="pwd" width="30" align="center">密码</th>
        <%--<th field="createTime" width="20" align="center">创建时间</th>--%>
        <th field="remark" width="20" align="center">备注</th>
        <th field="state" width="20" align="center">状态</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openUserAddDialog()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:openUserModifyDialog()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:deleteUser()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a><a
            href="javascript:verifyUser()" class="easyui-linkbutton"
            iconCls="icon-ok" plain="true">验证</a><a
            href="javascript:openImportDialog()" class="easyui-linkbutton"
            iconCls="icon-ruku" plain="true">导入</a><a
            href="javascript:exportUser()" class="easyui-linkbutton"
            iconCls="icon-jcsjgl    " plain="true">导出</a>

    </div>
    <div>
        &nbsp;用户名：&nbsp;<input type="text" id="s_userName" size="20"
                               onkeydown="if(event.keyCode==13) searchUser()"/>
        &nbsp;数据库名称：&nbsp;<input type="text" id="s_dbName" size="20"
                                onkeydown="if(event.keyCode==13) searchUser()"/>
        &nbsp;IP地址：&nbsp;<input type="text" id="s_ip" size="20"
                                onkeydown="if(event.keyCode==13) searchUser()"/>
        &nbsp;登录状态：&nbsp;<input type="text" id="s_state" size="20"
                                onkeydown="if(event.keyCode==13) searchUser()"/>
        <a
                href="javascript:searchUser()" class="easyui-linkbutton"
                iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog"
     style="width: 880px;height:300px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>数据库类型：</td>
                <td><select type="text" id="dbInfo" class="easyui-combobox"
                            name="dbInfo" style="width:120px;">
                    <option value="mysql">mysql</option>
                    <option value="oracle">oracle</option>
                </select>&nbsp;<font
                        color="red">*</font>
                </td>
                <td>IP地址：</td>
                <td><input type="text" id="ip" name="ip"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font>
                </td>
                <td>端口：</td>
                <td><input type="text" id="port" name="port"
                           class="easyui-validatebox" required="true"
                           value="3306"/>&nbsp;<font
                        color="red">*</font>
                </td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="userName" name="userName"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font>
                    <input type="hidden" id="userId" value="0">
                    <input type="hidden" id="mainframeID" value="0">
                </td>
                <td>密码：</td>
                <td><input type="password" id="password" name="password"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font>
                </td>
                <td>备注：</td>
                <td><input type="text" id="remark" name="remark"
                           class="easyui-validatebox" required="false"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>数据库名称：</td>
                <td><input type="text" id="dbName" name="dbName"
                           class="easyui-validatebox" required="false"/>&nbsp;
                </td>
                <td id="rN">数据库管理员用户名：</td>
                <td><input type="text" id="rootName" name="rootName"
                           class="easyui-validatebox" required="false"
                           placeholder="新增主机时需要"/>&nbsp;
                </td>
                <td id="rP">数据库管理员密码：</td>
                <td><input type="password" id="rootPwd" name="rootPwd"
                           class="easyui-validatebox" required="false"
                           placeholder="新增主机时需要"/>&nbsp;
                </td>
            </tr>

        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveUser()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:closeUserDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

<div id="dlgForImport" class="easyui-dialog"
     style="width: 880px;height:300px;padding: 10px 20px" closed="true"
     buttons="#dlgForImport-buttons">
    <form id="uploadManage"  method="post" enctype="multipart/form-data">
        选择文件：<input id="uploadExcel" name="uploadExcel" type="file" style="width:200px" data-options="prompt:'请选择文件...'">
    </form>

    <a href="<%=request.getScheme() + "://" + request.getServerName()
    + ":" + request.getServerPort() + request.getContextPath()+ "/pmusers/template" %> "
       title="下载模板" target="_blank" >下载模板</a>
</div>

<div id="dlgForImport-buttons">
    <a href="javascript:importUser()" class="easyui-linkbutton"
       iconCls="icon-ok">上传</a> <a href="javascript:closeImportDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>

</div>


</body>
</html>