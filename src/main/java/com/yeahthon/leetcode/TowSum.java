package com.yeahthon.leetcode;

// 两数之和：反转链表（头插法）
public class TowSum {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node3;
        node3.next = node4;
        node4.next = node2;

        System.out.println(node2.reverseListNode().list2Integer());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 新链表头指针
        ListNode prev = new ListNode(0);
        // 进位指针
        int carry = 0;
        // 遍历指针
        ListNode cur = prev;

        while (l1 != null || l2 != null) {
            // 不等于空时直接取值，等于空时赋值0，保持两个链表具有相同的位数
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10;
        }


        return new ListNode(l1.reverseListNode().list2Integer() + l2.reverseListNode().list2Integer());
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // 遍历链表
    public void showList() {
        ListNode cur = this;
        while (cur != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
    }

    // 反转链表
    public ListNode reverseListNode() {
        // 1、定义一个指针cur，用于遍历链表
        ListNode cur = this;
        // 2、定义一个指针next，暂存当前节点cur的下一个节点，防止链表断裂
        ListNode next = null;
        // 3、定义一个哨兵节点，用于链接反转后的链表
        ListNode reverseNode = new ListNode(0);

        while (cur != null) {
            next = cur.next;
            // 4、遍历取出原链表中的每一个节点
            // 4.1、接旧头：将当前节点cur的后续连接到反转链表
            cur.next = reverseNode.next;
            // 4.2、换新头：再将已经拼接后的链表连接到反转链表
            reverseNode.next = cur;

            // 5、链表后移
            cur = next;
        }

        return reverseNode;
    }

    // 转成整数
    public Integer list2Integer() {
        ListNode cur = this.next;
        int sum = 0;
        int step = 1;

        while (cur != null) {
            sum += cur.val * step;
            step *= 10;
            cur = cur.next;
        }

        return sum;
    }
}