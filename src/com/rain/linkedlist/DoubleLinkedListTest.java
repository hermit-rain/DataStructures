package com.rain.linkedlist;

/**
 * 使用带 head 头的双向链表实现 –英雄联盟的英雄排行管理
 * 管理单向链表的缺点:
 * 1) 单向链表，查找的方向只能是一个方向，而双向链表可以向前或者向后查找。
 * 2) 单向链表不能自我删除，需要靠辅助节点 ，而双向链表，则可以自我删除
 *
 * 实现双向链表的增删改
 */

public class DoubleLinkedListTest {

    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1,"盲僧-李青","瞎子");
        HeroNode2 hero2 = new HeroNode2(2,"圣枪游侠-卢锡安","奥巴马");
        HeroNode2 hero3 = new HeroNode2(3,"探险家-伊泽瑞尔","EZ");
        HeroNode2 hero4 = new HeroNode2(4,"虚空恐惧-科加斯","大虫子");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //添加
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"疾风剑豪-亚索","风男");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表");
        doubleLinkedList.list();
        //删除
        doubleLinkedList.del(2);
        System.out.println("删除后的链表情况为~");
        doubleLinkedList.list();

    }

}

class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode2 head = new HeroNode2(0,"","");
    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 遍历
     * 和 单链表一样，只是可以向前，也可以向后查找
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表头为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断链表是否在最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将节点后移
            temp = temp.next;
        }

    }

    /**
     * 添加一个节点到双向链表的最后
     * 1.先找到双向链表的最后这个节点
     * 2.temp.next=newHeroNode
     * 3.newHeroNode.pre=temp
     */
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表到最后
        while (true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //当退出循环后，temp就指向了链表的最后
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 修改
     * 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
     * 只是 节点类型改成 HeroNode2
     */
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //根据no编号找到要修改的编号
        HeroNode2 temp = head.next;
        boolean flag = false;   //表示  是否找到该节点
        while (true) {
            if(temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
           temp.name = newHeroNode.name;
           temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("没有找到不能修改啊亲");
        }
    }

    /**
     * 删除
     * 1.因为是双向链表，因此，我们可以实现自我删除某个节点
     * 2.直接找到要删除的这个节点，比如 temp
     * 3.temp.pre.next=temp.next
     * 4.temp.next.pre=temp.pre;
     */
    public void del(int no) {

        //判断当前链表是否为空
        if(head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if(temp == null) {
                break;
            }
            if(temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.pre.next = temp.next;
            // 注意：如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if(temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }

}


//定义一个HeroNode2,每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;  //指向下一个节点，默认null
    public HeroNode2 pre;   //指向前一个节点，默认null

    public HeroNode2(int no,String name,String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}