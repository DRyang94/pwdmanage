<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/jquery-easyui-1.3.3/themes/default/easyui.css">
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
                mainframeName: $("#s_mainframeName").val(),
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
            var systemInfo = $("#systemInfo").val();
            var mainframeName = $("#mainframeName").val();
            var ip = $("#ip").val();
            var port = $("#port").val();
            var mainframeID = $("#mainframeID").val();
            var rootName = $("#rootName").val();
            var rootPwd = $("#rootPwd").val();

            var data = {"userID": userID, "userName": userName,
                "pwd": password, "remark": remark, "state": null,
                "mainframeID": mainframeID, "mainframeName": mainframeName,
                "systemInfo": systemInfo, "ip": ip, "port": port,
                "rootName": rootName, "rootPwd": rootPwd,
                "dbID":null, "dbName":null, "dbInfo":null }
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
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].userID);
            }
            var ids = strIds.join(",");
            $.ajax({
                type: "VERIFY",//方法类型
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
        <th field="mainframeName" width="10" align="center">主机名</th>
        <th field="systemInfo" width="10" align="center">系统</th>
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
            iconCls="icon-confirm" plain="true">验证</a>
    </div>
    <div>
        &nbsp;用户名：&nbsp;<input type="text" id="s_userName" size="20"
                               onkeydown="if(event.keyCode==13) searchUser()"/>
        &nbsp;主机名称：&nbsp;<input type="text" id="s_mainframeName" size="20"
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
                <td>主机类型：</td>
                <td><select type="text" id="systemInfo" class="easyui-combobox"
                            name="systemInfo" style="width:120px;">
                        <option value="aa">linux</option>
                        <option>unix</option>
                    </select>&nbsp;<font
                        color="red">*</font>
                </td>
                <td>IP地址：</td>
                <td><input type="text" id="ip" name="ip"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font>
                </td>
                <td>SSH端口：</td>
                <td><input type="text" id="port" name="port"
                           class="easyui-validatebox" required="true"
                           value="22"/>&nbsp;<font
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
                <td><input type="text" id="password" name="password"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font>
                </td>
                <td>备注：</td>
                <td><input type="text" id="remark" name="remark"
                           class="easyui-validatebox" required="false"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>主机名称：</td>
                <td><input type="text" id="mainframeName" name="mainframeName"
                           class="easyui-validatebox" required="false"/>&nbsp;
                </td>
                <td id="rN">主机管理员用户名：</td>
                <td><input type="text" id="rootName" name="rootName"
                           class="easyui-validatebox" required="false"
                           placeholder="新增主机时需要"/>&nbsp;
                </td>
                <td id="rP">主机管理员密码：</td>
                <td><input type="text" id="rootPwd" name="rootPwd"
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
</body>
</html>