package com.rain.huffmantree;


/**
 * 测试赫夫曼树相关方法
 */

public class Application {

    public static void main(String[] args) {

        //测试创建 赫夫曼树
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        HuffmanTree huffmanTree = new HuffmanTree();

        Node root = huffmanTree.createHuffmanTree(arr);
        //前序遍历输出赫夫曼树
        huffmanTree.preOrder(root);


    }


}
