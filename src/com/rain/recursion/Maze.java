package com.rain.recursion;


/**
 * 递归 ==> 迷宫回溯问题
 *
 * 递归的规则
 * 1) 执行一个方法时，就创建一个新的受保护的独立空间(栈空间)
 * 2) 方法的局部变量是独立的，不会相互影响, 比如 n 变量
 * 3) 如果方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.
 * 4) 递归必须向退出递归的条件逼近，否则就是无限递归,出现 StackOverflowError，死龟了:)
 * 5) 当一个方法执行完毕，或者遇到 return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕
 */

public class Maze {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int [][] map = new int[8][7];
        //使用1来表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板用1表示
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("地图的情况为");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + "\t");
            }
            System.out.println();
        }

        //使用递归给小球找路
        setWay(map,1,1);

        //输出新的地图，小球走过，并标记走过的递归
        System.out.println("小球走过并标记过的地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j]+"\t");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小球找路
     * 1. map 表示地图
     * 2. i,j 表示从地图的哪个位置开始出发 (1,1)
     * 3. 如果小球能到 map[6][5] 位置，则说明通路找到.
     * 4. 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
     * 5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
     * @param map 地图
     * @param i 从那个位置开始找
     * @param j
     * @return 如果找到通路返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] ==2) {//通路已经找到了
            return true;
        } else {
            if(map[i][j] == 0) {//如果当前这个节点还没有走过
                //走法策略 下=>右=>上=>左
                map[i][j] = 2;  //假设该店可以走通过
                if (setWay(map,i+1, j)) {//向下
                    return true;
                } else if (setWay(map, i, j+1)) {//向右
                    return true;
                } else if (setWay(map, i-1, j )) {//向上
                    return true;
                } else if (setWay(map, i, j-1)) {//向左
                    return true;
                } else{
                    //说明该点走不通即死路
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }




}
