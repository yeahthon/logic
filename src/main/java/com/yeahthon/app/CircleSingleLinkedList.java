package com.yeahthon.app;

import lombok.Getter;
import lombok.Setter;

/**
 * desc：环形单向链表
 */
public class CircleSingleLinkedList {
    public static void main(String[] args) {
        CircleSingleLinkedListManager circleSingleLinkedListManager = new CircleSingleLinkedListManager();
//        circleSingleLinkedListManager.constructCircle(125);
//        System.out.println("环形单项链表的元素如下：");
//        circleSingleLinkedListManager.showCircleLinkedList();
//        circleSingleLinkedListManager.josephu(125, 10, 20);
//        System.out.println(circleSingleLinkedListManager.hasCircle());

        Element element1 = new Element(1);
        Element element2 = new Element(2);
        Element element3 = new Element(3);
        Element element4 = new Element(4);
        Element element5 = new Element(5);
        circleSingleLinkedListManager.constructCircleByOrder(element5);
        circleSingleLinkedListManager.constructCircleByOrder(element4);
        circleSingleLinkedListManager.constructCircleByOrder(element3);
        circleSingleLinkedListManager.constructCircleByOrder(element1);
        circleSingleLinkedListManager.constructCircleByOrder(element2);
        circleSingleLinkedListManager.showCircleLinkedList();
    }
}

class CircleSingleLinkedListManager {
    // 创建一个用于指向第一个元素的头节点指针
    private Element head = null;

    // 根据传入环形链表元素的个数，构建环形单向链表
    public void constructCircle(int circleSize) {
        // 校验
        if (circleSize < 1) {
            System.out.println("元素个数小于1！！！");
            return;
        }

        // 创建辅助指针节点，永远指向最后一个元素
        Element current = null;

        for (int i = 1; i <= circleSize; i++) {
            // 创建节点元素
            Element addElement = new Element(i);

            if (i == 1) {
                // 如果是第一个元素
                head = addElement;
                // 将元素构成环形
                head.setNext(head);
                // 后移辅助指针
                current = head;
            } else {
                // TODO 总结
                // 先连接新元素
                current.setNext(addElement);
                // 再构成环形
                addElement.setNext(head);
                // 辅助指针后移
                current = addElement;
            }
        }
    }

    // 按照顺序添加元素到环形单向链表，并将head指向最小节点
    public void constructCircleByOrder(Element element) {
        // 空链表
        if (head == null) {
            head = element;
            element.setNext(element);
            return;
        }

        // 情况1：插入的元素比head小（新最小值）
        if (element.getId() < head.getId()) {
            // 找到尾节点（最大值）
            Element end = head;
            while (end.getNext() != head) {
                end = end.getNext();
            }

            // 插入到head前面
            end.setNext(element);
            element.setNext(head);

            // 更新head
            head = element;
        } else {
            // 情况2：插入的元素比head大（插入到中间或末尾）
            Element current = head;

            while (current.getNext() != head) {
                // 找到插入位置：current <= element <= next
                if (current.getId() <= element.getId() &&
                        element.getId() <= current.getNext().getId()) {
                    break;
                }
                current = current.getNext();
            }

            // 插入节点
            Element next = current.getNext();
            current.setNext(element);
            element.setNext(next);
        }
    }

    public void showCircleLinkedList() {
        if (head == null) {
            System.out.println("环形链表为空");
            return;
        }

        // 借助辅助指针节点遍历
        Element current = head;
        while (true) {
            System.out.printf("节点编号为：%d\n", current.getId());

            if (current.getNext() == head) {
                break;
            }

            // 辅助指针节点后移
            current = current.getNext();
        }
    }

    /**
     * 约瑟夫环
     * @param circleSize 环大小
     * @param startId 起始节点编号
     * @param step 步长
     */
    public void josephu(int circleSize, int startId, int step) {
        // 校验
        if (head == null || startId < 1 || startId > circleSize) {
            System.out.println("输出数值有误，请重新输入！");
            return;
        }

        // 创建辅助指针节点，永远指向环形链表中的最后一个元素
        Element end = head;
        while (end.getNext() != head) {
            // 辅助指针后移
            end = end.getNext();
        }

        // 从startId位置开始，即head、end均后移startId - 1个位置
        for (int i = 1; i <= startId - 1; i++) {
            head = head.getNext();
            end = end.getNext();
        }

        // 开始出圈操作，让head、end均向后移动step-1个位置
        while (true) {
            if (end == head) {
                // 圈中只剩一个元素
                break;
            }

            for (int i = 1; i <= step - 1; i++) {
                head = head.getNext();
                end = end.getNext();
            }

            // 此时head指向的就是需要出圈的节点
            System.out.printf("节点%d出圈\n", head.getId());
            // head元素出圈，head指向下一个节点
            head = head.getNext();
            // end链接新head节点
            end.setNext(head);
        }

        System.out.printf("最后圈内剩下的节点编号为:%d", head.getId());
    }

    // 判断链表是否有环
    public boolean hasCircle() {
        if (head == null || head.getNext() == null) {
            return false;
        }

        Element slow = head;
        Element fast = head.getNext();

        // TODO
        // 循环的跳出设计
        // 当只要有一个条件不成立即可跳出循环
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}

@Getter
@Setter
// 构造环形单向链表的节点
class Element {
    private int id;
    private Element next;

    public Element(int id) {
        this.id = id;
    }
}
