package com.rain.huffmantree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 * <p>
 * 介绍
 * 给定 n 个权值作为 n 个叶子结点，构造一棵二叉树，若该树的带权路径长度(wpl)达到最小
 * 称这样的二叉树为 最优二叉树，也称为哈夫曼树(HuffmanTree), 或者霍夫曼树
 * <p>
 * 性质
 * 结点的带权路径长度为：从根结点到该结点之间的路径长度与该结点的权的乘积
 * 树的带权路径长度：树的带权路径长度规定为所有叶子结点的带权路径长度之和，记为 WPL(weighted path length)
 * 权值越大的结点离根结点越近的二叉树才是最优二叉树 ， WPL 最小的就是赫夫曼树
 * <p>
 * 赫夫曼树创建思路
 * <p>
 * 1) 从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * 2) 取出根节点权值最小的两颗二叉树
 * 3) 组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4) 再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复 1-2-3-4 的步骤，直到数列中所有的数据都被处理，就得到一颗赫夫曼树
 */

public class HuffmanTree {


    /**
     * 前序遍历
     *
     * @param root 传入根节点
     */
    public void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空不能遍历阿");
        }

    }

    /**
     * 创建赫夫曼树的方法
     *
     * @param arr 待创建成赫夫曼树的数组
     * @return 创建好后的赫夫曼树的 root 节点
     */
    public static Node createHuffmanTree(int[] arr) {

        //遍历arr 将每个元素构成一个 Node 并存入ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            System.out.println("node = " + nodes);

            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从 ArrayList 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将 parent 加入到 nodes
            nodes.add(parent);
        }

        //将 parent 加入到 nodes
        return nodes.get(0);

    }





}
