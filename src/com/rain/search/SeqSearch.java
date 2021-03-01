package com.rain.search;

/**
 * 线性查找
 * 从头到尾遍历数组 如果找到即返回 (数组可以是无序的)
 * 时间复杂度 O(n)
 */

public class SeqSearch {

    public static void main(String[] args) {

        int arr[] = {1, 9, 11, -1, 34, 89 };
        int index = seqSearch(arr, 9);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为" + index);
        }

    }

    /**
     * 线性查找方法
     * 这里我们实现的线性查找方法是找到满足条件的值即返回
     * @param arr 待查找的数组
     * @param value  待查找数组值中的数值
     * @return
     */
    public static int seqSearch(int[] arr, int value) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        //没有找到即返回 -1
        return -1;
    }





}
