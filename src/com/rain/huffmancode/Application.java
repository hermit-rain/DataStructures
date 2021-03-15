package com.rain.huffmancode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 测试 赫夫曼 编码
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

        //测试生成 每个字节对应的 赫夫曼编码
        Map<Byte, String> huffmanCodes = huffmanCode.getCodes(huffmanTreeRoot);
        System.out.println("赫夫曼编码为 ：" + huffmanCodes);

        //测试压缩字符串
        byte[] huffmanCodeBytes = huffmanCode.zip(contentBytes, huffmanCodes);
        System.out.println("压缩后得到的十进制数组为" + Arrays.toString(huffmanCodeBytes));


    }



}
