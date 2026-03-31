package com.yeahthon.app;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * desc：单向链表
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "吴用", "智多星");
        HeroNode hero3 = new HeroNode(3, "卢俊义", "玉麒麟");

        SingleLinedListManager singleLinedListManager = new SingleLinedListManager();
        singleLinedListManager.add(hero1);
        singleLinedListManager.add(hero2);
        singleLinedListManager.add(hero3);

        singleLinedListManager.showSingleLinedList();
    }
}

// SingleLinedList管理类
class SingleLinedListManager {
    // 初始化头结点，头结点不存放数据，只表示链表的头
    HeroNode head = new HeroNode(0, "", "");

    // 向链表中添加元素：找到最后节点，最后节点的next指向新元素
    public void add(HeroNode element) {
        // 使用一个辅助指针节点来遍历链表，通过该节点表示next所指向的元素来辅助遍历
        // 初始化该辅助指针节点为头结点
        HeroNode temp = head;
        // TODO 遍历，找到链表的最后一个元素，循环停止的逻辑：当前元素的next为空
        // 退出循环时，temp就指向最后一个元素
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            // TODO 节点后移
            temp = temp.getNext();
        }
        temp.setNext(element);
    }

    // 显示链表：遍历
    public void showSingleLinedList() {
        if (head.getNext() == null) {
            System.out.println("链表为空！");
            return;
        }

        // 借助辅助指针节点来遍历链表
        // 不为空，至少有一个元素
        HeroNode temp = head.getNext();
        while (true) {
            // TODO 遍历判断，循环停止条件：当前元素为null时停止
            if (temp == null) {
                break;
            }

            // 输出节点信息
            System.out.println(temp);
            // TODO 节点后移
            temp = temp.getNext();
        }
    }
}

@Getter
@Setter
// 定义HeroNode，每个HeroNode就是一个链表中的节点
class HeroNode {
    private int id;
    private String name;
    private String nickName;
    // 指向下一个节点
    private HeroNode next;

    // 构造器
    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    // 重写toString
    @Override
    public String toString() {
        return "HeroNode [id = " + id + ", name = " + name + ", nickName = " + nickName + "]";
    }
}