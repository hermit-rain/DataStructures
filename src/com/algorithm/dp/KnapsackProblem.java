package com.algorithm.dp;


/**
 * 动态规划实现 背包问题(0/1)
 * <p>
 * 1) dp核心思想是：将大问题划分为小问题进行解决，从而一步步获取最优解的处理算法
 * 2) 动态规划算法与分治算法类似，其基本思想也是将待求解问题分解成若干个子问题先求解子问题，然后从这些子问题的解得到原问题的解。
 * 3) 与分治法不同的是，适合于用动态规划求解的问题，经分解得到子问题往往不是互相独立的( 即下一个子 阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解 )
 * 4) 动态规划可以通过填表的方式来逐步推进，得到最优解.
 * <p>
 * 0/1背包问题求解思路
 * 1.dp[i][0] = dp[0][j] = 0    第一行 第一列填入 0
 * 2.当 w[i] > j 时 ==> dp[i][j] = dp[i - 1][j]
 * 即当准备加入的新商品的容量大于当前背包的容量时 就直接将上面单元格的值写入当前的表格中
 * 3.j >= w[i] 时  dp[i][j] = max{dp[v - 1][j], val[i] + dp[i - 1][j - w[i]]}
 */

public class KnapsackProblem {


    public static void main(String[] args) {

        int[] w = {1, 4, 3}; //物品的重量
        int[] val = {1500, 3000, 2000}; //商品的价值
        int m = 4;  //背包的容量 4
        int n = val.length; //商品的数量
        //定义二维数组存储可实现的最大价值
        //dp[i][j]表示前i件物品体积不超过j的情况下能达到的最大价值
        int[][] dp = new int[n + 1][m + 1];
        //定义二维数组用于记录商品的情况
        int[][] path = new int[n + 1][m + 1];

        //因为初始化第一行和第一列默认是0 所以我们从 i = 1 开始处理
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (w[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    if (dp[i - 1][j] < val[i - 1] + dp[i - 1][j - w[i - 1]]) {
                        dp[i][j] = val[i - 1] + dp[i - 1][j - w[i - 1]];
                        //把当前的背包情况记录到path
                        path[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

        }
        //显示当前的dp矩阵
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.printf("%d\t", dp[i][j]);
            }
            System.out.println();
        }

        //显示结果集
        int i = path.length - 1;
        int j = path[0].length - 1;
        //逆序遍历path找到最终组合
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入背包");
                j -= w[i - 1];
            }
            i--;
        }


    }


}
