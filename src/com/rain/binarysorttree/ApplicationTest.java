package com.rain.binarysorttree;

/**
 * 用于测试二叉树
 */

public class ApplicationTest {

    public static void main(String[] args) {

        //首先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
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




    }


}
