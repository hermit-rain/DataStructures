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

        //测试解压字符串
        byte[] sourceBytes = huffmanCode.decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原始的字符串" + new String(sourceBytes));

//        //测试整合后的方法
//        byte[] sourceBytes1 = huffmanCode.huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(sourceBytes1));

//        //测试压缩文件
//        String srcFile = "C:\\Users\\hermi\\Desktop\\bing.jpg";
//        String dstFile = "C:\\Users\\hermi\\Desktop\\bing.zip";
//
//        huffmanCode.zipFile(srcFile, dstFile);
//        System.out.println("压缩文件成功 亲");

//        //测试文件解压
//        String zipFile = "C:\\Users\\hermi\\Desktop\\bing.zip";
//        String dstFile2 = "C:\\Users\\hermi\\Desktop\\bing.jpg";
//
//        huffmanCode.unZipFile(zipFile, dstFile2);
//        System.out.println("解压成功~");


    }


}
