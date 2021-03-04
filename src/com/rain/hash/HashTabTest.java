package com.rain.hash;


import java.util.Scanner;

/**
 * 哈希表
 * 散列表（Hashtable，也叫哈希表），是根据关键码值(Key value)而直接进行访问的数据结构。
 * 也就是说，它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。这个映射函数叫做散列函数，存放记录的数组叫做散列表。
 * 哈希表在缓存技术出现前可以应用于在内存与数据库中间 来充缓存层
 *
 * 题目描述
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址..)
 * 当输入该员工的id时,要求查 找到该员工的 所有信息.
 * 要求: 不使用数据库,尽量节省内存,速度越快越好=>哈希表(散列)
 * 实现方式 ==> 数组 + 链表
 */

public class HashTabTest {

    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }

}




//创建一个Emp 表示雇员
class Emp {
    public int id;
    public String name;
    public Emp next; //next默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

}

//创建一个EmpLinkedList 类表示链表
class EmpLinkedList {

    //头指针，执行第一个Emp 因为该链表的head 是直接指向第一个 Emp的
    private Emp head;

    /**
     * 添加雇员到链表
     * 假定当添加雇员时 id 是自增长的 即 id 的分配总是从小到大
     * 因此我们直接将雇员信息添加到链表最后即可
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针 帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp.next = emp;
        }

    }


    /**
     * 遍历雇员信息
     * @param no 通过
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.println("第" + (no + 1) + "链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf("== id = %d name = %s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next; //后移
        }
        System.out.println();

    }


    /**
     * 根据 id 查找雇员
     * @param id
     * @return 如果找到则返回 Emp 如果没有找到则 返回 null
     */
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            //退出
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;

    }


}


//创建HashTab 管理多条链表
class HashTab {

    private EmpLinkedList[] empLinkedListArray;
    private int size; //用于表示有多少条链表

    public HashTab (int size) {
        this.size = size;
        //初始化emLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];

        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }

    /**
     * 编写散列函数，使用一个简单取模法
     * @param id
     * @return
     */
    private int hashFun(int id) {
        return id % size;

    }


    /**
     * 添加雇员信息
     * @param emp
     */
    public void add(Emp emp) {

        //根据员工的id 得到该员工应该添加到那条链表
        int empLinkedListNo = hashFun(emp.id);

        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);

    }

    /**
     * 遍历所有链表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }

    }

    /**
     * 根据输入的id,查找雇员
     * @param id
     */
    public void findEmpById(int id) {
        //使用散列函数来确定那条链表
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {//找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }

    }



}













