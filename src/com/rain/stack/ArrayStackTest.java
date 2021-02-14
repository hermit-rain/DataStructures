package com.rain.stack;

import java.util.Scanner;

/**
 * 用数组模拟栈的使用
 * 1.定义一个top来表示栈顶，初始化为 -1
 * 2.入栈操作，当有数据加入到栈时，top++,stack[top] = data;
 * 3.出栈操作， int value = stack[top]; top--; return value;
 */

public class ArrayStackTest {

    public static void main(String[] args) {

        //测试
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;    //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 表示退出程序");
            System.out.println("push: 表示添加数据到队列");
            System.out.println("pop: 表述从栈中取出数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的顺序为"+res);
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
        System.out.println("程序退出--");

    }


}

//定义一个ArrayStack 表示栈
class ArrayStack {
    private  int maxSize;   //栈的大小
    private int[] stack;    //数组模拟栈，数据即存放到该数组中
    private int top = -1;   //top表示栈顶，初始化为-1

    //定义构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty() {
        return top == -1;
    }
    //入栈
    public void push(int value) {
        //判断栈是否满
        if(isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop() {
        //先判断是否为空
        if(isEmpty()) {
            throw  new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历，从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        //需要从栈顶开始显示数据
        for(int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}