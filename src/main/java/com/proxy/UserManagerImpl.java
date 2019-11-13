package com.proxy;

import java.text.MessageFormat;

/**
 * The type User manager.
 * @author liushun
 */
public class UserManagerImpl implements IUserManager {
    /**
     * 新增用户抽象方法.
     * @param userName the user name
     * @param password the password
     */
    @Override
    public void addUser(String userName, String password) {
        System.out.println("调用了新增的方法！");
        System.out.println("传入参数为 userName: " + userName + " password: " + password);
    }

    /**
     * 删除用户抽象方法.
     * @param userName the user name
     */
    @Override
    public void deleteUser(String userName) {
        //System.out.println("调用了删除的方法！");
        //System.out.println("传入参数为 userName: " + userName);
    }
}
