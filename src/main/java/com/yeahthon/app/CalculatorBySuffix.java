package com.yeahthon.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 计算器：后缀表达式（逆波兰表达式） + 栈
 */
public class CalculatorBySuffix {
    public static void main(String[] args) {
        // 测试直接计算中缀表达式
        // 给定一个逆波兰表达式
//        String suffixExpression = "30 4 + 5 * 6 -";
        // 遍历逆波兰表达式，将元素存入到集合中
//        List<String> list = getListString(suffixExpression);
//        int result = calculate(suffixExpression);
//        System.out.println("最后结算的结果是：" + result);

        // 测试中缀表达式转后缀表达式
        // 中缀表达式
        String infixExpression = "1+((2+3)*4)-5";
        // 中缀表达式集合
        List<String> infixExpressionList = stringToInfixExpressionList(infixExpression);
        System.out.println(infixExpressionList);
        // 后缀表达式
        List<String> suffixExpression = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpression);
    }

    // 将一个逆波兰表达式中的元素放入到集合中
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");

        // 数组直接转集合
        return new ArrayList<>(Arrays.asList(split));
    }

    // 计算
    public static int calculate(List<String> list) {
        // 只需创建一个栈
        Stack<String> stack = new Stack<>();

        for (String element : list) {
            if (element.matches("\\d+")) {
                // 匹配的是多位数
                stack.push(element);
            } else {
                // 匹配的是符号
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;

                if (element.equals("+")) {
                    result = num1 + num2;
                } else if (element.equals("-")) {
                    result = num1 - num2;
                } else if (element.equals("*")) {
                    result = num1 * num2;
                } else if (element.equals("/")) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误！");
                }

                // 将计算结果入栈
                stack.push(result + "");
            }
        }

        // 最后留在栈中的数据即为运算结果
        return Integer.parseInt(stack.pop());
    }

    // 将中缀表达式转成对应的List集合
    public static List<String> stringToInfixExpressionList(String infixExpression) {
        ArrayList<String> infixExpressionList = new ArrayList<>();
        // 遍历中缀表达式字符串的指针
        int index = 0;
        // 拼接多位数
        String str;
        // 每次遍历的一个字符
        char c;

        do {
            if ((c = infixExpression.charAt(index)) < 48 || (c = infixExpression.charAt(index)) > 57) {
                // 如果是一个字符，直接加入集合
                infixExpressionList.add(c + "");
                index++;
            } else {
                // 如果是一个数字，则需要考虑多位数的情况
                str = "";
                while (index < infixExpression.length()
                        && (c = infixExpression.charAt(index)) >= 48
                        && (c = infixExpression.charAt(index)) <= 57) {
                    str += c;
                    index++;
                }
                infixExpressionList.add(str);
            }
        } while (index < infixExpression.length());

        return infixExpressionList;
    }

    // 将中缀表达式List集合转为后缀表达式List集合
    public static List<String> parseSuffixExpressionList(List<String> infixExpressList) {
        // 符号栈
        Stack<String> operatorStack = new Stack<>();
        // 存储中间结果的List集合
        ArrayList<String> resultList = new ArrayList<>();

        // 遍历中缀表达式List
        for (String element : infixExpressList) {
            if (element.matches("\\d+")) {
                // 如果是一个数字，直接入中间集合
                resultList.add(element);
            } else if (element.equals("(")) {
                // 如果是个左括号，直接压入符号栈
                operatorStack.push(element);
            } else if (element.equals(")")) {
                // 如果是个右括号，依次弹出符号栈中栈顶的元素加入中间集合，直到遇到左括号为止，并将左括号去掉
                while (!operatorStack.peek().equals("(")) {
                    resultList.add(operatorStack.pop());
                }
                // 消除左括号
                operatorStack.pop();
            } else {
                // 如果是一个运算符
                // 当当前符号的优先级小于等于符号栈顶的优先级，将符号栈的栈顶元素弹出并加入中间集合，直至条件不成立
                while (!operatorStack.isEmpty() &&
                        Operation.getOperatorLevel(operatorStack.peek()) >= Operation.getOperatorLevel(element)) {
                    resultList.add(operatorStack.pop());
                }
                // 将该符号压入栈
                operatorStack.push(element);
            }
        }

        // 将栈中剩余的运算符弹出加入中间集合
        resultList.add(operatorStack.pop());

        return resultList;
    }

}

// 符号类，获取传入符号的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getOperatorLevel(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符:" + operation);
                break;
        }

        return result;
    }
}