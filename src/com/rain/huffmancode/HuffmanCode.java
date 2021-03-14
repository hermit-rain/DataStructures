package com.rain.huffmancode;


import java.util.*;

/**
 * 赫夫曼编码
 * 赫夫曼编码也翻译为 哈夫曼编码 是一种编码方式  属于一种程序算法
 * 赫夫曼编码是赫哈夫曼树在电讯通信中的经典的应用之一
 * 赫夫曼编码广泛地用于数据文件压缩
 * 赫夫曼树是无损压缩
 * 前缀编码  字符的编码都不能是其他字符编码的前缀
 * <p>
 * 数据压缩
 * 定长编码   ==> 采用ASCII编码的方式转换字符  待存储的二进制数据量较大
 * 变长编码   ==> 通过统计每个字符出现的次数来进行编码  解决压缩问题但是无法避免解码时多义性的问题
 * 赫夫曼编码 ==> 先统计字符出现的个数然后按照权值创建赫夫曼树，然后通过左右节点分别取 0 1 进行编码
 * 因为每个字符的权值都对应一个叶子节点，每一个字符对应的路径都是不同的 所以解决了解码多义性的问题
 * <p>
 * 思路
 * 1.传入一个字符串 ===> i like like like java do you like a java
 * 2.对每个字符进行统计  d:1y:1u:1j:2 v:2 o:2 l:4 k:4 e:4i:5 a:5 :9
 * 3.按照上面字符出现的次数构建一颗赫夫曼树, 次数作为权值
 * 4.根据赫夫曼树，给各个字符,规定编码 (前缀编码)， 向左的路径为 0 向右的路径为 1
 * 5.根据取得的赫夫曼编码 变可得到输入字符串的 对应的字符编码了
 */

public class HuffmanCode {


    /**
     * 前序遍历
     *
     * @param root
     */
    private static void perOrder(Node root) {
        if (root != null) {
            root.perOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }


    /**
     * 统计字符数组中每个字符出现的次数
     *
     * @param bytes 接受字符数组
     * @return 返回 List集合  ==> [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......]
     */
    public List<Node> getNode(byte[] bytes) {

        //1.创建一个 Node 类型的 ArrayList 集合
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历 bytes 统计 每一个 byte 出现的次数
        Map<Byte, Integer> counts = new HashMap<>();

        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                //Map集合中还没有该字符 就直接存入
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //将每一个键值对转换成一个Node 对象 并加入到 nodes 集合中
        //遍历 map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;

    }

    /**
     * 通过 list 创建赫夫曼树
     *
     * @param nodes 接受的 List <Node> 集合
     * @return
     */
    public Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {

            //以从小到大的顺序进行排序
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点 没有data, 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已经处理的两棵二叉树从 nodes 中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);

    }


}
