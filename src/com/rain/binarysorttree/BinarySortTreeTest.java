package com.rain.binarysorttree;


/**
 * 二叉树遍历应用实例  ===> 前序,中序,后序
 *
 * 1) 前序遍历: 先输出父节点，再遍历左子树和右子树
 * 2) 中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
 * 3) 后序遍历: 先遍历左子树，再遍历右子树，最后输出父节
 *
 * 树的常用术语
 * 节点  根节点  父节点  子节点 叶子节点 (没有子节点的节点)  节点的权(节点值) 路径(从 root 节点找到该节点的路线)
 * 层 子树  树的高度(最大层数) 森林 (多颗子树构成森林)
 *
 * 树存储方式的分析
 * 能提高数据存储，读取的效率, 比如利用 二叉排序树(BinarySortTree)
 * 既可以保证数据的检索速度，同时也 可以保证数据的插入，删除，修改的速度
 *
 *
 */


public class BinarySortTreeTest {

    public static void main(String[] args) {
        //首先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "jay");
        HeroNode node1 = new HeroNode(2,"jj");
        HeroNode node2 = new HeroNode(3, "eve");
        HeroNode node3 = new HeroNode(4, "李健");
        HeroNode node4 = new HeroNode(5, "许嵩");

        //手动创建二叉树
        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node3);
        node2.setRight(node4);

        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.perOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

    }


}

//定义一个 BinaryTree 二叉树

class BinaryTree {

    private HeroNode root; //根节点

    //定义构造器
    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //前序遍历
    public void perOrder() {
        if (this.root != null) {
            this.root.perOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }


}


//创建一个HeroNode节点
class HeroNode{

    private int no;
    private String name;
    private HeroNode left; //默认值为 null
    private HeroNode right; //默认值为 null

    public HeroNode(int no, String name) {
        this.name = name;
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void perOrder() {
        System.out.println(this); //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.perOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.perOrder();
        }
    }


    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }



}

