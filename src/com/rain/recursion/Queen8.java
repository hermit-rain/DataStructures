package com.rain.recursion;

/**
 * 八皇后问题 （递归回溯问题）
 * 问题描述：
 * 该问题是国际西洋棋棋手马克斯·贝瑟尔于 1848 年提出：在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击
 * 即：任意两个皇后都不能处于同一行、 同一列或同一斜线上，问有多少种摆法(92)
 *
 * 理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法， 用一个一维数组即可解决问题.
 * arr[8]= {0,4,7,5, 2,6,1,3}//对应 arr 下标 表示第几行，即第几个皇后 arr[i] =val
 * val 表示第 i+1 个皇后，放在第 i+1 行的第 val+1 列
 *
 */

public class Queen8 {
    //定义max 表示共有多少个皇后
    int max = 8;
    //定义数组array 保存皇后放置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count =  0;
    static int judgeCount = 0;


    public static void main(String[] args) {

        //测试
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共的解法种数" + count);
        System.out.printf("一共判断的次数为%d次",judgeCount);

    }


    /**
     * 查看当我们放置第n个皇后后，就去检测该皇后是否和前面已经摆放的皇后冲突
     * 1.array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
     * 2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
     *    （即求斜率 k = (y2-y1)/(x2-x1),当斜率为1即在同一斜线上）
     * 3.因为n每次都在递增，所以没有必要判断是否在同一行
     * @param n
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 将皇后摆放的位置输出
     */
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    /**
     * 放置第n个皇后
     * 注意： check 是每一次递归时，进入到check中都有for(int i = 0; i< max; i++),因此产生回溯
     * @param n
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n ，放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                //不冲突即接着放n+1个皇后，并开始递归
                check(n+1);
            }
            //如果冲突，就继续执行 array[n] = i,即将第n个皇后，放置在本行得 后移的一个位置
        }
    }


}
