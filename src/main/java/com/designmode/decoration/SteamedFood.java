/**
 * @author:
 * @Description:
 * @Data: 2018/11/9 17:08
 **/
package com.designmode.decoration;

/**
 * 蒸食物
 * 蒸-装饰者
 */
public class SteamedFood extends FoodDecoration
{
    private AbstractFood food;

    public SteamedFood(AbstractFood f)
    {
        this.food = f;
    }

    @Override
    public String getDesc()
    {
        return getDecoration() + food.getDesc();
    }

    private String getDecoration()
    {
        return "蒸";
    }
}
