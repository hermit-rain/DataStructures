package com.rain.binarytree;

/**
 * 用于创建二叉树的 HeroNode节点
 */


public class HeroNode {

    private int no;
    private String name;
    private HeroNode left; //默认值为 null
    private HeroNode right; //默认值为 null

    //补充说明
    //因为线索二叉树的左右节点既可以是子树又可以是前驱节点或者后继节
    //所以我们定义两个新的节点来区分左右指针的类型
    // 0 == > 子树  1 ==> 前驱或者后继节点
    private int leftTye;
    private int rightType;


    public HeroNode(int no, String name) {
        this.name = name;
        this.no = no;
    }


    public int getLeftTye() {
        return leftTye;
    }

    public void setLeftTye(int leftTye) {
        this.leftTye = leftTye;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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


    /**
     * 二叉树删除节点
     * 规定
     * 1.如果待删除的节点是叶子节点，则删除节点
     * 2.如果删除的节点是非叶子节点 则删除该子树
     * 思路
     * 首先考虑如果树是空树root 如果只有一个 root 节点 则等价于将该二叉树置空
     * 1.因为当前的二叉树是单向的 所以我们只能判断当前的节点的子节点是否是待删除节点
     * 而不能判断当前节点是否为待删除节点 ===>类似于单向链表的删除
     * 2.如果当前节点的左子节点不为空 并且该左子节点就是要待删除的节点 == > this.left = null 并且返回 (结束递归删除)
     * 3.如果当前节点的右子节点不为空 并且 右子节点就是待删除的节点 ==> this.right = null 并且返回
     * 4.如果第二步和第三步 都没有删除节点 那么我们就需要向左子树进行递归删除
     * 5.如果第四步也没有删除 则应该向右子树进行递归删除
     *
     * @param no
     */
    public void delNode(int no) {

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }

    }




}
