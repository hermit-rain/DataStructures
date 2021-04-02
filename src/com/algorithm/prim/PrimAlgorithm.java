package com.algorithm.prim;

import java.util.Arrays;

/**
 * Prim算法 ==> 实现修路问题
 * <p>
 * 最小生成树
 * 给定一个带权的无向连通图,如何选取一棵生成树,使树上所有边上权的总和为最小
 * <p>
 * Prim算法思想
 * 普利姆算法求最小生成树，也就是在包含 n 个顶点的连通图中，找出只有(n-1)条边包含所有 n 个顶点的
 * 连通子图，也就是所谓的极小连通子图
 * <p>
 * 修路问题描述
 * 1) 有胜利乡有 7 个村庄(A,B,C,D,E,F,G) ，现在需要修路把 7 个村庄连通
 * 2) 各个村庄的距离用边线表示(权) ，比如 A – B 距离 5 公里
 * 3) 问：如何修路保证各个村庄都能连通，并且总的修建公路总里程最短?
 */

public class PrimAlgorithm {

    public static void main(String[] args) {

        //测试
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        //创建邻接矩阵的关系 10000 ==> 两个顶点之间不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };
        //创建图对象
        MGraph graph = new MGraph(vertex);
        graph.createGraph(graph, vertex, data, weight);
        //显示图
        graph.showGraph(graph);
        //prim 找到最小生成树
        prim(graph, 0);

    }


    /**
     * Prim 算法 ==> 得到最小生成树
     *
     * @param graph 传入图
     * @param v     最小生成树的起始顶点
     */
    public static void prim(MGraph graph, int v) {
        //定义visited 用于标记顶点是否已经访问过 默认0 => 未访问过 1 => 访问过
        int[] visited = new int[graph.vertex];
        //标记当前顶点
        visited[v] = 1;
        int h1 = -1, h2 = -1;
        int minWeight = 10000;

        for (int k = 1; k < graph.vertex; k++) { //prim算法结束后会返回 vertex - 1 条边

            for (int i = 0; i < graph.vertex; i++) { // i => 表示已经访问过的顶点
                for (int j = 0; j < graph.vertex; j++) { //j => 表示还没有访问过的顶点
                    //每次从已访问过的顶点出发到未访问过的顶点 找到权最小的边 并用 h1 h2 记录边顶点的信息
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值 ：" + minWeight);

            //每个循环出来后更新标记信息 并重置 minWeight
            visited[h2] = 1;
            minWeight = 10000;

        }

    }


}

//图

class MGraph {

    int vertex; //图的顶点
    char[] data; //存放顶点的数据
    int[][] weight; //图的邻接矩阵 ==> 存储边

    //定义构造器
    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }

    /**
     * 构建图结构
     *
     * @param graph  图对象
     * @param vertex 图对应的顶点个数
     * @param data   图中各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {

        for (int i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }

    }

    //显示图结构
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }


}
