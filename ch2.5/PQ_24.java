/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* java PQ_24 15 < PQ_24.txt */

public class PQ_24<Val extends Comparable<Val>> implements Iterable<Val> {
    /* maxPQ。 第一个元素为最大值。分支节点元素都小于等于parent。
     * 数组实现。0位不放元素。--- 计数方便。
     * n 记录队列的元素数量，同时也是index
     * put（val）--swim， deletMax（）-- sink，*/
    private Val[] pq;
    private int n;

    /* constructor*/
    public PQ_24(int capacity) {
        pq = (Val[]) new Comparable[capacity
                + 1];  /*      88888888888888888888888888888888888888888888888888888888888*/
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void put(Val v) {
        if (n == pq.length - 1)
            resize(2 * n); /*  888888888888888888888888888888888888888888888888888888888888888*/
        pq[++n] = v;  /* 1 */
        swim(n);
    }

    public void swim(int k) {
        while (k > 1 && pq[k / 2].compareTo(pq[k]) < 0) {
            exch(pq, k, k / 2);
            k = k / 2;
        }
    }

    public Val deletMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Val temp = pq[1];
        exch(pq, 1, n--);
        pq[n + 1] = null;
        sink(1);
        if (n > 0 && n == (pq.length - 1) / 4) resize(pq.length
                                                              / 2);  /* 8           8  8888888888888888888888888888888888888888888888888888*/
        return temp;
    }

    public void sink(int k) {
        int j;
        while (2 * k
                <= n) {  /* 不能将j<n的条件移到这里*/ /*   888888888888888888888888888888888888888888888888888888888888*/
            j = 2 * k;
            if (j < n && pq[j].compareTo(pq[j + 1]) < 0) j++;
            if (pq[k].compareTo(pq[j]) > 0) break;
            exch(pq, k, j);
            k = j;
        }
    }

    public void exch(Val[] x, int i, int j) {
        Val temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }

    public void resize(int newsize) {
        Val[] temp = (Val[]) new Comparable[newsize];
        for (int i = 1; i <= n; i++) temp[i] = pq[i];
        pq = temp;
    }

    public Iterator<Val> iterator() {  /* 88              ****************************************************/
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Val> {

        private int i = 0;

        public boolean hasNext() {
            return i != n;
        }

        public Val next() {
            if (!hasNext()) throw new NoSuchElementException();
            return pq[++i];
        }
    }

    public static void main(String[] args) {
        PQ_24<String> pq = new PQ_24<String>(Integer.parseInt(args[0]));

        // while (!StdIn.isEmpty()) {
        //     String item = StdIn.readString();
        //     if (!item.equals("-")) pq.put(item);
        //     else if (!pq.isEmpty()) StdOut.print(pq.deletMax() + " ");
        // }
        // StdOut.println("(" + pq.size() + " left on pq)");

        /* for iterator test*/
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            pq.put(item);
        }
        for (String i : pq) StdOut.print(i + "  ");
        StdOut.println();
    }

}
