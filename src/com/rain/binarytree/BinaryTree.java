package com.rain.binarytree;

/**
 * 二叉树
 * 涉及 二叉树的 创建 遍历 查找 删除
 * <p>
 * 1) 前序遍历: 先输出父节点，再遍历左子树和右子树
 * 2) 中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
 * 3) 后序遍历: 先遍历左子树，再遍历右子树，最后输出父节
 * <p>
 * 树的常用术语
 * 节点  根节点  父节点  子节点 叶子节点 (没有子节点的节点)  节点的权(节点值) 路径(从 root 节点找到该节点的路线)
 * 层 子树  树的高度(最大层数) 森林 (多颗子树构成森林)
 * <p>
 * 树存储方式的分析
 * 能提高数据存储，读取的效率, 比如利用 二叉排序树(BinarySortTree)
 * 既可以保证数据的检索速度，同时也 可以保证数据的插入，删除，修改的速度
 */

//定义一个 BinaryTree 二叉树

public class BinaryTree {

    private HeroNode root; //根节点


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


    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }

    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root 节点那么这里就直接判断 root 是否为待删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树 不能删除啊亲~");
        }
    }


}






