package com.algorithm.horse;

/**
 * 马踏棋盘问题 ==> 回溯 + 贪心
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {


    private static int X; //棋盘的列数
    private static int Y; //棋盘的行数
    private static boolean visited[]; //记录各个位置是否被访问过
    private static boolean finished; //记录游戏是否成功


    public static void main(String[] args) {

        //测试
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];
        //计算耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");
        //棋盘的最终情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }


    /**
     * 根据当前的位置计算的卢还能走那些位置 并放入到集合中(最多有八个位置)
     *
     * @param curPoint 的卢马的当前位置
     * @return
     */
    private static ArrayList<Point> next(Point curPoint) {

        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //的卢马可以做的位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }


    //优化 ==> 贪心
    //根据当前这步的所有下一步的选择位置 进行非递减排序 减少回溯的次数
    public static void sort(ArrayList<Point> ps) {

        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取 o1 o2 的下一步的所有位置个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }

            }
        });

    }


    /**
     * 骑士周游方法
     *
     * @param chessboard 棋盘
     * @param row 的卢马当前的位置行
     * @param column 的卢马当前的位置列
     * @param step 第几步 起始位置就是第一步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {

        chessboard[row][column] = step;
        visited[row * X + column] = true; //标记该位置已经访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x,step + 1);
            }

        }
        //判断的卢马是否全部踏过棋盘
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }





    }




}
