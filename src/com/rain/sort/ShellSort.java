package com.rain.sort;

import java.util.Arrays;


/**
 *
 * 希尔排序
 * 希尔排序是希尔（DonaldShell）于 1959 年提出的一种排序算法。
 * 希尔排序也是一种插入排序它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序
 *
 * 思路
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含 的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止
 *
 * 希尔排序有两种实现方式
 * 1)交换法实现（分组 + 冒泡交换）
 * 2)移动法实现 （分组 + 插入移动）  ==> 真正的希尔排序
 *
 */

public class ShellSort {

    public static void main(String[] args) {

        //测试
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前的数组" + Arrays.toString(arr));

//        shellSort(arr);
        shellSort2(arr);

        System.out.println("排序后的算法" + Arrays.toString(arr));

    }



    /**
     * 交换法实现希尔排序
     * @param arr
     */
    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组），步长为gap
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮" + Arrays.toString(arr));

        }

    }


    /**
     * 位移法实现希尔排序 ==>真正的希尔排序
     * @param arr
     */
    public static void shellSort2(int[] arr) {

        //增量gap 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素逐步的对所在组的元素进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];  //保存待插入的数据
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环的时候，就给temp找到插入的位置
                    arr[j] = temp;
                }

            }

        }


    }






}
