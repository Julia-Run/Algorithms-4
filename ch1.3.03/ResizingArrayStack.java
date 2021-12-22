import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */
public class ResizingArrayStack<Item> implements Iterable<Item> {  /* attention--此处是Iterable*/
    // variables
    private Item[] a = (Item[]) new Object[1]; /* 初始数组长度为1*/
    private int N = 0;

    //method
    public boolean isEmpty() {
        return N == 0;
    }

    public String size() {
        return N + "   " + a.length;
    }

    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Item pop() {
        Item val = a[N - 1];
        a[N--] = null;
        if (N > 0 && N == a.length / 4) resize(N * 2);  /* 1/4*a.length = 0 !!!!!!!!!! */
        return val;
    }

    public void push(Item val) {
        if (N == a.length) resize(2 * N);
        a[N++] = val;
    }

    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }

    public class ReverseIterator implements Iterator<Item> {
        /* attention--此处是Iterator || ReverseIterator后不加<Item>*/
        private int i = N;  /* 可以直接用N, 但是再调用size就会随之改变*/

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];  /* --i and i-- are diffent, i-- not work */
        }

        public void Remove() {
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> a = new ResizingArrayStack<String>(); /* 无参数的构造函数*/
        StdOut.println(a.size());
        a.push("to");
        StdOut.println(a.size());
        a.push("be");
        StdOut.println(a.size());
        a.push("or");
        StdOut.println(a.size());
        a.push("not");
        StdOut.println(a.size());
        a.push("to");
        StdOut.println(a.size());
        a.pop();
        StdOut.println(a.size());
        a.push("be");
        StdOut.println(a.size());
        a.pop();
        StdOut.println(a.size());
        a.pop();
        StdOut.println(a.size());
        a.push("that");
        StdOut.println(a.size());
        a.pop();
        StdOut.println(a.size());
        a.pop();
        StdOut.println(a.size());
        a.pop();
        StdOut.println(a.size());
        a.push("is");
        StdOut.println(a.size());
        for (String i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println(" ");
        StdOut.println(a.size());

        for (int i = 10; i >= 0; i--) {
            StdOut.print(i + " ");
        }
        StdOut.println(" ");
        for (int j = 10; j >= 0; --j) {
            StdOut.print(j + " ");
        }
        StdOut.println(" ");
        int x = 0;
        int b = x++; // b = 0; a = 1
        StdOut.println(x + " " + b);
        x = 0;
        b = ++x; // b = 1; a = 1
        StdOut.println(x + " " + b);
        /* The idea is that ++x increments x and returns that value, while x++ returns x's value and then increments x.*/
    }
}
