/**
 * @author:
 * @Description:
 * @Data: 2018/8/31 16:14
 **/
package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * @Configuration 等价于<Beans></Beans>
 * @ComponentScan 自动扫描 等价于 <context:component-scan base-package="com.configuration"/>
 */
@Configuration
@ComponentScan(basePackages = "com.configuration")
public class TestConfiguration
{
    /**
     * Instantiates a new Test configuration.
     */
    public TestConfiguration()
    {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    ///**
    // * 如果使用 @ComponentScan 注解扫描， @Component的注解将被自动注册为bean
    // * @return the test bean
    // * @Bean注解注册bean,同时可以指定初始化和销毁方法
    // * @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    // */
    //@Bean(name = "testBean", initMethod = "start", destroyMethod = "cleanUp")
    //@Scope("prototype")
    //public TestBean testBean()
    //{
    //    return new TestBean();
    //}
}
