/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class MaxPQself<Item extends Comparable<Item>> {

    /* variables*/
    private int n = 0;
    private Item[] a;

    /* constructor*/
    public MaxPQself(int maxSize) {
        a = (Item[]) new Comparable[maxSize + 1];  /* 0位不储存，1 to N */
    }

    /* methods*/
    public Item deletMax() {
        /* 最大元素和最后元素换位，最后元素设置为null， n-1 */
        Item max = a[1];
        a[1] = a[n--];  /* a1 = a9, n=8*/
        a[n] = max;
        a[n + 1] = null;
        /* sink:  和左右支较大的换位，直到换到叶子*/
        int k = 1;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a[j], a[j + 1])) j++;
            if (!less(a[k], a[j])) break;
            exch(a, k, j);
            k = j;
        }
        return max;
    }

    public void exch(Item[] x, int i, int j) {
        Item temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }

    public boolean less(Item ai, Item aj) {
        return ai.compareTo(aj) == -1;
    }

    public void insertP(Item p) {
        a[++n] = p;
        /* swim out of the surface (bottom to top)*/
        int k = n;
        while (k > 1 && less(a[k / 2], a[k])) {
            exch(a, k, k / 2);
            k = k / 2;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }


    public static void main(String[] args) {
    }
}
