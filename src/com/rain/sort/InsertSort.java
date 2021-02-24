package com.rain.sort;
import java.util.Arrays;

/**
 * 插入排序
 * 插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的
 *
 * 思路：
 * 把 n 个待排序的元素看成为一个有序表和一个无序表，开始时有 序表中只包含一个元素，
 * 无序表中包含有 n-1 个元素，排序过程中每次从无序表中取出第一个元素，把它的排
 * 序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 *
 */

public class InsertSort {

    public static void main(String[] args) {

        //测试
        int[] arr = {12,45,-5,99,0};

        System.out.println("排序前的算法" + Arrays.toString(arr));

        insertSort(arr);

        System.out.println("排序后的算法" + Arrays.toString(arr));

    }


    /**
     * 插入排序方法
     * @param arr 输入一个待排序的数组
     */
    public static void insertSort(int[] arr) {

        int insertVal = 0;  //定义待插入的数
        int insertIndex = 0;    //定义待插入数据前一个数据的下标

        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];     //定义待插入数
            insertIndex = i - 1;    //即arr[i]的前一个数的下标

            //给insertVal 找到插入的位置
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                //逐渐和前一个数据进行比较直到insertIndex == -1 跳出循环
                insertIndex--;
            }
            // 优化
            // 当退出while循环时，说明插入的位置找到, 此时需要插入的位置insertIndex + 1
            // 这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

        }

    }


}
