package com.rain.graph;

import java.util.ArrayList;
import java.util.Arrays;


//描述图

public class Graph {

    private ArrayList<String> vertexList; //存储定点的集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges; //表示某个节点是否被访问
    //用于出存储某个节点是否被访问
    private boolean[] isVisited;

    //定义构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    //返回图中顶点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //显示图的邻接矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回顶点对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回 v1 和 v2 的权值
    public int getWight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示顶点的下标
     * @param v2     第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}


