package com.rain.sort;

import java.util.Arrays;


/**
 * 快速排序
 * 快速排序（Quicksort）是对冒泡排序的一种改进。
 * 基本思想是
 * 分治 + 冒泡 + 递归
 * 1.在待排序的元素任取一个元素作为基准(通常选第一个元素，但最的选择方法是从待排序元素中随机选取一个作为基准)，称为基准元素；
 * 2.将待排序的元素进行分区，比基准元素大的元素放在它的右边，比其小的放在它的左边
 * 3.对左右两个分区重复以上步骤直到所有元素都是有序的
 */

public class QuickSort {

    public static void main(String[] args) {

        //测试
        int[] arr = {10,5,3,1,7,2,8};
        System.out.println("排序前的数组" + Arrays.toString(arr));

        quickSort(arr,0,arr.length - 1);

        System.out.println("排序后的数组" + Arrays.toString(arr));

    }


    /**
     * 快速排序方法
     * @param arr   待排序的数组
     * @param L 待排序数组的第一个元素的下标
     * @param R 待排序数组的最后一个元素的下标
     */
    public static void quickSort(int arr[], int L, int R) {
        int left = L;
        int right = R;
        int temp = 0;
        if (left <= right) {   //待排序的元素至少有两个的情况
            temp = arr[left];  //待排序的第一个元素作为基准元素
            while (left != right) {   //从左右两边交替扫描，直到left = right
                while (right > left && arr[right] >= temp) {
                    right--;     //先从右往左扫描，找到第一个比基准元素小的元素
                }
                //找到这种元素arr[right]后与arr[left]交换 相当于从arr[right] 处挖坑，将该元素填充到当前基准元素处
                arr[left] = arr[right];
                while (left < right && arr[left] <= temp) {
                    left++; //之后从左往右扫描，找到第一个比基准元素大的元素
                }
                //找到这种元素arr[left]后，与arr[right]交换 ==> 将该arr[left]元素 填充到 之前arr[right] 挖好的坑中，之后反复填坑
                arr[right] = arr[left];
            }
            arr[right] = temp;    //基准元素归位 ==> 将基准元素填充到最后一个坑中
            quickSort(arr, L, left - 1);  //对基准元素左边的元素进行递归排序
            quickSort(arr, right + 1, R);  //对基准元素右边的进行递归排序
        }
    }



}
