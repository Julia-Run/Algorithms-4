/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class BSTrewrite<Key extends Comparable<Key>, Val> {

    /* variables****************************************/
    private Node root;

    private class Node implements TreeDrawer.PrintableNode {
        private Key key;
        private Val val;
        private int nodeNums;
        private Node left, right;

        public Node(Key k, Val v) {
            key = k;
            val = v;
            nodeNums = 1;
        }

        public TreeDrawer.PrintableNode getLeft() {
            return left;
        }

        public TreeDrawer.PrintableNode getRight() {
            return right;
        }

        public String getText() {
            return key + "";
        }
    }

    // basic methods
    public void draw() {
        TreeDrawer.draw(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.nodeNums;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int height() {
        return height(root);
    }

    public int height(Node x) {
        if (x == null) return -1;
        return height(x.left) > height(x.right) ? height(x.left) + 1 : height(x.right) + 1;
    }


    // get / put
    public Val get(Key k) {
        if (k == null) throw new IllegalArgumentException("key can not be null");
        Node x = get(root, k);
        if (x == null) throw new NoSuchElementException("Not in this BST");
        else return x.val;
    }

    public Node get(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com == 0) return x;
        else if (com < 0) return get(x.left, k);
        else return get(x.right, k);
    }

    public void put(Key k, Val v) {
        if (k == null) throw new IllegalArgumentException("key can not be null");
        root = put(root, k, v);
    }

    public Node put(Node x, Key k, Val v) {
        if (x == null) return new Node(k, v);
        int com = k.compareTo(x.key);
        if (com < 0) x.left = put(x.left, k, v);
        else if (com > 0) x.right = put(x.right, k, v);
        else x.val = v;
        x.nodeNums = size(x.left) + size(x.right) + 1;
        return x;
    }

    // min max
    public Key min() {
        if (isEmpty()) return null;
        Node x = min(root);
        return x.key;
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        Node x = max(root);
        return x.key;
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    // floor <= key, ceiling >= key
    public Key floor(Key k) {
        if (k == null) throw new IllegalArgumentException("key is null");
        if (isEmpty()) throw new NoSuchElementException("This is an empty BST");
        Node x = floor(root, k);
        if (x == null) throw new NoSuchElementException("Given k is too small");
        else return x.key;
    }

    public Node floor(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com == 0) return x;
        else if (com < 0) return floor(x.left, k);
        else {
            Node t = floor(x.right, k);
            if (t == null) return x;
            else return t;
        }
    }

    public Key ceiling(Key k) {
        if (k == null) throw new IllegalArgumentException("key is null");
        if (isEmpty()) throw new NoSuchElementException("This is an empty BST");
        Node x = ceiling(root, k);
        if (x == null) throw new NoSuchElementException("Given k is too large");
        else return x.key;
    }

    public Node ceiling(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com == 0) return x;
        else if (com > 0) return ceiling(x.right, k);
        else {
            Node t = ceiling(x.left, k);
            if (t == null) return x;
            else return t;
        }
    }

    // select, rank, start at zero;
    public Key select(int i) {
        if (i < 0 || i >= size()) throw new IllegalArgumentException("Out of range");
        return select(root, i);
    }

    public Key select(Node x, int i) {
        if (x == null) return null; // root = null;
        int j = size(x.left);
        if (i < j) return select(x.left, i);
        else if (i == j) return x.key;
        else return select(x.right, i - j - 1);
    }

    public int rank(Key k) {
        if (k == null) throw new IllegalArgumentException("Key can not be null");
        return rank(root, k);
    }

    public int rank(Node x, Key k) {
        if (x == null) return 0;
        int com = k.compareTo(x.key);
        if (com < 0) return rank(x.left, k);
        else if (com == 0) return size(x.left);
        else return rank(x.right, k) + size(x.left) + 1;
    }

    // deleteMin, deleteMax, delete
    public void deleteMin() {
        if (isEmpty()) return;
        root = deleteMin(root);
    }

    public Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.nodeNums = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) return;
        root = deleteMax(root);
    }

    public Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.nodeNums = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key k) {
        if (k == null) throw new IllegalArgumentException("Key can not be null");
        root = delete(root, k);
    }

    public Node delete(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com < 0) x.left = delete(x.left, k);
        else if (com > 0) x.right = delete(x.right, k);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(x.right);
            x.left = t.left;
            x.right = deleteMin(t);
        }
        x.nodeNums = size(x.left) + size(x.right) + 1;
        return x;
    }

    // print in order
    public void show() {
        show(root);
    }

    public void show(Node x) {
        if (x == null) return;
        show(x.left);
        StdOut.print(x.key + "  ");
        show(x.right);
    }

    // iterable
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> keysQueue = new Queue<Key>();
        keys(root, keysQueue, lo, hi);
        return keysQueue;
    }

    public void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if (x == null) return;
        int start = lo.compareTo(x.key);
        int end = hi.compareTo(x.key);
        if (start < 0) keys(x.left, q, lo, hi);
        if (start <= 0 && end >= 0) q.enqueue(x.key);
        if (end > 0) keys(x.right, q, lo, hi);
    }


    public static void main(String[] args) {
        BSTrewrite<Integer, String> st = new BSTrewrite<Integer, String>();
        StdOut.println(st.isEmpty());
        StdOut.println(st.size());
        st.put(1, "a");
        st.draw();
        st.put(3, "c");
        st.draw();
        st.put(2, "b");
        st.draw();
        st.put(5, "e");
        st.draw();
        st.put(-1, "A");
        st.draw();
        st.put(0, "0");
        st.draw();
        StdOut.println("***************Testing of get,size,isEmpty**************"
                               + "*********************************************");
        StdOut.println(st.get(2));
        StdOut.println(st.size());
        StdOut.println(st.isEmpty());
        StdOut.println("***************Testing of min, max**************"
                               + "*********************************************");
        st.draw();
        StdOut.println(st.min());
        StdOut.println(st.max());
        StdOut.println("***************Testing of floor, ceiling**************"
                               + "*********************************************");
        st.draw();
        StdOut.println(st.floor(8));
        StdOut.println(st.floor(1));
        StdOut.println(st.floor(3));
        // StdOut.println(st.floor(-8));
        StdOut.println(st.ceiling(3));
        StdOut.println(st.ceiling(1));
        StdOut.println(st.ceiling(-10));
        // StdOut.println(st.ceiling(10));
        StdOut.println("***************Testing of select, rank**************"
                               + "*********************************************");
        st.draw();
        StdOut.println(st.select(2));
        StdOut.println(st.rank(6));
        StdOut.println(st.rank(5));
        StdOut.println(st.rank(-1));
        StdOut.println("***************Testing of deleteMin, deleteMax, delete**************"
                               + "*********************************************");
        st.draw();
        st.deleteMin();
        st.draw();
        st.deleteMax();
        st.draw();
        st.put(-2, "B");
        st.put(-3, "C");
        st.put(8, "h");
        st.draw();
        st.delete(0);
        st.draw();
        st.show();
        StdOut.println();
        for (Integer i : st.keys()) {
            StdOut.print(i + " --> ");
        }
        StdOut.print("this is the end mark");
        StdOut.println();
        StdOut.print("the height of the tree now is " + st.height());
        // for (int i = 0; i < st.size(); i++) {
        //     StdOut.print(st.keys() + " --> ");
        // }
    }
}
