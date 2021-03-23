package com.rain.avl;

/**
 * 平衡二叉树
 * 平衡二叉树也叫平衡二叉搜索树又被称为 AVL 树， 可以保证查询效率较高
 * 在通过数组创建一棵二叉排序树的时候 因为根节点选取为数组的第一个元素
 * 同时整个二叉排序树的构建受到根节点值的影响 ==> 很可能导致整棵树向左或右倾斜(形似单链表) 从而影响查询效率
 * 特点： 一 棵空树或它的左右两个子树的高度差的绝对值不超过 1，并且左右两个子树都是一棵平衡二叉树
 * 实现： 红黑树、AVL(算法)、替罪羊树、伸展树等
 * <p>
 * 改进二叉排序树 ==> 平衡二叉树
 * 左旋
 * 1.创建一个新的节点 newNode 创建一个新的节点 值等于当前根节点的值
 * 2.把新节点的左子树设置为当前节点的左子树
 * 3.把新节点的右子树设置为当前节点的右子树的左子树
 * 4.把当前节点的值换为右子节点的值
 * 5.把当前节点的右子树设置为右子树的右子树
 * 6.把当前节点的左子树设置为新的节点
 * 右旋
 * 1.创建一个新的节点 值等于当前节点的值
 * 2.把新的节点的右子树设置为当前节点的右子树
 * 3.把新节点的左子树设置为当前节点的左子树的右子树
 * 4.把当前节点的值换成左子节点的值
 * 5.把当前节点的左子树设置为左子树的左子树
 * 6.把当前节点的右子树设置为新的节点
 * 双旋转
 * 问题分析：当符合右旋转的条件时 如果左子树的右子树高度大于它的左子树的高度  ==> 右旋之后并不能转换成平衡二叉树
 * 解决思路：先对当前这个节点的左节点进行左旋 再对当前节点进行右旋 (左旋转同理)
 */

public class AvlTreeTest {

    public static void main(String[] args) {

        //测试
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建一个 AVL 树
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //中序遍历
        System.out.println("中序遍历的结果为");
        avlTree.infixOrder();

        System.out.println("树的高度 = " + avlTree.getRoot().height());
        System.out.println("树的左子树的高度 = " + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度 = " + avlTree.getRoot().rightHeight());
        System.out.println("当前根节点为 = " + avlTree.getRoot());


    }
}

//创建AVL树
class AvlTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空 不能遍历啊亲");
        }
    }


}


//创建 Node 节点
class Node {

    int value;
    Node left;
    Node right;

    //定义构造器
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    //左旋
    private void leftRotate() {

        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    //右旋
    private void rightRotate() {

        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    //添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        //当添加完一个节点之后 如果(右子树的高度 - 左子树的高度) > 1 则左旋
        if (rightHeight() - leftHeight() > 1) {
            //判断是否需要双旋
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子节点进行右旋转
                right.rightRotate();
                //然后对当前节点进行左旋
                leftRotate();
            } else {
                leftRotate();
            }
            return; //一旦进行旋转加入后就不需要进行判读是否需要进行右旋了
        }

        //(如果左子树的高度 - 右子树的高度) > 1 右旋
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }

    }


}







