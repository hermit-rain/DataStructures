package com.rain.queue;

import java.util.Scanner;

/**
 * 通过数组来模拟环形队列  解决正常数组队列不能重复使用的弊端
 *
 * 充分利用数组. 因此将数组看做是一个环形的。(通过取模的方式来实现即可)  取模：%
 * front变量：从开始指向队列第一个元素的前一个元素转换成指向队列的第一个元素 即arr[front]就会队列的头元素
 * rear变量： 从开始指向队列的最后一个元素转化成指向队列的最后一个元素的后一个元素，即希望预留出一个空间作为约定
 * front初始值 = rear初始值 = 0；
 * 队列满的条件： （rear+1）% maxSize = front
 * 队列空的条件： rear == front
 * 队列中有效的数据个数： (rear + maxSize - front) % maxSize --方便遍历数组取出队列的值
 */


public class CircleArrayQueueTest {

    public static void main(String[] args) {

        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleArray queue = new CircleArray(4); //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
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

class CircleArray {
    private int maxSize;    //表示数组的最大容量
    private int front;
    private int rear;
    private int[] arr;

    //定义构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否为满
        if(isFull()) {
            System.out.println("队列已经满，不能添加数据啊亲");
            return;
        }
        arr[rear] = n;
        //添加完数据后rear需要取模后移
        rear = (rear + 1) % maxSize;
    }
    //获取队列数据-出队列
    public int getQueue() {
        if(isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //显示队列的所以数据
    public void showQueue() {
        //遍历
        if(isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }
    //求出当前的队列的有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
    //显示队列的头元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }

}

