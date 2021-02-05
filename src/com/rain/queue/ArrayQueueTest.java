package com.rain.queue;

import java.util.Scanner;

/**
 * 通过数组实现队列
 *
 * 队列遵循先入先出原理，通过定义两个变量 front 及 rear 分别记录队列前后端的下标
 * front 会随着数据输出而改变，而 rear 则是随着数据输入而改变
 * 通过定义 maxSize 来确定队列的最大长度
 * 添加数据时执行的操作：
 * 1) 将尾指针往后移：rear+1, 当 front ==rear 【空】
 * 2) 若尾指针 rear 小于队列的最大下标 maxSize-1，则将数据存入 rear 所指的数组元素中，否则无法存入数据。 rear ==maxSize-1[队列满]
 *
 * 弊端：数组不能重复使用，当队列填满后再全部出队列后 不能再次添加数据到数组 即 薛定谔的队列
 */

public class ArrayQueueTest {

    public static void main(String[] args) {
        //测试队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //用于接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show) : 显示数据");
            System.out.println("e(exit) : 推出程序");
            System.out.println("a(add) : 添加数据到队列");
            System.out.println("g(get) : 从队列取出数据");
            System.out.println("h(head) : 查看队列的头元素");
            key = scanner.next().charAt(0); //接受一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据为"+res);
                    } catch (Exception e) {
                        //捕获异常后打印异常信息
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("队列的头元素为："+res);
                    } catch (Exception e) {
                        // TODO: handle exception
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

        System.out.println("程序退出~~");

    }

}


//使用数组模拟队列首先编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize;    //表示数组的最大容量
    private int front;      //队列头
    private int rear;       //队列尾
    private int[] arr;      //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //注意front 以及 rear实际所指向的位置这很重要
        front = -1; // 指向队列头部，front是指向队列头的前一个位置.
        rear = -1;  // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //判断队列是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //首先判断队列是否为空
        if(isFull()) {
            System.out.println("队列已经满了，不能再添加数据了亲~~");
            return;
        }
        rear++; //让rear向后移
        arr[rear] = n;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //首先判断队列是否为空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空，不能取数据亲");
        }
        front++;    //front 后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if(isEmpty()) {
            System.out.println("队列为空，没有数据亲");
            return;
        }
        for (int i = 0;i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }


}