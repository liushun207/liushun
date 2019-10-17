package com;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 1.如果两个对象的`equals`的结果是相等的，则两个对象的`hashCode`的返回结果必须是相等的.
 * 2. 任何时候覆写了`equals`，都必须同时覆写`hashCode`
 * @author liushun
 */
@Data
public class EqualsObject {
    private int id;
    private String name;

    public EqualsObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        // 如果为null，或者并非同类，则直接返回false
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        // 如果引用指向同一个对象，则返回true
        if(this == o) {
            return true;
        }

        EqualsObject that = (EqualsObject) o;

        // 逻辑随业务场景不同而不同
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return id + name.hashCode();
    }

    public static void main(String[] args) {
        Set<EqualsObject> equalsObjects = new HashSet<>();

        EqualsObject a = new EqualsObject(1, "One");
        EqualsObject b = new EqualsObject(1, "One");
        EqualsObject c = new EqualsObject(1, "One");

        equalsObjects.add(a);
        equalsObjects.add(b);
        equalsObjects.add(c);

        // 当不覆写`hashCode`时 此处结果为3
        System.out.println(equalsObjects.size());
    }
}
