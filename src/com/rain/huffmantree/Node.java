package com.rain.huffmantree;


public class Node implements Comparable<Node> {

    int value;// 结点权值
    Node left; //左子节点
    Node right; //右子节点

    //定义构造器
    public Node(int value) {
        this.value = value;
    }


    @Override
    public int compareTo(Node node) {
        return this.value - node.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }


}
