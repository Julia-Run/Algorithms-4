/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_30<Item extends Comparable<Item>> {  /* 继承*/
    /* variables*/
    private MaxPQrepeat<Item> left;  /* max*/
    private MinPQmy<Item> right;  /* min*/
    private int n = 0;
    private Item mid;

    public Exercise_30() {
        left = new MaxPQrepeat<Item>(10);
        right = new MinPQmy<Item>(10);
    }


    /* methord*/
    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Item item) {
        if (n == 0 || less(item, left.call(1))) left.insert(item);
            /* 先放左边。
             * 第一次比较左边，此时不是null，可继续，后面则正常 */
        else right.insert(item);

        if (left.size() > right.size() + 1) right.insert(left.delMax());
            /* 没放一个数字，检查左右size，从而保证一直符合要求
             * mid不需要混在这里面，会越来越乱，分开处理*/
        else if (right.size() > left.size() + 1) left.insert(right.delMin());

        n++;
    }


    public Item getMid() {
        if (left.size() >= right.size()) mid = left.call(1);
        else mid = right.call(1);
        return mid;
    }

    public Item delMid() {
        if (left.size() >= right.size()) mid = left.delMax();
        else mid = right.delMin();
        return mid;
    }

    /* helpers*/
    public boolean less(Comparable ai, Comparable aj) {
        return ai.compareTo(aj) == -1;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /* examples*/
    public static void main(String[] args) {
        Exercise_30 b = new Exercise_30();
        b.insert(10);
        b.insert(15);
        b.insert(20);
        b.insert(100);
        b.insert(9);
        b.insert(50);
        b.insert(-100);
        b.insert(-3);
        b.insert(2);

        System.out.println(b.getMid());
        b.delMid();
        b.delMid();
        System.out.println(b.getMid());

        // Integer[] c = { 10, 20, 100, 9, 10, 2, 100, 3, 200, 250, 1000, 11 };
        // Exercise_27 d = new Exercise_27(c, 10);  /* 10 refers to extra spaces*/
        // d.insert(1000);
        // d.insert(2000);
        // for (int i = 1; i <= d.size(); i++) System.out.print(d.call(i) + "  ");

    }
}
