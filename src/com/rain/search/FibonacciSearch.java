package com.rain.search;

import java.util.Arrays;

/**
 * 斐波那契(黄金分割法)查找
 *
 * 黄金分割点是指把一条线段分割为两部分，使其中一部分与全长之比等于另一部分与这部分之
 * 取其前三位 数字的近似值是 0.618。由于按此比例设计的造型十分美丽，因此称为黄金分割，也称为中外比
 * 要求：待查找数组为有序数列
 *
 * 斐波那契数列
 * 特点：
 * 1.斐波那契数列 {1,1,2,3,5,8,13,21,34,55} 的两个相邻数 的比例，无限接近 黄金分割值 0.618
 * 2. F[k]=F[k-1]+F[k-2] ==> 斐波那契数列其中的一个元素的数值是其前两个元素数值的和
 *
 * 查找算法的思路
 * 斐波那契查找原理与前两种相似，仅仅改变了中间结点（mid）的位置
 * mid 不再是中间或插值得到，而是位 于黄金分割点附近，即 mid=low+F(k-1)-1（F 代表斐波那契数列）
 *
 * 1.由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质，可以得到 （F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1
 * 该式说明： 只要顺序表的长度为 F[k]-1，则可以将该表分成长度为 F[k-1]-1 和 F[k-2]-1 的两段
 * 2.类似的，每一子段也可以用相同的方式分割
 * 3.但待查找等的顺序表长度 n 不一定刚好等于 F[k]-1，所以需要将原来的顺序表长度 n 增加至 F[k]- 1
 * 注意：这里的 k 值只要能使得 F[k]-1 恰好大于或等于 n
 * 否则需要增加顺序表的长度，顺序表长度增加后，新增的位置（从 n+1 到 F[k]-1 位置）都赋为 n 位置的值即可
 *
 *
 */

public class FibonacciSearch {


    public static void main(String[] args) {


        int[] arr = {1,5,8,7,69,86,110,500,10000};

        System.out.println("index = " + fibSearch(arr, 5));

    }



    /**
     * 用非递归的方式得到一个斐波那契数列
     * @return
     */
    public static int[] fib() {
        int maxSize = 20;
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契算法
     * @param a 待查找的数组
     * @param key 需要查找的关键值
     * @return 返回对应的下标，如果没有则返回 -1
     */
    public static int fibSearch(int[] a, int key) {

        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标 ==> mid=low+F(k-1)-1 主要拿到该公式的 k
        int mid = 0; //存放mid值
        int f[] = fib(); //获取斐波那契数列
        //获取到一个与待查找数组大小相匹配的斐波那契的分割数值下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]的值可能大于 a 的长度因此我们需要使用Arrays类构造一个新的数组 并指向temp[]
        //该方法对于不足的部分默认补充 0
        int[] temp = Arrays.copyOf(a, f[k]);
        //重新补全的 0 全部更改为 原数组中的最后一个数
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //开始寻找 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                k--;
                //注解 ：k--
                //1. 全部元素 = 前面的元素 + 后边元素  2.f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k-- 即下次循环 mid = f[k-1-1]-1
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
                //注解 ： k -= 2
                // 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                // 即在f[k-2] 的前面进行查找 k -=2
                // 即下次循环 mid = f[k - 1 - 2] - 1
            } else { //找到
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;

    }






}
