/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<Item> implements Iterable<Item> {
    // variables
    private Node first;
    private Node last;
    private int n;

    /* Node class*/
    public class Node {
        private Item item;
        private Node next;
    }

    // method
    public Iterator<Item> iterator() {
        return new Qiterator();
    }

    /* iterator*/
    public class Qiterator implements Iterator<Item> {
        private int i = n;
        private Node curr = first;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");  /* attention!!!!*/
            Item item = curr.item;
            curr = curr.next;
            i--;
            return item;
        }

    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void enqueque(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        /* 边界条件：initially,first=last*/
        /* 或者可以认为是放入第一个元素时初始化first 以及last*/
        if (isEmpty()) first = last;   /* 边界条件！！！*/
            /* 常规情况*/
        else oldlast.next = last;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        else {
            Item item = first.item;
            first = first.next;
            n--;
            /* 边界条件：删除最后一个元素后，first=null，此时last也回到初始null*/
            if (isEmpty()) last = null; /* 边界条件！！！*/
            return item;
        }
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        MyQueue<String> a = new MyQueue<String>(); /* 无参数的构造函数*/
        StdOut.println(a.size());
        a.enqueque("to");
        StdOut.println(a.size());
        a.enqueque("be");
        StdOut.println(a.size());
        a.enqueque("or");
        StdOut.println(a.size());
        a.enqueque("not");
        StdOut.println(a.size());
        a.enqueque("to");
        StdOut.println(a.size());
        a.dequeue();
        StdOut.println(a.size());
        a.enqueque("be");
        StdOut.println(a.size());
        a.dequeue();
        StdOut.println(a.size());
        a.dequeue();
        StdOut.println(a.size());
        a.enqueque("that");
        StdOut.println(a.size());
        a.dequeue();
        StdOut.println(a.size());
        a.dequeue();
        StdOut.println(a.size());
        a.dequeue();
        StdOut.println(a.size());
        a.enqueque("is");
        StdOut.println(a.size());
        a.dequeue();
        a.dequeue();
        a.enqueque("this");
        a.enqueque("is");
        a.enqueque("a");
        a.enqueque("test");
        for (String i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println(" ");
        StdOut.println(a.size());
    }
}
