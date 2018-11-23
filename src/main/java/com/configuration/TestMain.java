/**
 * @author:
 * @Description:
 * @Data: 2018/8/31 16:17
 **/
package com.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain
{
    public static void main(String[] args)
    {
        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        // 如果加载spring-context.xml文件：
        // ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        TestBean tb = (TestBean) context.getBean("testBean");
        tb.sayHello();
        System.out.println(tb);

        TestBean tb2 = (TestBean) context.getBean("testBean");
        tb2.sayHello();
        System.out.println(tb2);
    }
}
