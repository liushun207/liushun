package com.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * 红包算法.
 * 模拟微信红包生成，单位：分
 * @author liushun
 */
public class RedPacket {
    // region 私有属性

    /**
     * 生成红包最小值 1分
     */
    private static final int MIN_MONEY = 1;

    /**
     * 生成红包最大值 200人民币
     */
    private static final int MAX_MONEY = 200 * 100;

    /**
     * 小于最小值
     */
    private static final int LESS = -1;

    /**
     * 大于最大值
     */
    private static final int MORE = -2;

    /**
     * 正常值
     */
    private static final int OK = 1;

    /**
     * 最大的红包是平均值的 TIMES 倍，防止某一次分配红包较大
     */
    private static final double TIMES = 2.1F;

    private int recursiveCount = 0;

    // endregion

    // region 公共方法

    /**
     * 分红包
     * @param money 总金额，单位：分
     * @param count 红包个数
     * @return List<Integer> 所有红包金额
     */
    public List<Integer> splitRedPacket(int money, int count) throws Exception {
        if(money <= 0 || count <= 0) {
            throw new Exception("数据错误");
        }

        List<Integer> moneys = new LinkedList<>();

        // 金额检查，如果最大红包 * 个数 < 总金额；则需要调大最大红包 MAX_MONEY
        if(MAX_MONEY * count <= money) {
            System.err.println("请调大最小红包金额 MAX_MONEY=[" + MAX_MONEY + "]");
            return moneys;
        }

        // 计算出最大红包
        int max = (int) ((money / count) * TIMES);
        max = max > MAX_MONEY ? MAX_MONEY : max;

        for(int i = 0; i < count; i++) {
            // 随机获取红包
            int redPacket = randomRedPacket(money, MIN_MONEY, max, count - i);
            moneys.add(redPacket);

            // 总金额每次减少
            money -= redPacket;
        }

        return moneys;
    }

    // endregion

    // region 私有方法

    /**
     * 随机获取红包
     * @param totalMoney 总金额
     * @param minMoney 最小金额
     * @param maxMoney 最大金额
     * @param count 红包个数
     * @return 红包金额
     */
    private int randomRedPacket(int totalMoney, int minMoney, int maxMoney, int count) {
        // 只有一个红包直接返回
        if (count == 1) {
            return totalMoney;
        }

        // 如果最大金额大于了剩余金额 则用剩余金额 因为这个 money 每分配一次都会减小
        maxMoney = maxMoney > totalMoney ? totalMoney : maxMoney;

        // 在 minMoney到maxMoney 生成一个随机红包
        int redPacket = minMoney == maxMoney ? minMoney :
                (int) (Math.random() * (maxMoney - minMoney) + minMoney);

        int lastMoney = totalMoney - redPacket;

        int status = checkMoney(lastMoney, count - 1);

        // 正常金额
        if (OK == status) {
            return redPacket;
        }

        //如果生成的金额不合法 则递归重新生成
        if (LESS == status) {
            recursiveCount++;
            System.out.println("recursiveCount==" + recursiveCount);
            return randomRedPacket(totalMoney, minMoney, redPacket, count);
        }

        if (MORE == status) {
            recursiveCount++;
            System.out.println("recursiveCount===" + recursiveCount);
            return randomRedPacket(totalMoney, redPacket, maxMoney, count);
        }

        return redPacket;
    }

    /**
     * 校验剩余的金额的平均值是否在 最小值和最大值这个范围内
     * @param lastMoney
     * @param count
     * @return
     */
    private int checkMoney(int lastMoney, int count) {
        double avg = lastMoney / count;
        if (avg < MIN_MONEY) {
            return LESS;
        }

        if (avg > MAX_MONEY) {
            return MORE;
        }

        return OK;
    }

    // endregion

    public static void main(String[] args) throws Exception {
        RedPacket redPacket = new RedPacket();
        List<Integer> redPackets = redPacket.splitRedPacket(20000, 10);
        System.out.println(redPackets);

        int sum = 0;
        for (Integer red : redPackets) {
            sum += red;
        }
        System.out.println(sum);
    }
}
