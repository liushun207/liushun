/**
 * @author:
 * @Description:
 * @Data: 2019/1/22 14:46
 **/
package com.util;

public class ByteUtil
{
    /**
     * 获取一个字节的bit数组
     * @param value
     * @return
     */
    public static byte[] getByteArray(byte value)
    {
        // 一个字节八位
        byte[] byteArr = new byte[8];
        for(int i = 7; i > 0; i--)
        {
            // 获取最低位
            byteArr[i] = (byte) (value & 1);

            // 每次右移一位
            value = (byte) (value >> 1);
        }
        return byteArr;
    }

    /**
     * 把byte转为字符串的bit
     * @param b byte
     * @return
     */
    public static String byte2BitString(byte b)
    {
        return "" + (byte) ((b >> 7) & 0x1)
                + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1)
                + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1)
                + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1)
                + (byte) ((b >> 0) & 0x1);
    }

    /**
     * 获取一个字节第n位,
     * 思路：右移n位，与1
     * @param value
     * @param index
     * @return
     */
    public static int get(byte value, int index)
    {
        return (value >> index) & 0x1;
    }

    /**
     * 获取一个字节的第m到第n位
     * @param value
     * @param start >0
     * @param end >0
     * @return
     */
    public static byte[] getBitRange(byte value, int start, int end)
    {
        byte[] rangeArray = new byte[end - start + 1];
        if(start > 7 || start < 0)
        {
            throw new RuntimeException("illegal start param");
        }

        if(end > 7 || end < 0)
        {
            throw new RuntimeException("illegal end param");
        }

        if(start > end)
        {
            throw new RuntimeException("start can not bigger than end");
        }

        if(start == end)
        {
            rangeArray[0] = (byte) ByteUtil.get(value, start);
            return rangeArray;
        }

        for(int i = end; i < start; i--)
        {
            rangeArray[i] = (byte) ByteUtil.get(value, start);
        }
        return rangeArray;
    }

    /**
     * 位字符串转字节
     * @param str
     * @return
     */
    public static byte bitString2Byte(String str)
    {
        if(null == str)
        {
            throw new RuntimeException("when bit string convert to byte, Object can not be null!");
        }
        if(8 != str.length())
        {
            throw new RuntimeException("bit string'length must be 8");
        }
        try
        {
            //判断最高位，决定正负
            if(str.charAt(0) == '0')
            {
                return (byte) Integer.parseInt(str, 2);
            }
            else if(str.charAt(0) == '1')
            {
                return (byte) (Integer.parseInt(str, 2) - 256);
            }
        }
        catch(NumberFormatException e)
        {
            throw new RuntimeException("bit string convert to byte failed, byte String must only include 0 and 1!");
        }

        return 0;
    }
}
