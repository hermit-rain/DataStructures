package com.algorithm.kmp;


/**
 * 字符串暴力匹配
 */

public class ViolenceMatch {

    public static void main(String[] args) {

        //测试暴力匹配
        String str1 = "悦悦子悦悦子悦悦悦悦";
        String str2 = "悦悦悦悦";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }


    /**
     * 字符串暴力匹配
     *
     * @param str1 源字符串
     * @param str2 字符子串
     * @return 如果找到则返回第一次匹配的起始下标 如果不匹配则返回 -1
     */
    public static int violenceMatch(String str1, String str2) {

        //将字符串转换成字符的形式存入到数组中
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        //定义索引
        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) { //如果对应字符相同
                i++;
                j++;
            } else { //对应的字符不同 则重置 j 并修改 i
                i = i - (j - 1);
                j = 0;
            }
        }
        //全部匹配成功
        if (j == s2Len) {
            return i - j;
        }
        {
            return -1;
        }


    }


}
