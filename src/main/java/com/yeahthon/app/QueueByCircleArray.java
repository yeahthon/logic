package com.yeahthon.app;

import java.util.Scanner;

public class QueueByCircleArray {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(4);
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

class CircleArray {
    private int maxSize;
    // 指向队列中的第一个元素
    private int start;
    // 指向队列中下一个可以插入元素的位置，永远是一个“空位置”
    private int end;
    private int[] circleArray;

    public CircleArray(int inputMaxSize) {
        maxSize = inputMaxSize;
        circleArray = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull() {
        return (end + 1) % maxSize == start;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return start == end;
    }

    // 添加数据到队列
    public void addQueue(int element) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据！");
            return;
        }

        // 直接添加数据
        circleArray[end] = element;
        // 后移end
        end = (end + 1) % maxSize;
    }

    // 从队列中取出数据
    public int getQueue() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列为空，不能取出数据！");
        }

        int result = circleArray[start];
        start = (start + 1) % maxSize;
        return result;
    }

    // 获取当前队列中有效的元素个数
    public int getElementSize() {
        return (end + maxSize - start) % maxSize;
    }

    // 显示队列中的所有元素
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }

        // 通过从start开始遍历，遍历一定元素个数
        System.out.println("开始遍历队列！");
        for (int i = start; i < start + getElementSize(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, circleArray[i % maxSize]);
        }
    }

    // 显示队列头部元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列中没有元素！");
        }

        return circleArray[start];
    }
}