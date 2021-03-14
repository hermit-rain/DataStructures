package com.rain.huffmancode;

import java.util.List;

/**
 * 测试 赫夫曼 编码
 *
 */

public class Application {

    public static void main(String[] args) {

        HuffmanCode huffmanCode = new HuffmanCode();

        //待压缩字符
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("字符串的长度为：" + contentBytes.length);
        //统计每个字符的个数
        List<Node> nodes = huffmanCode.getNode(contentBytes);
        System.out.println(nodes);
        //创建 赫夫曼树
        Node huffmanTreeRoot = huffmanCode.createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.perOrder();

    }



}
