/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_33<Item extends Comparable<Item>> {  /* 继承*/
    /* variables*/
    private Item[] a;
    private Integer[] pq;
    private Integer[] qp;
    private int n = 0;

    /* constructor*/
    public Exercise_33(int maxsize) {
        a = (Item[]) new Comparable[maxsize + 1];  /* ******************************/
        pq = new Integer[maxsize + 1];
        qp = new Integer[maxsize + 1];
        for (int i = 0; i < maxsize + 1; i++) qp[i] = -1;
    }

    /* methord*/
    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(int k, Item item) {
        pq[++n] = k;
        a[k] = item;
        qp[k] = n;
        swim(n);
    }

    public void swim(int n) {
        while (n > 1 && less(pq[n], pq[n / 2])) {
            exch(pq, n, n / 2);
            n = n / 2;
        }
    }

    public Item minByMinIndex() {
        return a[pq[1]];
    }

    public int delMin() {
        int minIndex = pq[1];
        exch(pq, 1, n--);
        sink(1);  /* n为全局变量，已经改变*/
        a[pq[n + 1]] = null;
        // pq[n + 1] = null;
        qp[pq[n + 1]] = -1;
        return minIndex;
    }

    public void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && !less(pq[j], pq[j + 1])) j++;
            if (less(pq[k], pq[j])) break;
            exch(pq, k, j);
            k = j;
        }
    }

    public int minIndex() {
        return pq[1];
    }

    public void change(int k, Item item) {
        a[k] = item;
        int kIndexInPQ = qp[k];
        swim(kIndexInPQ);
        sink(kIndexInPQ);
    }

    public void delet(int k) {
        int kIndexInPQ = qp[k];
        exch(pq, kIndexInPQ, n--);
        swim(kIndexInPQ);
        sink(kIndexInPQ);
        a[k] = null;
        qp[k] = -1;
    }

    public boolean contains(int k) {
        return qp[k] == -1;
    }

    public Item call(int n) {
        return a[n];
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
        Exercise_33<String> b = new Exercise_33<String>(15);
        b.insert(10, "g");
        b.insert(1, "o");
        b.insert(5, "o");
        b.insert(3, "d");
        b.insert(4, "h");
        b.insert(9, "e");
        b.insert(2, "l");
        b.insert(0, "o");

        for (int i = 1; i <= b.size(); i++) System.out.print(b.call(b.pq[i]) + "  ");
        System.out.println();
        for (int i = 1; i <= b.size(); i++) System.out.print(b.pq[i] + "  ");
        System.out.println();
        System.out.println(b.delMin());
        System.out.println(b.delMin());
        System.out.println(b.delMin());
        System.out.println(b.delMin());
        System.out.println(b.delMin());
        System.out.println();
        System.out.println();
        for (int i = 1; i <= b.size(); i++) System.out.print(b.pq[i] + "  ");
        System.out.println();
        for (int i = 1; i <= b.size(); i++) System.out.print(b.call(b.pq[i]) + "  ");
    }
}
