package com.rain.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * 选择式排序也属于内部排序法
 * 从欲排序的数据中，按指定的规则选出某一元素，再依规定交换位置后达到排序的目的
 * 选择排序时间复杂度是 O(n^2)
 * 说明：
 * 1.选择排序一共有 数组大小 - 1 轮排序
 * 2.每一轮排序又是一个循环
 * 2.1 先假定当前的这个数是最小值
 * 2.2 然后和后面的每个数进行比较，如果发现有比当前数更小的数就重新确定最小数，并得到下标
 * 2.3 当遍历到数组的最后时，就得到了本轮的最小数和下标
 * 2.4 交换然会继续循环
 *
 */

public class SelectSort {

    public static void main(String[] args) {

        //测试
        int[] nums = {100,-25,45,88,3,26};

        System.out.println("排序前的数组" + Arrays.toString(nums));

        selectSort(nums);

        System.out.println("排序后的数组" + Arrays.toString(nums));

//        //测试速度
//        //创建要给80000个的随机的数组
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
//        }
//
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
//
//        Date data1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(data1);
//        System.out.println("排序前的时间是=" + date1Str);
//
//        selectSort(arr);
//
//        Date data2 = new Date();
//        String date2Str = simpleDateFormat.format(data2);
//        System.out.println("排序前的时间是=" + date2Str);

    }


    /**
     * 选择排序
     * @param arr 输入一个待排序的数组
     */
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;   //用于保存下标
            int min = arr[i];   //用于记录最小的数据
            for (int j = i + 1; j < arr.length; j++) {
                //此处修改符号则变更为从大到小输出
                if (min > arr[j]) { //说明假定的最小值并不是最小的
                    min = arr[j];   //重置min
                    minIndex = j;   //重置minIndex
                }
            }
            //将小值放到前面arr[i]处
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }




}
