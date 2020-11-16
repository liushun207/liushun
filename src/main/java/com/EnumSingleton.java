package com;

import java.io.Serializable;

/**
 * 单元素的枚举单例模式
 * 好处：线程安全，防止反序列化重新创建新的对象
 * @author liushun
 * @date 2020/11/16
 */
public enum EnumSingleton implements Serializable {
    /**
     * 链接
     */
    DATASOURCE;

    private DBConnection connection = null;
    private EnumSingleton() {
        connection = new DBConnection();
    }

    public DBConnection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        DBConnection con1 = EnumSingleton.DATASOURCE.getConnection();
        DBConnection con2 = EnumSingleton.DATASOURCE.getConnection();
        System.out.println(con1 == con2);
    }
}


class DBConnection {
}

