package com.rain.sort;


import java.util.Arrays;

/**
 * 基数排序
 * 1) 基数排序属于“分配式排序” 又称“桶子法”（bucket sort）或 binSort
 *    顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
 * 2) 基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
 * 3) 基数排序(RadixSort)是桶排序的扩展
 * 4) 基数排序是 1887 年赫尔曼·何乐礼发明的。它是这样实现的：将整数按位数切割成不同的数字，然后按每个位数分别比较
 *
 * 思路
 * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零
 * 然后，从最低位开始，依次进行一次排序。 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列
 *
 */


public class RadixSort {

    public static void main(String[] args) {

        int[] arr = {20,45,7,63,99,100,0,11};

        System.out.println("排序前的数组" + Arrays.toString(arr));

        radixSort(arr);

        System.out.println("排序后的数组" + Arrays.toString(arr));

    }

    /**
     * 基数排序方法
     * @param arr 待排序的数组
     */
    public static void radixSort(int[] arr) {

        //得到数组中最大的数的位数
        //找到最大的数
        int max = arr[0]; //假设第一位数就是最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组表示 10 个桶每个桶就是一个一维数组
        //说明
        //1.二维数组包含十个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 名明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序 即 一维数组的下标依次取出数据，放入原来数组
            int index = 0;
            //遍历每一桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(即第k个一维数组), 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！bucketElementCounts[k] = 0;
                bucketElementCounts[k] = 0;

            }
            System.out.println("第"+(i+1)+"轮的排序处理 arr =" + Arrays.toString(arr));

        }

    }






}
