package com.rain.huffmancode;

import java.io.*;
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
    public static List<Node> getNode(byte[] bytes) {

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
     * @param nodes 接收的 List <Node> 集合
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {

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


    //获取每个叶子节点对应的赫夫曼编码
    //赫夫曼编码存放到 Map<Byte, String> 中 ===>  {32=01, 97=100, 100=11000, 117=11001, 101=1110 ...}
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //在生成赫夫曼编码遍历的过程中需要不断的拼接字符 ==> 拼接 0 1 ..
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入的 node 节点的所有叶子节点的赫夫曼编码得到 并存放到 huffmanCodes 集合中
     *
     * @param node          传入节点
     * @param code          路径 左子节点 0 右 子节点 1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code (0 1) 加入到stringBuilder2 中
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前节点是叶子节点还是非叶子节点
            if (node.data == null) {
                //非叶子节点即 向左右进行递归处理 ==> 逐渐添 0 1
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //如果是一个叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //重载 getCodes方法
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }


    /**
     * 通过生成好的 赫夫曼编码 Map<Byte, String> 将原始的 byte[] 进行压缩
     * 将原始的字符按照 赫夫曼编码 方式进行编码
     * 并将已经通过编码后的整个 0 1 字符串 按照每 8 bit 一组 转换成对应的十进制的形式 并存储到数组中
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     *
     * @param bytes        原始字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码 map
     * @return 返回经过赫夫曼编码处理后的 byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder stringBuilder = new StringBuilder();
        //遍历 byte 数组==> 字符串的赫夫曼编码
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //获取要返回十进制数组的长度
        int len = (stringBuilder.length() + 7) / 8;
        int index = 0; //用于记录 byte 的编号
        //创建压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        //每 8 位一组进行 转换
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                //如果不够 8 位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;

    }

    /**
     * 将一个 byte 字节 转换成一个二进制的字符串
     *
     * @param flag 标志是否需要进行补高位 true ==> 需要补高位 false ==> 不需要补高位  如果是最后一个字节 无需补高位
     * @param b    传入的 byte
     * @return byte字节 对应的二进制字符
     */
    public static String byteToBitString(boolean flag, byte b) {
        //定义一个变量来保存byte
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        //返回 temp 对应的二进制补码
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }


    /**
     * 对经过赫夫曼编码进行压缩的数据进行解压
     * 1.首先将 huffmanCodeBytes [-88, -65, -56, -65, -56, -65...] 转换成赫夫曼编码对应的二进制编码 "1010100010111..."
     * 2.通过 赫夫曼编码字符表Map<Byte, String> 将得到的二进制编码字符 转换成 对应的字符串 "i like like like java do you like a java"
     *
     * @param huffmanCodes 赫夫曼编码表 Map<Byte, String>
     * @param huffmanBytes 赫夫曼编码压缩得到的字节数组 Byte {-88, ...}
     * @return 压缩前字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        //先得到 huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是否为最后一个字符
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //将 赫夫曼编码表的 key - value 对进行调换 == > value - key
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建存储 byte 的集合
        List<Byte> list = new ArrayList<>();
        //i ==> 相当于索引 扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //递增的取出 key
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) { //没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //退出for 之后 list ==> "i like like like java do you like a java"
        //把 list 中的数据放入到 byte[]中
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }

    /**
     * 将赫夫曼压缩用到的方法进行整合
     * 返回压缩后编码的十进制字符 -88 ...
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 赫夫曼编码处理后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {

        List<Node> nodes = getNode(bytes);
        //根据 nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码 得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;

    }


    /**
     * 压缩文件
     *
     * @param srcFile 传入待压缩文件的全路径
     * @param dstFile 输出文件压缩后的文件路径
     */
    public static void zipFile(String srcFile, String dstFile) {

        OutputStream os = null;
        ObjectOutputStream oos = null;

        FileInputStream is = null;

        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件输出流 存放压缩文件
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            //同时把 赫夫曼编码也写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 针对赫夫曼编码进行解压文件
     *
     * @param zipFile 解压文件的路径
     * @param dstFile 输出解压后的文件路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //读取 byte 数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            //将数据写到 dsFile 文件
            os.write(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
