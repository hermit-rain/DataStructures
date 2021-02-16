package com.rain.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  逆波兰计算器
 *  输入一个中缀表达式后将其转化成逆波兰表达式(后缀表达式)，并使用栈(Stack)计算其结果
 *  1.中缀表达式转成对应的List
 *  2.中缀表达式转化成后缀表达式
 *  3.逆波兰表达式的计算

 */

public class PolandNotation {

    public static void main(String[] args) {
        //定义一个前缀表达的字符串
        String expression = "1+((2+3)*4)-5";
        //转化成List
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list为：" + infixExpressionList);
        //转换成后缀表达式
        List<String> suffixExpressionList = parseSuffixExceptionList(infixExpressionList);
        System.out.println("转换后的后缀表达式对应的list为：" + suffixExpressionList);
        //计算后缀表达
        System.out.println("运算的结果为：" + calculate(suffixExpressionList));

    }


    /**
     * 将中缀表达式转化成对应的 List
     * 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
     * 即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
     * @param s 输入的中缀表达式字符串
     * @return 返回相应的List集合
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式相应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;  //类似于指针用于遍历中缀表达式的字符串
        String str; //对多位的拼接
        char c; //每遍历一个字符即放入到c中
        do {
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57) {
                ls.add(""+c);
                i++;
            } else {//如果是数字则需要考虑多位数的情况
                str = "";
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;   //拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    /**
     * 将中缀表达式对应的List => 后缀表达式对应的List
     * 思路：
     * 1) 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压 s2；
     * 4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级
     *  1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *  2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
     *  3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的栈顶运算符相比较
     * 5) 遇到括号时：
     *  1.如果是左括号“(”，则直接压入 s1
     *  2.如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤 2 至 5，直到表达式的最右边
     * 7) 将 s1 中剩余的运算符依次弹出并压入 s2
     * 8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @param ls 输入中缀表达对应的List
     * @return 返回后缀表达式对应的List
     */
    public static List<String> parseSuffixExceptionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();
        //因为原本 s2 这个栈在整个转换的过程中没有pop操作,并且到最后需要逆序输出
        //因此比较麻烦这里我们就不用Stack<String>直接用List<String> s2
        List<String> s2 = new ArrayList<String>();
        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) { //如果是一个数则加入到s2
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();   //将“（”弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中的剩余的运算符依次弹出并加入到s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;  //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List

    }

    /**
     * 计算逆波兰表达式
     * 思路 例 :(3+4)×5-6 对应的后缀表达式就是 34+5 × 6-
     * 1．从左至右扫描，将 3 和 4 压入堆栈；
     * 2．遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
     * 3．将 5 入栈；
     * 4．接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
     * 5．将 6 入栈；
     * 6．最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     * @param ls 输入一个逆波兰表达式对应的List集合
     * @return 返回表达式运行的结果
     */
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //用正则表达取数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1*num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

}


//写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}