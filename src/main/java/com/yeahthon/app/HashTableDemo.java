package com.yeahthon.app;

import java.util.Scanner;

// 哈希表
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加");
            System.out.println("list:显示");
            System.out.println("find:查找");
            System.out.println("exit:退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名：");
                    String name = scanner.next();
                    LinkedElement element = new LinkedElement(id, name);
                    hashTable.add(element);
                    break;
                case "list":
                    hashTable.show();
                    break;
                case "find":
                    System.out.println("请输入需要查找的ID：");
                    int findId = scanner.nextInt();
                    hashTable.findElementById(findId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

// 数组
class HashTable {
    // 链表的个数
    private final int size;
    // 链表
    private final LinkedList[] linkedListArray;

    public HashTable(int size) {
        this.size = size;
        // 初始化数组
        linkedListArray = new LinkedList[size];
        // 初始化链表
        for (int i = 0; i < size; i++) {
            linkedListArray[i] = new LinkedList();
        }
    }

    // 哈希函数、散列函数
    public int hashFun(int id) {
        return id % size;
    }

    // 添加元素
    public void add(LinkedElement element) {
        // 确定链表
        int linkedListNo = hashFun(element.id);
        // 将元素加入链表
        linkedListArray[linkedListNo].add(element);
    }

    // 遍历所有链表
    public void show() {
        for (int i = 0; i < size; i++) {
            linkedListArray[i].show(i);
        }
    }

    // 根据id查找元素
    public void findElementById(int id) {
        // 确定链表
        int linkedListNo = hashFun(id);
        LinkedElement element = linkedListArray[linkedListNo].findElementById(id);
        if (element != null) {
            System.out.println("第" + linkedListNo + "条链表中找到元素，id=" + id);
        } else {
            System.out.println("在哈希表中没有找到该元素！");
        }
    }
}

// 链表
class LinkedList {
    // 头指针
    private LinkedElement head;

    // 添加元素到链表
    public void add(LinkedElement element) {
        if (head == null) {
            head = element;
            return;
        }

        LinkedElement current = head;
        while (current.next != null) {

            current = current.next;
        }
        current.next = element;
    }

    // 遍历链表
    public void show(int linkedId) {
        if (head == null) {
            System.out.println("第" + linkedId + "条链表为空！");
            return;
        }

        System.out.print("第" + linkedId + "条链表链表信息为：");
        LinkedElement current = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", current.id, current.name);
            if (current.next == null) {
                break;
            }
            current = current.next;
        }
        System.out.println();
    }

    // 根据id查找链表中的元素
    public LinkedElement findElementById(int id) {
        if (head == null) {
            System.out.println("链表为空!");
            return null;
        }

        LinkedElement current = head;
        while (true) {
            if (current.id == id) {
                break;
            }
            if (current.next == null) {
                current = null;
                break;
            }
            current = current.next;
        }

        return current;
    }
}

// 链表中的一个元素
class LinkedElement {
    public int id;
    public String name;
    public LinkedElement next;

    public LinkedElement(int id, String name) {
        this.id = id;
        this.name = name;
    }
}