package com.rain.binarysorttree;

/**
 * 二叉排序树
 * 给定数组创建二叉排序树
 *
 * BST:对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大
 *
 */


public class BinarySortTreeTest {

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        //循环添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树 ~");
        binarySortTree.infixOrder();

    }

}




//创建二叉排序树
class BinarySortTree {

private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 添加节点
     *
     * @param node 待添加节点
     */
    public void add(Node node) {
        if (root == null) {
            //如果 root 为空 则直接让root 指向 node
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 遍历二叉排序树
     *
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空 不能遍历");
        }
    }



}



//创建 Node 节点
class Node{

    int value;
    Node left;
    Node right;

    //定义构造器
    public Node (int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();

        }
    }


    /**
     * 二叉排序树添加节点
     *
     * @param node
     */
    public void add(Node node) {

        if (node == null) {
            return;
        }
        //判断传入的节点的值 和当前子树的根节点的值进行比较
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else { //添加的节点的值大于 当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }

        }
    }




}




