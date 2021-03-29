package com.algorithm.dac;

/**
 * 分治算法 ==> 汉诺塔问题
 * <p>
 * 简介
 * 字面上的解释是“分而治之”，就是把一个复杂的问题分成两个或更多的相同或相似的子问题
 * 再把子问题分成更小的子问题…… 直到最后子问题可以简单的直接求解，原问题的解即子问题 的解的合并
 * <p>
 * 分治法在每一层递归上都有三个步骤：
 * 1) 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
 * 2) 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
 * 3) 合并：将各个子问题的解合并为原问题的解
 * <p>
 * 汉诺塔思路
 * 1.如果有一个盘 A ==> C
 * 2.如果有 n >= 2 个盘 我们可以把所有的盘看成两个盘 即 最下面一个盘 ，上面所有为另一个盘
 * 3.先把最上面的盘 A ==> B
 * 4.把最下面的盘 A ==> C
 * 5.最后把 B塔的所以盘 B ==> C
 */

public class HanoiTower {

    public static void main(String[] args) {
        //测试
        hanoiTower(5, 'A', 'B', 'C');
    }



    //汉诺塔算法
    public static void hanoiTower(int num, char a, char b, char c) {

        if (num == 1) {
            System.out.println("第1个盘" + a + "->" + c);
        } else {
            //1. 先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hanoiTower(num - 1, a, c, b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘" + a + "->" + c);
            //3. 把B塔的所有盘 从 B->C , 移动过程使用到 a塔
            hanoiTower(num - 1, b, a, c);
        }

    }


}
