package com.yeahthon.app;

import java.util.Scanner;

public class QueueByArray {
    public static void main(String[] args) {
        QueueByArrayConstruct queue = new QueueByArrayConstruct(6);
        // 接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s:show：显示队列");
            System.out.println("e:exit：退出队列");
            System.out.println("a:add：添加元素到队列");
            System.out.println("g:get：从队列中取出元数");
            System.out.println("h:head：查看队列首的元数");
            // 接收第一个字符
            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个元素");
                    int element = scanner.nextInt();
                    queue.addQueue(element);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.printf("队列的头部元素是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出！");
    }
}

class QueueByArrayConstruct {
    private int maxSize;
    // 指向队列头部元素的前一个位置
    private int start;
    // 指向队列尾部元素
    private int end;
    private int[] arrayConstruct;

    // 创建队列构造器
    public QueueByArrayConstruct(int arrMaxSize) {
        maxSize = arrMaxSize;
        arrayConstruct = new int[maxSize];
        start = -1;
        end = -1;
    }

    // 判断队列是否满
    public boolean isFull() {
        return end == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return end == start;
    }

    // 添加数据到队列
    public void addQueue(int element) {
        if (isFull()) {
            System.out.println("队列已满，不能添加新元素！");
            return;
        }
        end++;
        arrayConstruct[end] = element;
    }

    // 出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能获取数据！");
        }
        start++;
        return arrayConstruct[start];
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，不能取数据！");
        }
        for (int i = 0; i < arrayConstruct.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arrayConstruct[i]);
        }
    }

    // 显示队列的头部数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据！");
        }
        return arrayConstruct[start + 1];
    }
}