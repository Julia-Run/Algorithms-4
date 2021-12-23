/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack1<Item> implements Iterable<Item> {
    // variables
    private Node first;
    private int n;

    // Node
    private class Node {  /* Node<Item> ???*/
        private Item item;
        private Node next;
    }

    //
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public class MyIterator implements Iterator<Item> {
        private int i = n;  /* 可以直接用N, 但是再调用size就会随之改变*/
        private Node curr = first;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item it = curr.item;
            curr = curr.next;
            i--;   /* Exception in thread "main" java.lang.NullPointerException */
            return it;
        }

        public void Remove() {
        }
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        MyStack1<String> a = new MyStack1<String>(); /* 无参数的构造函数*/
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
        a.push("this");
        a.push("is");
        a.push("a");
        a.push("test");
        for (String i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println(" ");
        StdOut.println(a.size());
    }
}
