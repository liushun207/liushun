package com.designmode.decoration;

import lombok.Data;

/**
 * 构建者模式
 * 用于构建复杂对象的一种模式，所构建的对象往往需要多步初始化或赋值才能完成
 * 使用Builder模式来替代多参数构造函数是一个比较好的实践法则
 * StudentBuilder();
 * StudentBuilder(String name);
 * StudentBuilder(String name，int age);
 * StudentBuilder(String name，int age，String address);
 * StudentBuilder(String name,int age,String address,String id);
 * @author liushun
 */
@Data
public class StudentBuilder {
    private String name;
    private int age;
    private String address;
    private String id;

    private StudentBuilder(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.id = builder.id;
    }

    public static void main(String[] args) {
        StudentBuilder studentBuilder = new StudentBuilder.Builder().setName("xxx").build();
    }

    public static class Builder {
        private String name = null;
        private int age = 0;
        private String address = null;
        private String id = null;

        public Builder() {

        }

        public Builder(String name) {
            this.name = name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public StudentBuilder build() {
            return new StudentBuilder(this);
        }
    }
}
