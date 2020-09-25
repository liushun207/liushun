package com.util;

import javax.servlet.http.HttpServletRequest;

/**
 * ip����
 */
public class IpUtil {
    private IpUtil() {
    }

    private static final String UNKNOWN = "unknown";

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For��Squid �������
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if(ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP��apache �������
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if(ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP��weblogic �������
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP����Щ���������
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if(ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP��nginx�������
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //��Щ����ͨ����������ô��ȡ����ip�ͻ��ж����һ�㶼��ͨ�����ţ�,���ָ�������ҵ�һ��ipΪ�ͻ��˵���ʵIP
        if(ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //���ǲ��ܻ�ȡ���������ͨ��request.getRemoteAddr();��ȡ
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
