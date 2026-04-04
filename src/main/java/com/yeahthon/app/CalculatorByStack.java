package com.yeahthon.app;

/**
 * desc：计算器：中缀表达式 + 栈
 */
public class CalculatorByStack {
    public static void main(String[] args) {
        String expression = "70*2+1-4";

        // 数栈：存储计算的数字
        StackByArrayConstruct numberStack = new StackByArrayConstruct(10);
        // 符号栈：存储运算符
        StackByArrayConstruct operatorStack = new StackByArrayConstruct(10);

        // 表达式扫描指针
        int index = 0;
        // 每次扫描表达式得到的字符
        char ch = ' ';
        // 拼接多位数
        String keepNum = "";
        // 从数栈取出局部计算的数字
        int num1 = 0;
        int num2 = 0;
        // 从符号栈取出的运算符
        int operator = 0;
        // 局部计算结果
        int result = 0;

        while (true) {
            // 获取expression的第一个字符，每次只取一个字符
            ch = expression.substring(index, index + 1).charAt(0);

            if (operatorStack.isOperator(ch)) {
                // 如果是运算符

                if (!operatorStack.isEmpty()) {
                    // 符号栈不为空时
                    // 情况一：当前操作符的优先级小于或者等于栈中的操作符，则从数栈中取出两个数，
                    //        从符号栈中取出一个运算符，进行运算，并将结果压入数栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        operator = operatorStack.pop();
                        result = numberStack.calculate(num1, num2, operator);
                        // 运算结果入栈
                        numberStack.push(result);
                        // 将当前扫描到的符号入栈
                        operatorStack.push(ch);
                    } else {
                        // 情况二：当前操作符的优先级大于栈中的操作符，直接入栈
                        operatorStack.push(ch);
                    }
                } else {
                    // 符号栈为空，直接入符号栈
                    operatorStack.push(ch);
                }
            } else {
                // 如果是数字，则直接入栈
                // 处理多位数，进行拼接
                keepNum += ch;

                if (index == expression.length() - 1) {
                    // 如果该字符已经是表达式的最后一位，则直接入栈
                    numberStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断当前字符的下一个字符是不是数字，如果是数字则继续扫描
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符，则入栈；例如“1”或者“123”
                        numberStack.push(Integer.parseInt(keepNum));
                        // 清空keepNum
                        keepNum = "";
                    }
                }
            }

            // 后一指针到expression末尾则跳出循环
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 表达式扫描完毕，从数栈、符号栈取数、操作符运算
        while (true) {
            // 如果符号栈为空，则计算完毕
            if (operatorStack.isEmpty()) {
                break;
            }

            num1 = numberStack.pop();
            num2 = numberStack.pop();
            operator = operatorStack.pop();
            result = numberStack.calculate(num1, num2, operator);
            numberStack.push(result);
        }

        // 取出数栈的最后结果
        int finalResult = numberStack.pop();
        System.out.printf("表达式：%s = %d", expression, finalResult);
    }
}

class StackByArrayConstruct {
    private int maxSize;
    private int[] stack;
    // 栈顶
    private int top = -1;

    // 构造器
    public StackByArrayConstruct(int maxSize) {
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

    // 查看当前栈顶元素，但不弹出元素
    public int peek() {
        return stack[top];
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

    // 返回运算符的优先级，数字越大优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是否是运算符
    public boolean isOperator(char element) {
        return element == '+' || element == '-' || element == '*' || element == '/';
    }

    // 计算方法
    public int calculate(int num1, int num2, int operator) {
        // 存放临时结果
        int result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}