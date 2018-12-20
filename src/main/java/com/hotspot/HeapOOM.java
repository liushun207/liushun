/**
 * @author:
 * @Description:
 * @Data: 2018/12/18 14:58
 **/
package com.hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 估计java内存堆20m，不可自动扩展，设置参数 -XX:+HeapDumpOnOutOfMemoryError 可让虚拟机在出现内存不足时抛出
 */
public class HeapOOM
{
    static class OOMObject
    {

    }

    public static void main(String[] args)
    {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while(true)
        {
            list.add(new OOMObject());
        }
    }
}
