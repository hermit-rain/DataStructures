package com.algorithm.kruskal;


import java.util.Arrays;

/**
 * 克鲁斯卡尔
 *
 * 1) 克鲁斯卡尔(Kruskal)算法，是用来求加权连通图的最小生成树的算法
 * 2) 基本思想：按照权值从小到大的顺序选择 n-1 条边，并保证这 n-1 条边不构成回路
 * 3) 具体做法：首先构造一个只含 n 个顶点的森林，然后依权值从小到大从连通网中选择边加入到森林中，并使森林中不产生回路，直至森林变成一棵树为止
 *
 *
 */

public class KruskalTest {


    private int edgeNum; //边的个数
    private char[] vertex; //顶点
    private int[][] matrix; //邻接矩阵
    private static final int INF = Integer.MAX_VALUE; // INF ==> 顶点非连通


    public static void main(String[] args) {

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalTest kruskalTest = new KruskalTest(vertex, matrix);
        kruskalTest.print();
        //测试克鲁斯卡尔
        kruskalTest.kruskal();

    }


    //定义构造器
    public KruskalTest(char[] vertex, int[][] matrix) {
        //初始化顶点
        int vlen = vertex.length;
        this.vertex = new char[vlen];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的个数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行冒泡排序
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }

    }

    /**
     * 返回顶点对应的下标
     *
     * @param ch 顶点字符 'A','B'
     * @return 如果找到则返回对应字符的下标 否则返回 -1
     */
    private int getPosition(char ch) {

        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从图结构中获取到边 放入 EData[] 数组中
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }


    /**
     * 获取指定下标顶点对应的终点
     * 终点 ==> 就是将所有顶点按照从小到大的顺序排列好之后 某个顶点的终点就是"与它连通的最大顶点"
     *
     * @param ends ends数组用于记录各个顶点对应的终点 起始为 0 在遍历的过程中不断改变更新
     * @param i    传入顶点对应的下标
     * @return 下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    // 克鲁斯卡尔算法 获取最小生成树
    public void kruskal() {

        int index = 0;
        //定义数组用于保存"已有最小生成树" 中各个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        EData[] rets = new EData[edgeNum]; //用于保存最后的最小生成树
        //获取图中所有边的集合
        EData[] edges = getEdges();
        System.out.println("图的边的集合为 = " + Arrays.toString(edges));
        //根据权值对边进行排序
        sortEdges(edges);
        for (int i = 0; i < edgeNum; i++) {
            //获取边的两个顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //获取两个顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            //判断是否构成回路
            if (m != n) {
                ends[m] = n; //设置m在"已有最小生成树" 中的终点
                rets[index++] = edges[i]; //将边加入到 rets 数组
            }

        }
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }

    }


}


//边类
class EData {
    char start; //边的一个点
    char end;  //边的另外一个点
    int weight; //边的权值

    //定义构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]";
    }

}
