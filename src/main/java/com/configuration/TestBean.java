/**
 * @author:
 * @Description:
 * @Data: 2018/8/31 16:20
 **/
package com.configuration;

import org.springframework.stereotype.Component;

/**
 * @Component 添加注册bean的注解 等价于<Bean id = "testBean"></Bean>
 */
@Component
public class TestBean
{
    private String username;
    private String url;
    private String password;

    /**
     * Say hello.
     */
    public void sayHello()
    {
        System.out.println("TestBean sayHello...");
    }

    //@Override
    //public String toString()
    //{
    //    return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    //}

    /**
     * Start.
     */
    public void start()
    {
        System.out.println("TestBean 初始化。。。");
    }

    /**
     * Clean up.
     */
    public void cleanUp()
    {
        System.out.println("TestBean 销毁。。。");
    }
}
