/**
 * @author:
 * @Description:
 * @Data: 2018/11/5 10:23
 **/
package com;

class Message<T extends Number> {
    private T message;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}

public class Test {

    public static void main(String[] args) {
        Message<Integer> message = new Message();
        message.setMessage(55);
        fun(message);
    }

    /**
     * 此时使用通配符"?"描述的是它可以接收任意类型，但是由于不确定类型，所以无法修改
     * @param temp
     */
    public static void fun(Message<? extends Number> temp) {
        //仍然无法修改！
        // temp.setMessage(100);
        System.out.println(temp.getMessage());
    }
}

