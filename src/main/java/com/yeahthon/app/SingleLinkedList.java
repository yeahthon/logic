package com.yeahthon.app;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

/**
 * desc：单向链表
 * func：
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        // 创建节点，无序插入链表
//        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
//        HeroNode hero3 = new HeroNode(3, "卢俊义", "玉麒麟");
//        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
//        HeroNode hero2 = new HeroNode(2, "吴用", "智多星");
//
//        SingleLinkedListManager singleLinedListManager = new SingleLinkedListManager();
//        singleLinedListManager.addByOrder(hero4);
//        singleLinedListManager.addByOrder(hero3);
//        singleLinedListManager.addByOrder(hero1);
//        singleLinedListManager.addByOrder(hero2);
//
//        System.out.println("初始链表：");
//        singleLinedListManager.showSingleLinkedList();

//        System.out.println();
//        HeroNode newHero = new HeroNode(2, "吴用!", "智多星!");
//        singleLinedListManager.updateElement(newHero);
//        System.out.println("修改后的链表：");
//        singleLinedListManager.showSingleLinedList();

//        System.out.println();
//        singleLinedListManager.deleteElement(1);
//        singleLinedListManager.deleteElement(4);
//        System.out.println("删除后的链表:");
//        singleLinedListManager.showSingleLinedList();
//        System.out.println();

//        System.out.println("链表中当前元素的个数为：" + singleLinedListManager.getLength());

//        System.out.println("当前链表中倒数第" + 2 + "个元素为：" + singleLinedListManager.getLastIndexNode(2, singleLinedListManager.getLength()));

//        singleLinedListManager.reverseLinkedList();
//        System.out.println("反转后的链表为：");
//        singleLinedListManager.showSingleLinkedList();

//        System.out.println("反向打印链表：");
//        singleLinedListManager.reverseShowLinkedList();

        // 创建第一个有序链表
        SingleLinkedListManager list1 = new SingleLinkedListManager();
        HeroNode hero2 = new HeroNode(2, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "武松", "行者");
        list1.addByOrder(hero2);
        list1.addByOrder(hero4);
        list1.addByOrder(hero6);
        System.out.println("第一个有序链表为：");
        list1.showSingleLinkedList();
        System.out.println();

        // 创建第二个有序链表
        SingleLinkedListManager list2 = new SingleLinkedListManager();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero5 = new HeroNode(5, "关胜", "大刀");
        list2.addByOrder(hero1);
        list2.addByOrder(hero3);
        list2.addByOrder(hero5);
        System.out.println("第二个有序链表为：");
        list2.showSingleLinkedList();
        System.out.println();

        SingleLinkedListManager mergeLinkedList = SingleLinkedListManager.mergeLinkedList(list1, list2);
        System.out.println("合并后的链表为：");
        mergeLinkedList.showSingleLinkedList();
    }
}

// SingleLinedList管理类
class SingleLinkedListManager {
    // 初始化头结点，头结点不存放数据，只表示链表的头
    HeroNode head = new HeroNode(0, "", "");

    // 向链表中添加元素：找到最后节点，最后节点的next指向新元素
    public void add(HeroNode element) {
        // TODO 使用一个辅助指针节点来遍历链表temp，通过该节点表示next所指向的元素来辅助遍历，即让temp表示当前节点
        // 初始化该辅助指针节点为头结点
        HeroNode temp = head;
        // TODO 遍历，找到链表的最后一个元素，循环停止的逻辑：当前元素的next为空
        // 退出循环时，temp就指向最后一个元素
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            // TODO 辅助指针节点后移，即将下一个节点赋值给辅助指针节点
            temp = temp.getNext();
        }
        temp.setNext(element);
    }

    // 按照顺序向链表中添加元素
    public void addByOrder(HeroNode element) {
        // TODO 通过辅助指针节点temp来遍历链表，辅助指针节点temp即表示当前元素
        // 因为是单项链表，新元素所插入的位置应为与temp的next位置
        HeroNode temp = head;

        // 设置标志位，标志当前插入的元素是否已经存在
        boolean flag = false;

        while (true) {
            // 链表为空
            if (temp.getNext() == null) {
                break;
            }

            if (temp.getNext().getId() > element.getId()) {
                // 辅助指针节点到达所要找的位置
                break;
            } else if (temp.getNext().getId() == element.getId()) {
                // 需要添加的元素已经存在
                flag = true;
                break;
            }

            // TODO 辅助指针节点后移，即将下一个节点赋值给辅助指针节点
            temp = temp.getNext();
        }

        if (flag) {
            // 元素已经存在
            System.out.println("需要添加的元素已经存在，编号为：" + element.getId());
        } else {
            // 直接插入到temp的后面
            element.setNext(temp.getNext());
            temp.setNext(element);
        }
    }

    // 修改节点信息
    public void updateElement(HeroNode element) {
        if (head.getNext() == null) {
            System.out.println("链表为空Q！");
            return;
        }

        HeroNode temp = head;
        // 标志位，表示是否找到该节点
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }

            if (temp.getId() == element.getId()) {
                flag = true;
                break;
            }

            temp = temp.getNext();
        }

        if (flag) {
            temp.setName(element.getName());
            temp.setNickName(element.getNickName());
        } else {
            System.out.println("没有找到所要修改的元素！");
        }
    }

    // 删除节点
    public void deleteElement(int id) {
        HeroNode temp = head;
        // 标志位：是否已经找到待删除的节点
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }

            // 找到待删除节点的前一个位置
            if (temp.getNext().getId() == id) {
                flag = true;
                break;
            }

            // 节点后移
            temp = temp.getNext();
        }

        if (flag) {
            temp.setNext(temp.getNext().getNext());
        } else {
            System.out.println("没有找到需要删除的节点编号：" + id);
        }
    }

    // TODO
    // 获取链表中有效节点的个数
    public int getLength() {
        HeroNode temp = head;
        if (temp.getNext() == null) {
            System.out.println("链表为空！");
            return 0;
        }

        int length = 0;
        while (temp.getNext() != null) {
            length++;
            temp = temp.getNext();
        }
        return length;
    }

    // 1、先获取链表的长度
    // 2、再获取第size-index+1个元素
    public HeroNode getLastIndexNode(int index, int length) {
        HeroNode temp = head;
        if (temp.getNext() == null) {
            System.out.println("链表为空！");
            return null;
        }

        // index校验
        if (index <= 0 || index > length) {
            System.out.println("元素索引位置有错！");
            return null;
        }

        for (int i = 0; i < length - index + 1; i++) {
            temp = temp.getNext();
        }

        return temp;
    }

    // TODO
    // 头插法
    public void reverseLinkedList() {
        // 链表为空或者元素个数为1时，无需反转
        if (head.getNext() == null || head.getNext().getNext() == null) {
            System.out.println("无需反转");
            return;
        }

        // 定义辅助指针节点用于遍历，当前遍历节点，当前正在处理的节点
        HeroNode cur = head.getNext();
        // 定义一个节点，用于指向temp的下一个节点，防止链断裂
        HeroNode next = null;
        // 辅助反转链表头部，哨兵节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        while (cur != null) {
            // 先使用next暂存当前temp节点的下一个节点
            next = cur.getNext();
            // 接旧头：先让当前cur节点连接新链表头部之后的数据
            cur.setNext(reverseHead.getNext());
            // 换新头：再将（当前节点+新链表节点）连接到新的链表头部上
            reverseHead.setNext(cur);
            // temp后移
            cur = next;
        }

        // 将链表反转
        head.setNext(reverseHead.getNext());
    }

    // 合并两个有序链表，返回依然是有序链表
    public static SingleLinkedListManager mergeLinkedList(SingleLinkedListManager list1,
                                                          SingleLinkedListManager list2) {
        // 新链表用于存放合并后的结果
        SingleLinkedListManager mergeList = new SingleLinkedListManager();

        // 获取两个链表的首个有效元素
        HeroNode head1 = list1.head.getNext();
        HeroNode head2 = list2.head.getNext();

        // 使用辅助指针节点，永远指向新链表的最后一个节点
        HeroNode current = mergeList.head;

        // 同时遍历两个链表，比较节点大小，将较小的节点加入到新链表
        while (head1 != null && head2 != null) {
            if (head1.getId() <= head2.getId()) {
                // 将head1加入新链表
                current.setNext(head1);
                current = head1;
                // 后移head1指针
                head1 = head1.getNext();
            } else {
                // 将head2加入新链表
                current.setNext(head2);
                current = head2;
                // 后移head2指针
                head2 = head2.getNext();
            }

            // 某一个链表先遍历完，另一个链表的剩余部分直接连接到新链表末尾
            if (head1 != null) {
                current.setNext(head1);
            }
            if (head2 != null) {
                current.setNext(head2);
            }
        }
        return mergeList;
    }

    // 显示链表：遍历
    public void showSingleLinkedList() {
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
            // TODO 辅助指针节点后移，即将下一个节点赋值给辅助指针节点
            temp = temp.getNext();
        }
    }

    // 利用栈的特性，逆向打印单向链表
    public void reverseShowLinkedList() {
        if (head.getNext() == null) {
            System.out.println("链表为空!");
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        // 借助辅助指针节点遍历链表
        HeroNode cur = head.getNext();
        while (cur != null) {
            // 入栈
            stack.push(cur);

            cur = cur.getNext();
        }

        // 出栈
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
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