package com.proxy;

/**
 * 用户接口
 * @author les
 */
public interface IUserManager {
    /**
     * 新增用户抽象方法.
     * @param userName the user name
     * @param password the password
     */
    void addUser(String userName, String password);

    /**
     * 删除用户抽象方法.
     * @param userName the user name
     */
    void deleteUser(String userName);
}
