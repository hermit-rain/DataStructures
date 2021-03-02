package com.rain.search;

/**
 * 插值查找算法
 * 插值查找算法类似于二分查找，不同的是插值查找每次从自适应 mid 处开始查找
 * 改变折半查找中的求 mid 索引的公式即
 *
 * mid = left + (right - left) * (1 / 2) ====== > mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
 *
 * 说明
 * 1) 插值查找算法，也要求数组是有序的
 * 2) 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快
 * 3) 关键字分布不均匀的情况下，该方法不一定比折半查找要好
 *    因为 (findVal - arr[left]) / (arr[right] - arr[left]) 只有在分布均匀的情况下才能精确锁定值
 */

public class InsertValueSearch {


    public static void main(String[] args) {


//        //该测试数组不如二分查找算法快
//        int[] arr = {1, 2, 3, 45, 89, 99, 100, 265, 288, 500, 850, 855, 1000, 2000, 2001, 8000};

        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i + 1;
        }

        int index = insertValueSearch(nums, 0, nums.length -1, 100);

        System.out.println("index = " + index);

    }


    /**
     * 插值查找算法
     * @param arr 待查找的数组
     * @param left 左边索引 ==> 0
     * @param right 右边索引 即从数组最右侧元素下标开始 ==> arr.length - 1
     * @param findVal 查找值
     * @return 如果找到则返回该 元素下标 若没找到则返回 -1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 否则 mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //(findVal - arr[left]) / (arr[right] - arr[left]) ！==> 1/2  条件是该数组均匀分布
        //所以只有数组密集且均匀分布的时候该算法才优于二分查找
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }



}
