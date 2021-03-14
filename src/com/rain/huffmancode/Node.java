package com.rain.huffmancode;

//创建Node 存数据和权值

public class Node implements Comparable<Node> {
    Byte data; //存放数据本身 如 'a' => 97
    int weight; //权值,表示字符出现的次数
    Node left;
    Node right;

    //定义构造器
    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        // 从小到大排列
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void perOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.perOrder();
        }
        if (this.right != null) {
            this.right.perOrder();
        }
    }

}
