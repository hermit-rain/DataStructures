package com.algorithm.dijkstra;


import java.util.Arrays;

/**
 * 迪杰斯特拉 ===> 最短路径
 * 用于计算一个结点到其他结点的最短路径
 * 它的主要特点是以 起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止。
 */


public class DijkstraAlgorithm {

    public static void main(String[] args) {

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; //表示不连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);
        //显示图的邻接矩阵
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dsj(1);
        //显示最终结果
        graph.showDijkstra();

    }

}

class Graph {

    private char[] vertex; //顶点
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv; //已经访问的顶点的集合


    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //显示结果
    public void showDijkstra() {
        vv.show();
    }



    private void update(int index) {
        int len = 0;

        for (int i = 0; i < matrix[index].length; i++) {
            // len ==> 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.isVisited(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index); //更新 i 顶点的前驱为 index
                vv.updateDis(i, len); // 更新出发顶点到 i 顶点的距离
            }

        }
    }


    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发顶点的下标
     */
    public void dsj(int index) {

        vv = new VisitedVertex(vertex.length, index);
        update(index); //更新 index 顶点到周围顶点的距离和前驱顶点
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr(); //选择并返回新的访问顶点
            update(index); //更新 index 顶点到周围顶点的距离和前驱顶点

        }
    }


}


//已访问顶点的集合
class VisitedVertex {

    public int[] already_arr; //记录是否访问过 1 => 访问过 0 => 未访问过
    public int[] pre_visited; //记录前一个顶点的下标
    public int[] dis; //记录从起始顶点到其他所有顶点的距离

    //定义构造方法
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1; //设置出发顶点被访问过
        this.dis[index] = 0; //设置出发顶点的访问距离为 0

    }

    /**
     * 判断 index 下标对应的顶点是否被访问过
     *
     * @param index 顶点的下标
     * @return 访问过则返回 true 否则返回 false
     */
    public boolean isVisited(int index) {
        return already_arr[index] == 1;
    }


    //更新出发点到当前顶点的距离
    public void updateDis(int index, int len) {
        dis[index] = len;
    }


    //更新 pre 这个顶点的前驱顶点为 index 顶点
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }


    //返回出发顶点到 index 顶点的距离
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问节点
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;

    }

    //显示最后的结果
    public void show() {

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.println(vertex[count] + "(" + i + ")");
            } else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();

    }



}




