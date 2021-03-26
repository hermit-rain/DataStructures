package com.rain.graph;

import java.util.ArrayList;
import java.util.Arrays;


//描述图

public class Graph {

    private ArrayList<String> vertexList; //存储顶点的集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges; //定义边的条数
    //用于出存储某个节点是否被访问
    private boolean[] isVisited;


    public static void main(String[] args) {

        //测试
        int n = 5;
        String[] Vertexs = {"A", "B", "C" ,"D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边 ==> A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        //显示图的邻接矩阵
        graph.showGraph();

        //返回边的个数 5
        System.out.println(graph.getNumOfEdges());

        //返回指定下标对应的顶点
        System.out.println(graph.getValueByIndex(1));

        //返回 A B 对应的权值
        System.out.println(graph.getWight(0, 1));

        //深度优先搜索
        System.out.println("深度优先搜索 ==> ");
        graph.dfs();

    }



    //定义构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0; //初始化边的个数
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

    //返回指定下标对应的顶点的字符
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回 v1 和 v2 的权值
    public int getWight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示顶点的下标
     * @param v2     第二个顶点对应的下标
     * @param weight 边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 返回当前顶点对应的第一个邻接顶点的下标
     *
     * @param index 输入当前节点的下标
     * @return 如果存在即返回目标节点的下标 否则 返回 -1
     */
    public int getFirstNeighbor(int index) {

        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 回溯 根据前一个顶点的下标获取 下一个邻接顶点的下标 B - D
     *
     * @param v1 前一个顶点的下标
     * @param v2 当前顶点
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先搜索
     *
     * @param isVisited 传入一个描述顶点是否已经被访问过的数组
     * @param i         传入遍历的起始顶点
     */
    private void dfs(boolean[] isVisited, int i) {

        System.out.println("访问顶点：" + getValueByIndex(i));
        //将节点设置为已经访问
        isVisited[i] = true;
        //查找顶点i的第一个邻接顶点 w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果 w 已经被访问过了 ==> 开始回溯
            w = getNextNeighbor(i, w);
        }

    }

    //对 dfs 进行重载
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }

    }









}


