package com.rain.binarytree;

/**
 * 用于测试二叉树
 */

public class ApplicationTest {

    public static void main(String[] args) {

        //创建需要的节点
        HeroNode root = new HeroNode(1, "jay");
        HeroNode node1 = new HeroNode(2, "jj");
        HeroNode node2 = new HeroNode(3, "eve");
        HeroNode node3 = new HeroNode(4, "李健");
        HeroNode node4 = new HeroNode(5, "许嵩");

        //手动创建二叉树
        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node3);
        node2.setRight(node4);

        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //传入根节点
        binaryTree.setRoot(root);

//        //测试遍历
//        System.out.println("前序遍历");
//        binaryTree.perOrder();
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

        //测试查找
        System.out.println("前序遍历查找");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        System.out.println(resNode);

        System.out.println("中序查找");
        HeroNode resNode1 = binaryTree.infixOrderSearch(5);
        System.out.println(resNode1);

        System.out.println("后序查找");
        HeroNode resNode2 = binaryTree.postOrderSearch(5);
        System.out.println(resNode2);

//        //测试删除节点
//        System.out.println("删除前 前序遍历==>");
//        binaryTree.perOrder();
//        binaryTree.delNode(5);
//        System.out.println("删除后 前序遍历==>");
//        binaryTree.perOrder();

        //测试顺序存储二叉树-前序遍历实现
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个 ArrayBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0); //1 2 4 5 3 6 7


        //测试实现线索二叉树
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        //传入根节点
        threadedBinaryTree.setRoot(root);
        //实现线索化
        threadedBinaryTree.threadedNodes();
        System.out.println("当前节点的前驱节点" + node3.getLeft()); // 3
        System.out.println("当前节点的后继节点" + node3.getRight()); // 1
        //中序线索二叉树的遍历
        threadedBinaryTree.threadedList(); // 2,1,4,3,5

    }


}
