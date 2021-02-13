package com.rain.linkedlist;

/**
 * 通过单向环形链表实现 Josephu(约瑟夫、约瑟夫环) 问题
 *
 * 设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数
 * 数 到 m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，
 * 由此产生一个出队编号的序列
 *
 * 构建单向的环形链表
 * 1.先创建第一个节点，让 first 指向该节点，并形成环
 * 2.后面当我们每创建一个新的节点就把该节点加入并到已有的环形链表中即可
 *
 * 遍历环形链表
 * 1.先让一个辅助指针 cur指向first节点
 * 2.然后通过一个while循环遍历该环形链表即可 cur.next == first 结束
 *
 * 约瑟夫问题思路：
 * 1.需要创建一个辅助指针 helper，事先应该指向环形链表的最后这个节点
 * 2.小孩报数前，先让 first和 helper移动 k-1次 （因为是从第 k 个小孩开始数数）
 * 3.当小孩报数时，让 first和 helper 指针同时移动 m-1次
 * 4.将 first 指向的小孩出圈 first = first.next， helper.next = first
 *
 */

public class Josephu {
    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addChildren(125);
        circleSingleLinkedList.showChildren();

        //测试小孩出圈
        circleSingleLinkedList.countChildren(15,15,125);

    }

}
//创建一个环形的单向链表
class CircleSingleLinkedList {

    //创建一个first节点
    private Children first = null;

    //添加小孩节点，构成一个环形的链表
    public void addChildren(int nums) {
        if(nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Children cur = null;    //辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            Children children = new Children(i);
            //如果是第一个小孩
            if (i == 1) {
                first = children;
                first.setNext(first);//构成环
                cur = first;
            } else {
                cur.setNext(children);
                children.setNext(first);
                cur = children;
            }
        }
    }
    //遍历当前环形链表
    public void showChildren() {
        //判断链表是否为空
        if(first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        //因为 first 不能动，因此我们仍然使用一个辅助变量完成遍历
        Children cur = first;
        while (true) {
            System.out.println("小孩的编号为"+cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }
    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countChildren(int startNo, int countNum, int nums) {
       //先校验数据
       if (first == null || startNo < 1 || startNo > nums) {
           System.out.println("参数输入有误，请重新输入");
           return;
       }
       //创建要给辅助指针帮助小孩出圈
        Children helper = first;
       while (true) {
           if(helper.getNext() == first) {// 说明helper指向最后小孩节点
               break;
           }
           helper = helper.getNext();
       }
       //小孩报数前，先让 first 和 helper 移动 k - 1次 即确定起始小孩
        for(int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次, 然后出圈
        while (true) {
            if(helper == first) {
                break;
            }
            //让 first 和 helper 指针同时的移动 countNum - 1
            for(int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());

    }

}

//创建一个Children类，表示小孩节点
class Children {
    private int no;
    private Children next;  //指向下一个节点，默认null

    public Children(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Children getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Children next) {
        this.next = next;
    }
}
