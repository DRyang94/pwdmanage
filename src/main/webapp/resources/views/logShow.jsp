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

        function searchLog() {
            $("#dg").datagrid('load', {
                "userID": $("#s_userID").val()
            });
        }

        function deleteLog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].logID);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确认要删除这<font color=red>"
                    + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.ajax({
                        type: "DELETE",//方法类型
                        dataType: "json",//预期服务器返回的数据类型
                        url: "/pwdmanage/log/" + ids,//url
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
    </script>
</head>
<body style="margin:1px;">
<table id="dg" title="日志管理" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/log/datagrid" fit="true"
       toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="userID" width="30" align="center">用户ID</th>
        <th field="opType" width="10" align="center">操作类型</th>
        <th field="operation" width="30" align="center">操作</th>
        <th field="createTime" width="20" align="center">创建时间</th>
        <th field="remark" width="20" align="center">备注</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
            <a href="javascript:deleteLog()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;用户id：&nbsp;<input type="text" id="s_userID" size="20"
                               onkeydown="if(event.keyCode==13) searchLog()"/> <a
            href="javascript:searchLog()" class="easyui-linkbutton"
            iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

</body>
</html>