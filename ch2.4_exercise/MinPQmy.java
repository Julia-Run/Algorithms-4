/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class MinPQmy<Item extends Comparable<Item>> {  /* 继承*/
    /* variables*/
    private Item[] a;
    private int n = 0;

    /* constructor*/
    public MinPQmy(int maxsize) {
        a = (Item[]) new Comparable[maxsize + 1];  /* ******************************/
    }

    public MinPQmy(Integer[] arr, int x) {
        n = arr.length;
        a = (Item[]) new Comparable[n + 1 + x];
        for (int i = 0; i < n; i++) a[i + 1] = (Item) arr[i];
        for (int k = n / 2; k >= 1; k--) sink(k);
    }

    /* methord*/
    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Item item) {
        a[++n] = item;
        swim(n);
    }

    public void swim(int n) {
        while (n > 1 && less(a[n], a[n / 2])) {
            exch(a, n, n / 2);
            n = n / 2;
        }
    }

    public Item delMin() {
        Item item = a[1];
        exch(a, 1, n--);
        a[n + 1] = null;
        sink(1);
        return item;
    }

    public void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && !less(a[j], a[j + 1])) j++;  /* j+1 is smaller*/
            if (less(a[k], a[j])) break;  /* k,j is smaller*/
            exch(a, k, j);
            k = j;
        }
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
        // MaxPQrepeat b = new MaxPQrepeat(5);
        // b.insert(10);
        // b.insert(15);
        // b.delMax();
        // b.insert(20);
        // b.insert(100);
        // b.insert(9);
        // b.insert(50);
        // for (int i = 1; i <= b.size(); i++) System.out.print(b.call(i) + "  ");
        // System.out.println();
        Integer[] c = { 10, 20, 100, 9, 10, 2, 100, 3, 200, 250, 1000, 11 };
        MinPQmy d = new MinPQmy(c, 10);  /* 10 refers to extra spaces*/
        d.insert(1000);
        d.insert(2000);
        for (int i = 1; i <= d.size(); i++) System.out.print(d.call(i) + "  ");
    }
}
