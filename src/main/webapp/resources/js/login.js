function login() {
    var userName = $("#userName").val();
    var password = $("#pwd").val();
    if (userName == null || userName == "") {
        alert("用户名不能为空！");
        return;
    }
    if (password == null || password == "") {
        alert("密码不能为空！");
        return;
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/pwdmanage/sysusers/cookie",
        data: $('#adminlogin').serialize(),
        success: function (result) {
            if (result.resultCode == 200) {
                setCookie("userName", result.data.currentUser.userName);
                setCookie("roleName", result.data.currentRole.roleName);
                if(result.data.currentRole.roleName == "admin") {
                    window.location.href = "main.jsp";
                }
                else {
                    window.location.href = "main2.jsp";
                }
            }
            ;

        },
        error: function () {
            alert("异常！");
        }
    });

}