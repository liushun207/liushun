
package com.designmode.decoration;

/**
 * 装饰者基类
 * 食物基类
 */
public abstract class AbstractFood
{
    /**
     * 描述.
     */
    protected String desc;

    /**
     * 获取描述
     * @return the desc
     */
    public abstract String getDesc();
}