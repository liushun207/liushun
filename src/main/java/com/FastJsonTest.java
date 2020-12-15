package com;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * FastJsonTest
 * @author liushun
 * @date 2020/12/9
 */
public class FastJsonTest {
    public static void main(String[] args) {
        String str = "{\"code\":\"0000 \",\"message\":\"交易成功,认证成功\",\"out_trade_no\":\"20161026110000000032\",\"sign\":\"PBPQIn7/loK6gc0ku/i13EX6K8vSn+lnhY6mGZZA0Bk3OSS1WZK41zDr1XKnOW/N9OHsxL7/yutyZgwwMOUjltp7Nn6CmHJw3+W3bjmzwjrXTv0G2iSRPWxGHwbDJsqkALOIzbjDW62/yRh8pSh/RsM6opX8Pa/umLJBMiYMqyQ\",\"tran_amt\":\"0.500\",\"tran_time\":\" Wed Oct 26 11:00:01 CST 2016\",\"reserved\":\"{\\\"origRespCode\\\":\\\"00\\\",\\\"origRespMsg\\\":\\\"成功 [0000000]\\\",\\\"oriTraceNo\\\":\\\"123456\\\",\\\"oriTraceTime\\\":\\\"20201130110101\\\"}\"}";

        Map<String, String> respMap = (Map<String, String>) JSONObject.parse(str);

        // 保留字段
        String reserved = respMap.get("reserved");

        if(StringUtils.isNotBlank(reserved)) {
            Map<String, String> reservedMap = (Map<String, String>) JSONObject.parse(reserved);

            String oriTraceNo = reservedMap.get("oriTraceNo");
            if(StringUtils.isNotBlank(oriTraceNo)) {
                System.out.println(oriTraceNo);
            }

            // 设置 node
            String origRespMsg = reservedMap.get("origRespMsg");
            if(StringUtils.isNotBlank(origRespMsg)) {
                System.out.println(origRespMsg);
            }
        }
    }
}
