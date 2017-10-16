package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.entity.SysFunction;

import java.util.List;

public interface SysFunctionDao {
    /**
     * 查找功能列表
     *
     * @param name
     * @return
     */
    SysFunction getFun(String name);

}
