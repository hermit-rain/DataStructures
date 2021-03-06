package com.rain.binarysorttree;

/**
 * 用于创建二叉树的 HeroNode节点
 */


public class HeroNode {

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


    /**
     * 前序遍历查找
     *
     * @param no 数组待查找节点的 no
     * @return 如果找到则返回该节点，没有找到则返回 null
     */
    public HeroNode preOrderSearch(int no) {
        //比较当前节点是不是
        System.out.println("进入前序遍历");
        if (this.no == no) {
            return this;
        }
        //判断当前节点是否为空 如果不为空则递归前序查找
        //如果左递归前序查找 找到该节点 则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //右递归前序查找 找到节点 则返回 否则继续判断
        //当前节点的右子节点是否为空 如果不空 则继续向右遍历查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {

        //定义一个返回节点作为判断
        HeroNode resNode = null;
        //从左子节点开始中序遍历
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        //判断根节点
        if (this.no == no) {
            return this;
        }
        //否则向右递归遍历
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }


    /**
     * 后序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        if (this.no == no) {
            return this;
        }
        return resNode;

    }


}
