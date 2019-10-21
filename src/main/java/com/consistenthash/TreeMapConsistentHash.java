package com.consistenthash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * TreeMap 实现
 * @author liushun
 * @since JDK 1.8
 **/
public class TreeMapConsistentHash extends AbstractConsistentHash {
    // region 私有

    /**
     * 用于保存Hash环上的节点
     * TreeMap使用红黑树实现，插入效率比较差，但是查找效率极高
     */
    private TreeMap<Long, String> treeMap = new TreeMap<Long, String>();

    /**
     * 虚拟节点数量，虚拟节点数越多越均匀
     */
    private static final int VIRTUAL_NODE_SIZE = 100;

    // endregion

    /**
     * 新增节点
     * @param key
     * @param value
     */
    @Override
    protected void add(long key, String value) {
        for(int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
            Long hash = super.hash("vir" + key + i);
            treeMap.put(hash, value);
        }
        treeMap.put(key, value);
    }

    /**
     * 根据当前的 key 通过一致性 hash 算法的规则取出一个节点
     * @param value
     * @return
     */
    @Override
    protected String getFirstNodeValue(String value) {
        long hash = super.hash(value);
        // System.out.println("value=" + value + " hash = " + hash);

        // 获取key 大于 hash 的 keys
        SortedMap<Long, String> last = treeMap.tailMap(hash);
        if(!last.isEmpty()) {
            return last.get(last.firstKey());
        }
        return treeMap.firstEntry().getValue();
    }
}
