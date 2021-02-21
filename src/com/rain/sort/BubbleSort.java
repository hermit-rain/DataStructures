package com.rain.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）,依次比较相邻元素的值
 * 若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒
 *
 * 优化：
 * 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序
 * 因此要在 排序过程中设置一个标志 flag 判断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排 序写好后，在进行)
 *
 */

public class BubbleSort {

    public static void main(String[] args) {

        //测试
        int[] arr = {3,9,-1,10,-5};

//        int[] nums = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            nums[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
//        }

        long fristTime =  System.currentTimeMillis();

        System.out.println("冒泡排序之前");
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);

        System.out.println("冒泡排序之后");
        System.out.println(Arrays.toString(arr));

        long lastTime = System.currentTimeMillis();

        System.out.println("排序所需要的时间" + (lastTime - fristTime));

    }

    /**
     * 冒泡排序方法
     * 1.一共进行 (arr.length - 1) 次大的循环
     * 2.每一趟排序的次数在逐渐的减少
     * 3.如果我们发现在某趟排序中，没有发生一次交换， 可以提前结束冒泡排序==>优化
     * @param arr 输入需要排序的数组
     */
    public static void bubbleSort(int[] arr) {

        //定义临时变量
        int temp = 0;
        boolean flag = false;   //标识常量，表示是否进行交换过 初始定义false没有交换

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i ; j++) {
                //如果前面的数比后面的数字大则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (!flag) {
                //如果在一趟大循环中一次都没有发生过 那么就跳出循环提前结束循环
                break;
            } else {
                // 重置 flag !!!!
                flag = false;
            }
        }
    }




}
