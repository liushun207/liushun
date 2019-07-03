/**
 * @author:
 * @Description:
 * @Data: 2019/1/9 17:02
 **/
package com.util;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具.
 */
public class ToolUtil {
    /**
     * 将16进制转换为二进制.
     * @param hexStr the hex str
     * @return byte [ ]
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if(hexStr.length() < 1) {
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];
        for(int i = 0; i < hexStr.length() / 2; i++) {
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
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if(hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    // /**
    //  * 将16进制转换为浮点数
    //  * 用于将Modbus通信中截取后四字节的十六进制数转换为单精度浮点数.
    //  * @param hexStr the hex str
    //  * @return the float
    //  */
    // public static Float parseHexStr2Float(String hexStr)
    // {
    //     // float 类型值为123.456 以大端模式存储数据即高字节存于低地址，低字节存于高地址,小端模式反之
    //     byte[] bytes = parseHexStr2Byte(hexStr);
    //
    //     // 创建一个 ByteArrayInputStream，使用bytes作为其缓冲区数组
    //     //ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    //
    //     // 再将 bais 封装为DataInputStream类型
    //     //DataInputStream dis = new DataInputStream(bais);
    //
    //     try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes); DataInputStream dis = new DataInputStream(bais))
    //     {
    //         float flt = dis.readFloat();
    //         return flt;
    //     }
    //     catch(IOException ex)
    //     {
    //         return -1f;
    //     }
    // }

    /**
     * 将16进制转换为浮点数
     * @param hexStr the hex str
     * @return the float
     */
    public static Float parseHexStr2Float(String hexStr) {
        try {
            float result = Float.intBitsToFloat(Integer.parseInt(hexStr, 16));
            return result;
        }
        catch(Exception ex) {
            return -1f;
        }
    }

    /**
     * 16进制字符串转Long.
     * @param hexStr 16进制字符串
     * @return the long
     */
    public static Long parseHexStr2Long(String hexStr) {
        Long result = Long.parseLong(hexStr, 16);
        return result;
    }

    /**
     * 计算16进制累加和.
     * @param hexdata 16进制字符串
     * @return 16进制字符串 string
     */
    public static String makeChecksum(String hexdata) {
        if(hexdata == null || hexdata.isEmpty()) {
            return "00";
        }

        hexdata = hexdata.replaceAll(" ", "");

        int total = 0;
        int len = hexdata.length();

        if(len % 2 != 0) {
            return "00";
        }

        int num = 0;
        while(num < len) {
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
    public static String parseHexStr2Str(String hexdata) {
        if(hexdata == null || hexdata.isEmpty()) {
            return null;
        }

        hexdata = hexdata.replaceAll(" ", "");

        int len = hexdata.length();

        if(len % 2 != 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int num = 0;
        while(num < len) {
            String s = hexdata.substring(num, num + 2);
            stringBuilder.append(s + " ");
            num = num + 2;
        }

        return stringBuilder.toString();
    }

    // region 私有方法

    private static String hexInt(int total) {
        int a = total / 256;
        int b = total % 256;

        if(a > 255) {
            return hexInt(a) + format(b);
        }

        return format(a) + format(b);
    }

    private static String format(int hex) {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if(len < 2) {
            hexa = "0" + hexa;
        }
        return hexa;
    }

    // endregion

    /**
     * 获取本机外网ip
     */
    public static String getV4IP() {
        String ipUrl = "http://www.ip138.com/ips1388.asp";

        String ip = "";

        HttpURLConnection urlConnection = null;
        BufferedReader in = null;

        try {
            URL url = new URL(ipUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

            // 请求链接超时的时间
            urlConnection.setConnectTimeout(2000);

            // 读取数据超时的时间
            urlConnection.setReadTimeout(2000);

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            StringBuilder inputLine = new StringBuilder();
            String read = "";

            while((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }

            Pattern p = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
            Matcher m = p.matcher(inputLine.toString());
            if(m.find()) {
                ip = m.group();
            }

            return ip;
        }
        catch(Exception e) {
            return ip;
        }
        finally {
            if(in != null) {
                try {
                    in.close();
                }
                catch(IOException e) {
                }
            }

            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    /**
     * 获取本机MAC地址
     */
    public static String getMACAddress() throws Exception {
        //获取本地IP对象
        InetAddress ipAddress = InetAddress.getLocalHost();

        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ipAddress).getHardwareAddress();

        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < mac.length; i++) {
            if(i != 0) {
                sb.append(":");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);

            sb.append(s.length() == 1 ? 0 + s : s);
        }

        //把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }

    // 获取mac地址
    public static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac = null;
            while(allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();

                // 把一些非物理网卡或无用网上过滤掉，然后再取网上的IPV4地址即可。
                if(netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
                    continue;
                }
                else {
                    mac = netInterface.getHardwareAddress();
                    if(mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
                        }

                        if(sb.length() > 0) {
                            return sb.toString();
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            //_logger.error("MAC地址获取失败", e);
        }
        return "";
    }

    public static String bytes2Hexstr(byte[] byteData, int start, int end) {
        String retStr = "";

        for(int no = start; no < end; no++) {
            int data = byteData[no] & 0xff;
            retStr += String.format("%02X", data);
        }

        return retStr;
    }

    public static String byteArray2Hexstr(byte[] byteData, int start, int end) {
        String retStr = "";

        for(int no = start; no < end; no++) {
            int data = byteData[no] & 0xff;
            // 区别
            retStr += String.format("%02d", data);
        }

        return retStr;
    }

    /**
     * 从数值中解析出底数等于2的多个指数幂值集合
     * 例如：input=15，将解析出[1,2,4,8]，即表示15包含2º,2¹,2²,2³
     */
    public static List<Integer> exponent(Integer num) {
        if(num == null || num <= 0) {
            return null;
        }

        char[] strArr = Integer.toBinaryString(num).toCharArray();
        int index = strArr.length - 1;

        List<Integer> result = new ArrayList<>();
        while(index >= 0)
        {
            if(strArr[index] == '1')
            {
                int val = (int)Math.pow(2, strArr.length - index - 1);

                result.add(val);
            }

            index--;
        }

        return result;
    }

    public static String getStr(String b) {
        return b;
    }

    public static void main(String[] args) {
        try {


            System.out.println(exponent(15));
            System.out.println(exponent(3));
            System.out.println(exponent(5));
            System.out.println(exponent(12));

            // String str = "05030841b741a3000042686856";
            //
            // byte[] bytes1 = ToolUtil.parseHexStr2Byte(str);
            //
            // String byteStr = ToolUtil.bytes2Hexstr(bytes1, 0, bytes1.length);

            // Float value = Float.intBitsToFloat(Integer.parseInt(str, 16));

            //System.out.println(byteStr);


            // for(int i = 0; i < 5000; i++)
            // {
            //     String ip = getMACAddress();
            //     System.out.println(ip);
            // }

            // for(int i = 0; i < 5000; i++)
            // {
            //     String ip1 = getMACAddress();
            //     System.out.println(ip1);
            // }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
