package com.rain.binarytree;

/**
 * 顺序存储二叉树
 * 从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组
 *
 * 顺序存储二叉树的特点:
 * 1) 顺序二叉树通常只考虑完全二叉树
 * 2) 第 n 个元素的左子节点为 2*n+1
 * 3) 第 n 个元素的右子节点为 2*n+2
 * 4) 第 n 个元素的父节点为 (n-1)/2
 * 5) n: 表示二叉树中的第几个元素(按 0 开始编号)
 *
 * 如：给定一个数组 {1,2,3,4,5,6,7}，要求以二叉树前序遍历的方式进行遍历  前序遍历的结果应当为 1,2,4,5,3,6,7
 *
 */
//编写一个 ArrayBinaryTree 实现顺序存储二叉树遍历

public class ArrBinaryTree {

    private int[] arr;

    //定义构造器
    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 顺序存储二叉树的前序遍历
     * 附：中序及后序遍历 只需要改变 index 的输出位置即可
     *
     * @param index 数组下标 起始下标为 0
     */
    public void preOrder(int index) {
        //如果数组为空 或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空 不能进行二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

}
