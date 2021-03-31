package com.algorithm.kmp;


import java.util.Arrays;

/**
 * KMP算法
 */

public class KMPAlgorithm {

    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        System.out.println("next=" + Arrays.toString(next));//[0,0,0,0,1,2,0]

        int index = kpmSearch(str1, str2, next);
        System.out.println("index=" + index); //15

    }


    /**
     * 获取字符串的部分匹配值表
     *
     * @param dest 输入字符该字符串
     * @return 返回该字符串对应的部分匹配值表
     */
    public static int[] kmpNext(String dest) {

        //定义一个数组用于记录部分匹配值
        int[] next = new int[dest.length()];
        //长度为1的字符串的部分匹配值为0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) 我们需要从next[j-1]获取新的j
            //直到有  dest.charAt(i) == dest.charAt(j)成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;

    }

    /**
     * kmp算法
     *
     * @param str1 源字符串
     * @param str2 字符子串
     * @param next 子串对应的部分匹配表
     * @return 如果找到返回第一个匹配的位置 否则返回 -1
     */
    public static int kpmSearch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {
            //核心代码
            //出现字符不相同的位置时 j 索引通过部分匹配表进行改变
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;

    }


}
