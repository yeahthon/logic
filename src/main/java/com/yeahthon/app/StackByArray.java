package com.yeahthon.app;

import java.util.Scanner;

public class StackByArray {
    public static void main(String[] args) {
        StackByArrayConstruct stack = new StackByArrayConstruct(5);
        String key = " ";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show:显示栈");
            System.out.println("exit:推出程序");
            System.out.println("push:添加元素到栈");
            System.out.println("pop:从栈取出元素");
            System.out.println("请输入操作类型:");
            key = scanner.next();

            switch (key) {
                case "show":
                    stack.showStack();
                    break;
                case "push":
                    System.out.println("请输入一个元素：");
                    int input = scanner.nextInt();
                    stack.push(input);
                    break;
                case "pop":
                    try {
                        int result = stack.pop();
                        System.out.println("出栈的元素为：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序！");
    }
}

class StackByArrayManager {
    private int maxSize;
    private int[] stack;
    // 栈顶
    private int top = -1;

    // 构造器
    public StackByArrayManager(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int element) {
        if (isFull()) {
            System.out.println("栈已满！");
            return;
        }

        top++;
        stack[top] = element;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空！");
        }

        int result = stack[top];
        top--;
        return result;
    }

    // 遍历栈，从栈顶到栈底
    public void showStack() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}