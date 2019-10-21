package com.algorithm;

import java.util.Stack;

/**
 * 三种方式反向打印单向链表
 * @author liushun
 * @since JDK 1.8
 */
public class ReverseNode {
    /**
     * 利用栈的先进后出特性.
     * @param node the node
     */
    public static void reverseNodeByStack(Node node) {
        if(node == null) {
            return;
        }

        System.out.println("====翻转之前====");

        Stack<Node> stack = new Stack<>();
        do {
            System.out.print(node.getValue() + "===>");

            stack.push(node);
            node = node.getNext();
        } while(node != null);

        System.out.println("");
        System.out.println("====翻转之后====");
        while(!stack.isEmpty()) {
            System.out.print(stack.pop().getValue() + "===>");
        }
    }

    /**
     * 递归
     * @param node the node
     */
    public static void recNode(Node node) {
        if(node == null) {
            return;
        }

        if(node.getNext() != null) {
            recNode(node.getNext());
        }

        System.out.print(node.getValue() + "===>");
    }

    /**
     * 利用头插法插入链表
     * @param head the head
     */
    public static void reverseNode(Node head) {
        if(head == null) {
            return;
        }

        // 最终翻转之后的 Node
        Node node;

        Node pre = head;
        Node cur = head.getNext();
        Node next;

        // 当前指针不为空
        while(cur != null) {
            // 下一个的指针
            next = cur.getNext();

            // 链表的头插法
            cur.setNext(pre);
            pre = cur;

            cur = next;
        }

        // 由于是反转 所以头结点的指针为null
        head.setNext(null);
        node = pre;

        // 遍历新链表
        while(node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    public static void main(String[] args) {
        Node<Integer> last = new Node<>(4, null);
        Node<Integer> third = new Node<>(3, last);
        Node<Integer> second = new Node<>(2, third);
        Node<Integer> first = new Node<>(1, second);


        // ReverseNode.reverseNodeByStack(first);
        // ReverseNode.recNode(first);
        ReverseNode.reverseNode(first);
    }
}
