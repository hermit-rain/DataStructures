package com.rain.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法
 * 该算法采用经典的分治（divide-and-conquer） 策略
 *
 * 思路： 先分后治
 * 先将整个待排序的数组向下分割成最小块，然后将最小单元按照排序规则向下逐渐进行组合
 * 时间复杂度 O(n - 1) 空间复杂度 O(1)
 *
 */

public class MergeSort {

    public static void main(String[] args) {

        //测试
        int[] arr = {8, 4, 5, 0, 1, -25, 6, 2};

        System.out.println("排序前的数组" + Arrays.toString(arr));

        //归并排序需要 一个额外的空间
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println("排序后的数组" + Arrays.toString(arr));

    }



    /**
     * 将数组数据分解 并加入合并算法
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {

        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //从栈顶开始合并 一共合并 arr.length - 1 次
            merge(arr, left, mid, right, temp);

        }
    }

    /**
     * 归并排序中的合并的方法
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int [] temp) {

        int i = left;   //初始化 i 左边有序序列的初始索引
        int j = mid + 1; //初始化 j 右边的有序的序列的初始索引
        int t = 0;  //指向temp 数组的当前索引

        //1.将左右两边有序数据按照规则填充到temp数组中
        //直到左右两边的有序数列有一彼岸处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边有序序列的当前元素小于等于右边的有序序列的当前元素
            //即将左边的当前元素 填充到 temp数组中
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { //反之 将右边的有序序列的当前元素 填充到 temp 数组中 =
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //2.将有剩余数据的一边的数据全部填充到temp
        while (i <= mid) { //左边的有序序列还有剩余数据，就将全部的数据填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) { //右边的有序序列还有剩余数据，就将全部的数据填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //3. 将temp数组的元素拷贝到arr  注意 并不是每次都拷贝所有==>从左向右从上到下
        t = 0;
        int tempLeft = left;
        //第一次合并 tempLeft = 0, right = 1; 第二次 tempLeft = 2, right = 3;...
        // 最后一次 tempLeft = 0, right = 7;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }




}
