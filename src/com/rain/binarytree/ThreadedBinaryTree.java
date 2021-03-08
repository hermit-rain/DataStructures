package com.rain.binarytree;


/**
 * 线索二叉树 ====> 实现及遍历
 *
 * 完全二叉树 ==> 线索二叉树
 * 考虑到完全二叉树中部分节点的左右指针并没有充分的利用所以提出线索二叉树的概念 让各个节点都尽可能的指向自己的前后节点
 *
 * 基本概念
 * 1) n 个结点的二叉链表中含有 n+1 【公式 2n-(n-1)=n+1】 个空指针域。
 * 2) 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(ThreadedBinaryTree)
 * 3) 根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 * 4) 一个结点的前一个结点，称为前驱结点 ; 一个结点的后一个结点，称为后继结点
 *
 */
//定义一个ThreadedBinaryTree 实现二叉树的线索化

public class ThreadedBinaryTree {

    //根节点
    private HeroNode root;
    //辅助节点 用于指向当前节点的前驱节点
    //在递归进行线索化时 pre 总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //重载 threadedNode方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }


    /**
     * 二叉树中序线索化
     *
     * @param node 当前要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        //如果 node == null 不能线索化
        if (node == null) {
            return;
        }
        //1.递归线索化左子树
        threadedNodes(node.getLeft());

        //2.线索化当前节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftTye(1);
        }
        //接着处理后继节点
        //通过pre 辅助指针 "反向"创建后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        //node 指针按照中序遍历的顺序移动
        //pre 辅助指针始终指向 node 节点的前驱节点
        pre = node;

        //3.递归线索化右子树
        threadedNodes(node.getRight());

    }


    /**
     * 遍历线索二叉树
     */
    public void threadedList() {
        //定义一个变量 存储当前遍历的节点 从root开始
        HeroNode node = root;
        while (node != null) {
            //循环一开始先向左找到第一个线索化的节点 即 leftType == 1 的节点
            //在右子树的遍历中 也同样是首先进入循环向左找到该子树的第一个线索化的节点 然后逐渐输出后继节点
            while (node.getLeftTye() == 0) {
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点有后继节点就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //跳出后继节点的循环后 将 node 指针更新至 当前节点的右子节点
            node = node.getRight();
        }

    }



}
