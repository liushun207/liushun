package com;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * InitSequenceBeanTest
 * @author liushun
 * @date 2020/10/9
 */
public class InitSequenceBeanTest implements InitializingBean {
    private boolean properties;

    public InitSequenceBeanTest() {
        System.out.println("InitSequenceBean constructor");
    }

    public void setProperties(boolean properties) {
        System.out.println("InitSequenceBean set properties");
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitSequenceBean afterPropertiesSet");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("InitSequenceBean postConstruct");
    }

    public void initMethod() {
        System.out.println("InitSequenceBean initMethod");
    }

    public boolean isProperties() {
        return properties;
    }

    public static void main(String[] args) {
        // <bean id="initSequenceBean" class="com.spring.initbean.InitSequenceBean"
        //     init-method="initMethod">
        //     <property name="properties" value="true"></property>
        // </bean></import>

        // 输出顺序
        // InitSequenceBean constructor
        // InitSequenceBean set properties
        // InitSequenceBean postConstruct
        // InitSequenceBean afterPropertiesSet
        // InitSequenceBean initMethod
    }
}
