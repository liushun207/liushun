/**
 * @author:
 * @Description:
 * @Data: 2018/11/9 13:49
 **/
package com;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性Hash算法.
 * @param <T> 节点类型
 */
public class ConsistentHash<T>
{
    // region 成员

    /**
     * Hash计算对象，用于自定义hash算法
     */
    private HashFunc hashFunc;

    /**
     * 复制的节点个数
     * 使用虚拟节点的思想，为每个物理节点（服务器）在圆上分配100～200个点。
     * 这样就能抑制分布不均匀，最大限度地减小服务器增减时的缓存重新分布。
     * 用户数据映射在虚拟节点上，就表示用户数据真正存储位置是在该虚拟节点代表的实际物理服务器上
     */
    private final int numberOfReplicas;

    /**
     * 一致性Hash环
     */
    private final SortedMap<Long, T> circle = new TreeMap<>();

    // endregion

    // region 构造函数

    /**
     * 构造，使用Java默认的Hash算法.
     * @param numberOfReplicas 复制的节点个数，增加每个节点的复制节点有利于负载均衡
     * @param nodes 节点对象
     */
    public ConsistentHash(int numberOfReplicas, Collection<T> nodes)
    {
        this.numberOfReplicas = numberOfReplicas;

        // lambda 写法
        this.hashFunc = (Object key) -> md5HashingAlg(key.toString());

        // 传统写法
        // this.hashFunc = new HashFunc()
        // {
        //     @Override
        //     public Long hash(Object key)
        //     {
        //         return md5HashingAlg(key.toString());
        //     }
        // };

        // 初始化节点
        for(T node : nodes)
        {
            add(node);
        }
    }

    /**
     * 构造函数
     * @param hashFunc hash算法对象
     * @param numberOfReplicas 复制的节点个数，增加每个节点的复制节点有利于负载均衡
     * @param nodes 节点对象
     */
    public ConsistentHash(HashFunc hashFunc, int numberOfReplicas, Collection<T> nodes)
    {
        this.numberOfReplicas = numberOfReplicas;
        this.hashFunc = hashFunc;

        // 初始化节点
        for(T node : nodes)
        {
            add(node);
        }
    }

    // endregion

    // region 公共方法

    /**
     * 增加节点<br>
     * 每增加一个节点，就会在闭环上增加给定复制节点数<br>
     * 例如复制节点数是2，则每调用此方法一次，增加两个虚拟节点，这两个节点指向同一Node
     * 由于hash算法会调用node的toString方法，故按照toString去重
     * @param node 节点对象
     */
    public void add(T node)
    {
        for(int i = 0; i < numberOfReplicas; i++)
        {
            circle.put(hashFunc.hash(node.toString() + i), node);
        }
    }

    /**
     * 移除节点的同时移除相应的虚拟节点
     * @param node 节点对象
     */
    public void remove(T node)
    {
        for(int i = 0; i < numberOfReplicas; i++)
        {
            circle.remove(hashFunc.hash(node.toString() + i));
        }
    }

    /**
     * 获得一个最近的顺时针节点
     * @param key 为给定键取Hash，取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * @return 节点对象
     */
    public T get(Object key)
    {
        // hash 环为空 直接返回
        if(circle.isEmpty())
        {
            return null;
        }

        // 获取hash 值
        long hash = hashFunc.hash(key);

        // 获取顺时针方向上最近的一个hash
        if(!circle.containsKey(hash))
        {
            // 返回此映射的部分视图，其键大于等于 hash
            SortedMap<Long, T> tailMap = circle.tailMap(hash);

            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }

        // 正好命中
        return circle.get(hash);
    }

    // endregion

    // region 私有方法

    /**
     * 使用MD5算法
     * @param key 给定键
     * @return hash
     */
    private static long md5HashingAlg(String key)
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
            return res;
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return 0L;
    }

    /**
     * 使用FNV1hash算法
     * @param key 给定键
     * @return hash
     */
    private static long fnv1HashingAlg(String key)
    {
        final int p = 16777619;
        int hash = (int) 2166136261L;

        for(int i = 0; i < key.length(); i++)
        {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        return hash;
    }

    // endregion

    /**
     * Hash算法对象，用于自定义hash算法
     */
    public interface HashFunc
    {
        /**
         * Hash算法.
         * @param key the key
         * @return the long
         */
        Long hash(Object key);
    }
}
