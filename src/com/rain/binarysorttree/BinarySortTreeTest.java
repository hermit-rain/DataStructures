package com.rain.binarysorttree;

/**
 * 二叉排序树
 *
 * 给定数组创建二叉排序树
 * BST:对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大
 *
 * 二叉排序树的删除
 * 1.删除叶子节点
 * 2.删除只有一棵子树的节点
 * 3.删除有两棵子树的节点
 */


public class BinarySortTreeTest {

    public static void main(String[] args) {

        //测试
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        //循环添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树 ~");
        binarySortTree.infixOrder();

        //删除节点
        binarySortTree.delNode(7);

        System.out.println("root = " + binarySortTree.getRoot());

        System.out.println("删除节点后的中序遍历为=====>");
        binarySortTree.infixOrder();

    }

}

//创建二叉排序树
class BinarySortTree {

private Node root;


    //返回根节点
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
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空 不能遍历");
        }
    }

    /**
     * 查找待删除的节点
     *
     * @param value 输入待查找节点的值
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找待删除节点的父节点
     *
     * @param value 待查找节点的值
     * @return
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 找到并返回以 node 为根节点的二叉排序树的最小节点的值
     *
     * @param node 二叉排序树的根节点
     * @return 返回该二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {

        Node target = node;
        //循环查找左子节点 ==> 因为左子节点一定小于根节点和右子节点
        while (target.left != null) {
            target = target.left;
        }
        //跳出循环时  target == > 值最小的节点
        delNode(target.value);
        return target.value;
    }


    /**
     * 删除节点
     *
     * @param value 待删除节点的值
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //找到待删除的节点
            Node targetNode = search(value);
            //如果没有找到
            if (targetNode == null) {
                return;
            }
            //当该二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到待删除节点 targetNode 的父节点
            Node parent = searchParent(value);
            //如果待删除节点为叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断 targetNode 是 parent 的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //待删除节点有两棵子树的节点
                //在右子树中找到最小的值的节点删除 并将值与当前节点的值进行替换
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                //删除只有一棵子树的节点
                //如果待删除节点有一个左子节点
                if (targetNode.left != null) {
                    //考虑到 树的度为 2 ==> 待删除节点为根节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    //如果待删除的节点只有一个右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

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

    /**
     * 查找要删除的节点
     *
     * @param value 待删除节点的值
     * @return 如果找到则返回该节点 否则返回 null
     */
    public Node search(int value) {

        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            //如果待查找节点的值小于当前节点 则向左遍历查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果待查找节点的值大于当前节点的值 向右子树查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找待删除节点的父节点
     *
     * @param value 待查找节点的值
     * @return 如果找到则返回待删除节点的父节点 否则就返回 null
     */
    public Node searchParent(int value) {

        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果待查找的值小于当前节点的值 并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null;
            }
        }

    }




}




