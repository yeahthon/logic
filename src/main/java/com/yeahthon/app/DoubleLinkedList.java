package com.yeahthon.app;

public class DoubleLinkedList {
}

class DoubleHeroNodeManager {
    // 初始化头结点
    private final DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    // 遍历
    public void showList() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }

        // 辅助指针节点
        DoubleHeroNode current = head.next;
        while (current != null) {

            System.out.println(current);
            current = current.next;
        }
    }

    // 添加节点到链表的最后
    public void addElement(DoubleHeroNode element) {
        DoubleHeroNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        // 当退出循环时，current就指向最后一个元素
        current.next = element;
        element.pre = current;
    }

    // 修改链表中的元素
    public void updateElement(DoubleHeroNode element) {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }

        // 定义辅助指针节点
        DoubleHeroNode current = head.next;
        // 标识是否找到该节点
        boolean flag = false;
        while (true) {
            if (current == null) {
                break;
            }

            if (current.id == element.id) {
                flag = true;
                break;
            }

            current = current.next;
        }

        // 根据flag判断是否需要修改节点
        if (flag) {
            current.name = element.name;
            current.nickName = element.nickName;
        } else {
            System.out.println("没有找到编号为 " + element.id + " 的元素！");
        }
    }

    // 按照id删除某个节点
    public void deleteElement(int id) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除！");
            return;
        }

        // 定义辅助指针节点
        DoubleHeroNode current = head.next;
        // 标志是否找到待删除的节点
        boolean flag = false;
        while (true) {
            if (current == null) {
                break;
            }

            if (current.id == id) {
                flag = true;
                break;
            }

            current = current.next;
        }

        // 根据flag判断是否需要删除数据
        if (flag) {
            // 删除
            current.pre.next = current.next;
            // 防止删除最后一个元素引起的空指针异常
            if (current.next != null) {
                current.next.pre = current.pre;
            }
        } else {
            System.out.println("需要删除的节点 " + id + " 不存在！");
        }
    }
}

class DoubleHeroNode {
    int id;
    String name;
    String nickName;
    DoubleHeroNode pre;
    DoubleHeroNode next;

    public DoubleHeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode [id = " + id + ", name = " + name + ", nickName = " + nickName + "]";

    }
}