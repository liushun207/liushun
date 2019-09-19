package com.proxy;

/**
 * 简单测试.
 * jdk8 ，动态代理的性能得到了显著的提升，而 cglib 的表现并未跟上
 */
public class ProxyTest {

    public static void main(String[] args) {

        // region 第一种 对比生成的代理数量

        // long start = System.currentTimeMillis();
        //
        // for (int i = 0; i < 1000000; i++) {
        //     // 实例化JDKProxy对象
        //     JdkProxy jdkProxy = new JdkProxy();
        //
        //     // 获取代理对象
        //     IUserManager user = (IUserManager) jdkProxy.getJDKProxy(new UserManagerImpl());
        //
        //     // 执行新增方法
        //     user.addUser("admin", "123123");
        // }
        //
        // long end = System.currentTimeMillis();
        //
        // System.out.println("JdkProxy = " + ((end - start)) + " millisecond");
        //
        // start = System.currentTimeMillis();
        //
        // for (int i = 0; i < 1000000; i++) {
        //     // 实例化 CglibProxy对象
        //     CglibProxy cglib = new CglibProxy();
        //
        //     // 获取代理对象
        //     IUserManager user =  (IUserManager) cglib.getCglibProxy(new UserManagerImpl());
        //
        //     // 执行删除方法
        //     user.deleteUser("admin");
        // }
        //
        // end = System.currentTimeMillis();
        // System.out.println("CglibProxy = " + ((end - start)) + " millisecond");

        // endregion

        // region 第二种 执行的数量

        long start = System.currentTimeMillis();

        // 实例化JDKProxy对象
        JdkProxy jdkProxy = new JdkProxy();

        // 获取代理对象
        IUserManager user = (IUserManager) jdkProxy.getJDKProxy(new UserManagerImpl());

        for (int i = 0; i < 5000000; i++) {
            // 执行新增方法
            user.addUser("admin", "123123");
        }

        long end = System.currentTimeMillis();

        System.out.println("JdkProxy = " + ((end - start)) + " millisecond");

        // 实例化 CglibProxy对象
        CglibProxy cglib = new CglibProxy();

        // 获取代理对象
        IUserManager cgUser =  (IUserManager) cglib.getCglibProxy(new UserManagerImpl());

        start = System.currentTimeMillis();

        for (int i = 0; i < 5000000; i++) {
            // 执行删除方法
            cgUser.deleteUser("admin");
        }

        end = System.currentTimeMillis();
        System.out.println("CglibProxy = " + ((end - start)) + " millisecond");

        // endregion
    }
}
