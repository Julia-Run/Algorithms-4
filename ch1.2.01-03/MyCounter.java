/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class MyCounter {
    // 实例变量
    private final String name;
    private int count;

    //构造函数
    public MyCounter(String s) {
        name = s;
    }

    //方法
    public void increase() {
        count++;
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return count + " " + name;
    }

    /*用例测试*/
    public static void main(String[] args) {
        MyCounter head = new MyCounter("heads");
        MyCounter tail = new MyCounter("tails");
        head.increase();
        head.increase();
        tail.increase();
        System.out.println(head);
        System.out.println(tail);
        System.out.println(head.tally());
        System.out.println(tail.tally());
        System.out.println(head.name);
        System.out.println(tail.count);

    }
}
