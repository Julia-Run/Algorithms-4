/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* java PQ_24 15 < PQ_24.txt */

public class Exercise_24<Val extends Comparable<Val>> implements Iterable<Val> {
    /* maxPQ。 第一个元素为最大值。分支节点元素都小于等于parent。
     * 数组实现。0位不放元素。--- 计数方便。
     * n 记录队列的元素数量，同时也是index
     * put（val）--swim， deletMax（）-- sink，*/

    /* 加入时间戳，将相等的元素按照插入时间比较大小
     * 插入时间较后的元素B（时间戳较大TB>TA），应该后后后后后后后出。
     * 在maxPQ中，先出的是maxer且时间戳小。--- 认为时间戳较小的元素更大|时间戳更大的元素更小
     * 在minPQ中，先出的是miner且时间戳小。--- 认为时间戳较小的元素更小--*/
    private Val[] pq;
    private int n;
    private int[] time;
    private int timeStamp;

    /* constructor*/
    public Exercise_24(int capacity) {
        pq = (Val[]) new Comparable[capacity + 1];  /*      88888888888888888888888888888888*/
        n = 0;
        time = new int[capacity + 1];
        timeStamp = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void put(Val v) {
        if (n == pq.length - 1) resize(2 * n);
        /*  8888888888888888888888888*/
        pq[++n] = v;  /* 1 */
        time[n] = ++timeStamp;
        swim(n);
    }

    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public Val deletMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Val temp = pq[1];
        exch(1, n--);
        time[n + 1] = 0;
        pq[n + 1] = null;
        sink(1);
        if (n > 0 && n == (pq.length - 1) / 4) resize(pq.length / 2);  /* 8888888888888888*/
        return temp;
    }

    public void sink(int k) {
        int j;
        while (2 * k
                <= n) {  /* 不能将j<n的条件移到这里*/ /*   888888888888888888888888888888888888888888888888888888888888*/
            j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    public void exch(int i, int j) {
        Val temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        int temptime = time[i];
        time[i] = time[j];
        time[j] = temptime;
    }

    public void resize(int newsize) {
        Val[] temp = (Val[]) new Comparable[newsize];
        int[] temptime = new int[newsize];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
            temptime[i] = time[i];
        }
        pq = temp;
        time = temptime;
    }

    public boolean less(int i, int j) {
        int com = pq[i].compareTo(pq[j]);
        if (com < 0) return true;
        else if (com > 0) return false;
        return time[i] > time[j];
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

    private static final class Tuple implements Comparable<Tuple> {
        private String name;
        private int id;

        private Tuple(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int compareTo(Tuple that) {
            return this.name.compareTo(that.name);
        }

        public String toString() {
            return name + " " + id;
        }
    }

    public static void main(String[] args) {
        Exercise_24<Tuple> pq = new Exercise_24<Tuple>(100);

        // insert a bunch of strings
        String text = "it was the best of times it was the worst of times it was the "
                + "age of wisdom it was the age of foolishness it was the epoch "
                + "belief it was the epoch of incredulity it was the season of light "
                + "it was the season of darkness it was the spring of hope it was the "
                + "winter of despair";
        String[] strings = text.split("\\s+");
        for (int i = 0; i < strings.length; i++) {
            pq.put(new Tuple(strings[i], i));
        }


        // delete and print each key
        while (!pq.isEmpty()) {
            StdOut.println(pq.deletMax());
        }
        StdOut.println();

    }

}
