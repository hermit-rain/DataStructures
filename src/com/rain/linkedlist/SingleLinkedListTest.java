package com.rain.linkedlist;

import java.util.Stack;

/**
 * 使用带 head 头的单向链表实现 –英雄联盟英雄排行管理，完成对英雄人物的增删改查操作
 *
 * 链表是以节点的方式来存储的 每个节点包含 data 域， next 域（指向下一个节点）
 * 链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定
 *
 * 直接添加到链表尾部
 * 1.先创建一个head头节点，作用就是表示单链表的头
 * 2.后面每添加一个节点，就直接加入到链表的头
 *
 * 遍历
 * 1.通过一个辅助变量遍历，帮助遍历整个链表
 *
 * 根据date域数据来决定节点添加的位置（本例根据英雄id排名排序）
 * 1.首先找到新添加的节点的位置，是通过辅助变量（指针-对象）遍历解决
 * 2.新的节点.next = temp.next
 * 3.将temp.next = 新的节点
 *
 * 修改节点
 * 1.通过遍历找到 该节点
 * 2. temp.name = newHeroNode.name; temp.nickname = newHeroNode.nickname;
 *
 * 删除节点
 * 1.先找到需要删除节点的前一个节点temp
 * 2.temp.next = temp.next.next
 * 3.被删除的节点，将不会有其他的指向（空指针），会被垃圾回收机制回收

 */

public class SingleLinkedListTest {

    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1,"盲僧-李青","瞎子");
        HeroNode hero2 = new HeroNode(2,"圣枪游侠-卢锡安","奥巴马");
        HeroNode hero3 = new HeroNode(3,"探险家-伊泽瑞尔","EZ");
        HeroNode hero4 = new HeroNode(4,"虚空恐惧-科加斯","大虫子");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //直接加入到链表尾部
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);

        //加入按照编号的顺序
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);

        //显示一手
        singleLinkedList.list();

        //测试单链表的有效节点个数
        System.out.println("单链表的有效节点个数为"+getLength(singleLinkedList.getHead()));

        //测试返回倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),2);
        System.out.println("倒数第k个节点为"+res);

        //测试链表反转功能
        System.out.println("反转单向链表为~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        //测试逆序打印
        System.out.println("逆序打印单向链表,结果并不改变单链表的结构");
        reversePrint(singleLinkedList.getHead());

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"圣枪游侠","双抢奥巴马");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();

        //删除节点
        singleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        singleLinkedList.list();

    }

    /**
     * 获取单链表的节点个数（如果是带头节点，不需要统计头节点）
     * head 链表的头节点
     * 返回就是链表的有效的节点个数
     */
    public static int  getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助变量，这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 查找单链表中倒数第k个节点 [新浪]
     * 编写一个方法，接受head节点，同时节点后index（index表示倒数第几个数）
     * 1.把链表从头到尾遍历，得到链表的总长度 getLength
     * 2.得到size后，我们从链表的第一个开始遍历（size - index）个即为结果
     * 3.如果找到了即返回该节点，否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表是否为空，返回null
        if(head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历 size - index 位置，即是我们倒数的第k个节点
        //先做一次index的校验
        if (index <= 0 ||  index > size) {
            return null;
        }
        //定义一个辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 单链表的反转[腾讯]
     * 头插法
     * 1.先定义一个节点reverseHead = new HeroNode();
     * 2.从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放到reverseHead的最前端
     * 3.原来的链表的 head.next = reverseHead.next
     */
    public static void reverseList(HeroNode head) {
        //如果链表为空或者只有一个节点则无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助指针（变量）， 帮助遍历原来的了链表
        HeroNode cur = head.next;
        HeroNode next = null;   //指向cur的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        while (cur != null) {
            next = cur.next;
            //第一次循环将cur的next域置为空，之后循环将reverseHead的节点连接到cur节点上
            cur.next = reverseHead.next;
            reverseHead.next = cur; //将cur连接到新的链表头上
            cur = next; //让cur后移
        }
        //将head.next 指向reverseHead.next,即将链表头替换为原表头
        head.next = reverseHead.next;
    }


    /**
     * 从尾到头打印单链表 [百度]
     * 方式一：先将单链表反转操作，然后遍历即可；弊端：破坏原来的单链表的结构
     * 方式二：可以通过 栈 数据结构，将各个节点压入栈中，然后再pop出栈即可
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所以节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}

//定义一个SingleLinkedList 管理英雄
class SingleLinkedList {
    //初始化头节点
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //直接添加节点到链表的尾部
    public void add(HeroNode heroNode) {
        //head头节点不能动，需要辅助节点 temp
        HeroNode temp = head;
        //遍历节点找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到链表表的最后，直接将链表后移
            temp = temp.next;
        }
        //当遍历完成后，temp指向了链表的最后
        temp.next = heroNode;
    }

    //根据排名添加节点
    public void addByOrder(HeroNode heroNode) {
        //定义辅助变量-表示是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;   // flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到即在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;    //说明编号已经存在了
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            System.out.println("准备插入的英雄的编号 %d 已经存在"+ heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空-");
            return;
        }
        //找到需要修改的节点编号 no
        HeroNode temp = head.next;
        boolean flag = false;   //表示是否找到节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("没有找到编号 %d 的节点，不能修改");
        }
    }

    //删除节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;   // 标志是否找到待删除节点的
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //找到待删除的节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;   //temp后移，遍历
        }
        //判断flag
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的节点 %d 节点不存在"+ no);
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }
}


//定义一个英雄HeroNode类，每一个对象就是一个节点
class HeroNode {
    public int no;  //英雄编号
    public String name; //英雄名字
    public String nickname; //英雄的昵称
    public HeroNode next;  //指向下一个节点

    //定义构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //重写toString方法
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
