package com.consistenthash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 一致性 hash 算法抽象类
 * @author liushun
 * @since JDK 1.8
 **/
public abstract class AbstractConsistentHash {
    // region 保护方法

    /**
     * 新增节点
     * @param key
     * @param value
     */
    protected abstract void add(long key, String value);

    /**
     * 排序节点，数据结构自身支持排序可以不用重写
     */
    protected void sort() {
    }

    /**
     * 根据当前的 key 通过一致性 hash 算法的规则取出一个节点
     * @param value
     * @return
     */
    protected abstract String getFirstNodeValue(String value);

    // endregion

    // region 公共方法

    /**
     * 初始化所有节点
     * @param nodes
     */
    public void add(List<String> nodes) {
        for(String value : nodes) {
            add(hash(value), value);
        }
        sort();
    }

    /**
     * 获取一个服务节点
     * @param key
     * @return
     */
    public String getServer(String key) {
        return getFirstNodeValue(key);
    }

    /**
     * hash 运算
     * @param value
     * @return
     */
    public Long hash(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = value.getBytes("UTF-8");
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + value, e);
        }

        md5.update(keyBytes);
        byte[] digest = md5.digest();

        // hash code, Truncate to 32-bits
        long hashCode = ((long) (digest[3] & 0xFF) << 24) | ((long) (digest[2] & 0xFF) << 16) | ((long) (digest[1] & 0xFF) << 8) | (digest[0] & 0xFF);

        long truncateHashCode = hashCode & 0xffffffffL;
        return truncateHashCode;
    }

    // endregion
}
