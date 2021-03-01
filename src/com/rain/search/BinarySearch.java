package com.rain.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 注意： 使用二分查找的前提是 该数组是有序的
 *
 * 思路
 * 1.首先确定该数组的中间下标 mid = (left + right) / 2;
 * 2.然后让需要查找的数 findVal 和 arr[mid] 进行比较
 *  2.1 findVal > arr[mid] 说明你要查找的数在 mid 的右边，因此需要向右递归查找
 *  2.2 findVal < arr[mid] 说明你要查找的数在 mid 的左边，因此需要向左递归查找
 *  2.3 findVal == arr[mid] 说明找到 就返回
 *
 * 递归结束的条件
 * 1.找到即结束
 * 2.递归完整的数组，让然没有找到findVal,也需要结束递归 ==> left > right 即退出
 */

public class BinarySearch {


    public static void main(String[] args) {

        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int nums[] = {0, 1, 2, 3, 4, 5, 6, 6, 6, 7, 8, 9, 10};

        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        List<Integer> resIndexList = binarySearch2(nums, 0, nums.length - 1, 6);

        System.out.println("resIndex = " + resIndex);
        System.out.println("reIndexList = " + resIndexList);

    }

    /**
     *
     * @param arr 待查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //当遍历了整个数组都没有找到待查找的值便退出递归
        if (left > right) {
            return -1;
        }
        //定义中间索引
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }


    /**
     * 拓展
     * 若一个有序数组中有多个相同的数值的时候，返回待查找数据的所有下标
     * 例如 {1,4,8,56,100,100,100,145,789} 返回数值为 100 的所有下标
     *
     * 思路
     * 1.在找到 mid 索引值时不要马上返回
     * 2.向 mid 索引的左边扫描 将所有 与 mid 相同的元素的下标 加入到 list集合
     * 3.向 mid 索引的右边扫描 将所有 与 mid 相同的元素的下标 加入到 list集合
     * 4.将 ArrayList 集合返回
     *
     * 注：
     * 因为是有序数组所以相同数据出现的位置一定在相邻的区域
     * 所以从 mid 开始左右扫描直到遇到 != mid 的元素出现即可退出扫描 相比于全盘扫描降低了复杂度
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        //定义一个list集合，找到 mid 索引后开始 左右扫描 将与 mid 相同的元素下标加入到集合中
        List<Integer> resIndexList = new ArrayList<Integer>();

        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //找到 mid 之后 向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }
            //将 mid 本身 加入到集合
            resIndexList.add(mid);
            //向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }

            return resIndexList;
        }

    }






}
