/**
 * @author:
 * @Description:
 * @Data: 2019/1/9 17:02
 **/
package com.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

/**
 * 工具.
 */
public class ToolUtil
{
    /**
     * 将16进制转换为二进制.
     * @param hexStr the hex str
     * @return byte [ ]
     */
    public static byte[] parseHexStr2Byte(String hexStr)
    {
        if(hexStr.length() < 1)
        {
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];
        for(int i = 0; i < hexStr.length() / 2; i++)
        {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

    /**
     * 将二进制转换成16进制.
     * @param buf the buf
     * @return string string
     */
    public static String parseByte2HexStr(byte buf[])
    {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; i++)
        {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if(hex.length() == 1)
            {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    /**
     * 将16进制转换为浮点数
     * 用于将Modbus通信中截取后四字节的十六进制数转换为单精度浮点数.
     * @param hexStr the hex str
     * @return the float
     */
    public static Float parseHexStr2Float(String hexStr)
    {
        // float 类型值为123.456 以大端模式存储数据即高字节存于低地址，低字节存于高地址,小端模式反之
        byte[] bytes = parseHexStr2Byte(hexStr);

        // 创建一个 ByteArrayInputStream，使用bytes作为其缓冲区数组
        //ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        // 再将 bais 封装为DataInputStream类型
        //DataInputStream dis = new DataInputStream(bais);

        try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes); DataInputStream dis = new DataInputStream(bais))
        {
            float flt = dis.readFloat();
            return flt;
        }
        catch(IOException ex)
        {
            return -1f;
        }
    }

    /**
     * 16进制字符串转Long.
     * @param hexStr 16进制字符串
     * @return the long
     */
    public static Long parseHexStr2Long(String hexStr)
    {
        Long result = Long.parseLong(hexStr, 16);
        return result;
    }

    /**
     * 计算16进制累加和.
     * @param hexdata 16进制字符串
     * @return 16进制字符串 string
     */
    public static String makeChecksum(String hexdata)
    {
        if(hexdata == null || hexdata.isEmpty())
        {
            return "00";
        }

        hexdata = hexdata.replaceAll(" ", "");

        int total = 0;
        int len = hexdata.length();

        if(len % 2 != 0)
        {
            return "00";
        }

        int num = 0;
        while(num < len)
        {
            String s = hexdata.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }

        return hexInt(total);
    }

    /**
     * 16进制字符串加空格 如 0203 =》 02 03.
     * @param hexdata 16进制字符串
     * @return 16进制字符串加上空格 string
     */
    public static String parseHexStr2Str(String hexdata)
    {
        if(hexdata == null || hexdata.isEmpty())
        {
            return null;
        }

        hexdata = hexdata.replaceAll(" ", "");

        int len = hexdata.length();

        if(len % 2 != 0)
        {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int num = 0;
        while(num < len)
        {
            String s = hexdata.substring(num, num + 2);
            stringBuilder.append(s + " ");
            num = num + 2;
        }

        return stringBuilder.toString();
    }

    // region 私有方法

    private static String hexInt(int total)
    {
        int a = total / 256;
        int b = total % 256;

        if(a > 255)
        {
            return hexInt(a) + format(b);
        }

        return format(a) + format(b);
    }

    private static String format(int hex)
    {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if(len < 2)
        {
            hexa = "0" + hexa;
        }
        return hexa;
    }

    // endregion
}
