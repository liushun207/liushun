package com.algorithm;

import lombok.Data;

/**
 * 简单的单向链表
 * @author liushun
 * @since JDK 1.8
 **/
@Data
public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}
