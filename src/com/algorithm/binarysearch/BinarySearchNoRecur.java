package com.algorithm.binarysearch;

/**
 * 二分查找的非递归实现
 */

public class BinarySearchNoRecur {


    public static void main(String[] args) {

        //测试
        int[] arr = {1, 2, 4, 5, 6, 7, 9, 15};
        int index = binarySearch(arr, 4);
        System.out.println("index = " + index);

    }


    /**
     * 二分查找非递归实现
     *
     * @param arr    待查找的排序数组
     * @param target 待查找元素
     * @return 返回查找元素对应的下标 没有则返回 -1
     */
    public static int binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


}
