/**
 * @author:
 * @Description:
 * @Data: 2018/11/9 17:09
 **/
package com.designmode.decoration;

/**
 * 烤食物
 * 烤-装饰者
 */
public class RoastFood extends FoodDecoration
{
    private AbstractFood food;

    public RoastFood(AbstractFood f)
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
        return "烤";
    }
}