/**
 * @author:
 * @Description:
 * @Data: 2018/11/9 17:03
 **/
package com.designmode.decoration;

/**
 * 鸡.
 */
public class Chicken extends AbstractFood
{
    public Chicken()
    {
        desc = "鸡肉";
    }

    @Override
    public String getDesc()
    {
        return desc;
    }
}
