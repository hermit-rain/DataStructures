package com.rain.sparsearray;

/**
 *一个五子棋程序实现的底层数据结构————即二维数组与稀疏数组之间的转化
 *
 * 二维数组 转 稀疏数组
 * 1.遍历原始的二维数组，得到有效的数据的个数sum
 * 2.根据sum就可以创建稀疏数组 sparseArr int[sum+1][3]
 * 3.将二维数组的有效数据存入到 稀疏数组中
 * 稀疏数组 转 二维数组
 * 1.先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组
 * 2.然后读取稀疏数组后几行的数据，并赋给原始的二维数组
 */

public class SparseArray {

    public static void main(String[] args) {
        //创建一个11*11的二维数组 0:没有棋子 1：黑子 2：蓝子
        int chessArr1[][] = new int[11][11];

        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;

        System.out.println("原始的二维数组为：");
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                System.out.printf("%d\t", chessArr1[i][j]);
            }
            System.out.println();
        }
        // 通过for Each 遍历数组
//        for (int[] row : chessArr1) {
//            for (int data : row) {
//                System.out.printf("%d\t",data);
//            }
//            System.out.println();
//        }

        //1.先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏矩阵
        int sparseArr[][] = new int[sum+1][3];

        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组并将有效数据存入到稀疏数组中
        //count用于记录第几个有效的数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组为:");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
            System.out.println();
        }

        System.out.println("将稀疏数组恢复成原始的二维数组");

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for  (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("恢复后的二维数组为：");

        for (int [] row : chessArr2) {
            for (int date : row) {
                System.out.printf("%d\t",date);
            }
            System.out.println();
        }

    }

}
