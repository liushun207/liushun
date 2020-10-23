package com;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 银行
 **/
@Data
public class Bank {
    private String name;
    private String address;
    private int age;

    private final Map<String, Integer> map = new HashMap<>();

    public Bank(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Bank bank = (Bank) o;
        return age == bank.age && Objects.equals(name, bank.name) && Objects.equals(address, bank.address);
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(name, address, age);
    // }

    public static void main(String[] args) {
        // Bank bank1 = new Bank("1", "1", 1);
        // Bank bank2 = new Bank("1", "1", 1);
        // System.out.println(bank1.equals(bank2));

        // String ext = "{\"channelExt\":{\"action\":\"SIGN_FLOW_FINISH\",\"businessScence\":\"少雄科技股份有限公司合作协议20200904-1545-自动签约\",\"endTime\":\"2020-09-04 15:46:14\",\"flowId\":\"5c6187b1a61c4fea8d17beaa889a8023\",\"paygateNo\":\"60000002\"},\"code\":\"2-已完成: 所有签署人完成签署\",\"note\":\"完成\",\"tranState\":\"00\"}";
        // JSONObject extJob = JSONObject.parseObject(ext);
        // ContractReturn contractReturn = JSONObject.toJavaObject(extJob, ContractReturn.class);
        // contractReturn.setChannelExt(extJob.getString("channelExt"));
        //
        // System.out.println(JSON.toJSONString(contractReturn));

        String a = 1L + "";
    }
}
