/**
 * @author:
 * @Description:
 * @Data: 2018/11/9 17:05
 **/
package com.designmode.decoration;

/**
 * 鸭
 */
public class Duck extends AbstractFood
{
    public Duck()
    {
        desc = "鸭肉";
    }

    @Override
    public String getDesc()
    {
        return desc;
    }
}
