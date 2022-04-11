/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class BSTrb<Key extends Comparable<Key>, Val> {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node implements TreeDrawerRB.PrintableNode {
        private Key key;
        private Val val;
        private int size;
        private Node left, right;
        private boolean color;

        public Node(Key k, Val v, int n, boolean c) {
            key = k;
            val = v;
            size = n;
            color = c;
        }

        public TreeDrawerRB.PrintableNode getLeft() {
            return left;
        }

        public TreeDrawerRB.PrintableNode getRight() {
            return right;
        }

        public String getText() {
            return key + "";
        }

        public boolean redNode() {
            return color;
        }
    }

    // general func

    public void draw() {
        TreeDrawerRB.draw(root);
    }

    public boolean isRED(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // put prepare
    private Node rotateLeft(Node x) {
        Node t = x.right;
        x.right = t.left;
        t.left = x;
        t.color = x.color;
        x.color = RED;
        t.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return t;
    }

    private Node rotateRight(Node x) {
        Node t = x.left;
        x.left = t.right;
        t.right = x;
        t.color = x.color;
        x.color = RED;
        t.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return t;
    }

    private void flipColors(Node x) {
        // if (x != null && x.left != null && x.right != null) { // add if conditions
        //     x.color = RED;
        //     x.left.color = BLACK;
        //     x.right.color = BLACK;
        // // this will lead to error.
        // }
        if (x != null) {
            x.color = !x.color;
            if (x.left != null) x.left.color = !x.left.color;
            if (x.right != null) x.right.color = !x.right.color;
        }
    }

    private Node balance(Node x) {
        if (x == null) return null;
        if (!isRED(x.left) && isRED(x.right)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    // put
    public void put(Key k, Val v) {
        if (k == null) throw new IllegalArgumentException("k is null");
        if (v == null) delete(k);
        root = put(root, k, v);
        root.color = BLACK; // make sure root is black;
    }

    private Node put(Node x, Key k, Val v) {
        if (x == null) return new Node(k, v, 1, RED);
        int comp = k.compareTo(x.key);
        if (comp < 0) x.left = put(x.left, k, v);
        else if (comp > 0) x.right = put(x.right, k, v);
        else x.val = v;
        return balance(x);
    }

    // get
    public Val get(Key k) {
        if (k == null) throw new IllegalArgumentException("k is null");
        return get(root, k);
    }

    private Val get(Node x, Key k) {
        if (x == null) return null;
        int comp = k.compareTo(x.key);
        if (comp < 0) return get(x.left, k);
        else if (comp > 0) return get(x.right, k);
        else return x.val;
    }


    // min / max key
    public Key min() {
        if (isEmpty()) throw new IllegalArgumentException("empty");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    // delete prepare
    private Node moveRedLeft(Node x) {
        // delMin, change 2- to
        flipColors(x);
        if (x.right != null && isRED(x.right.left)) { //  x.right != null
            x.right = rotateRight(x.right); // split single element to the left
            x = rotateLeft(x);  // merge this element with left-node
            flipColors(x); // flip back
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (x.left != null && isRED(x.left.left)) { // add an if condition x.left != null
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    // deleteMin: make sure there is no 2-node during searching
    public void deleteMin() {
        if (isEmpty()) throw new IllegalArgumentException("empty");
        if (!isRED(root.left) && !isRED(root.right)) root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (!isRED(x.left) && !isRED(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMax() {
        if (isEmpty()) throw new IllegalArgumentException("empty");
        if (!isRED(root.left) && !isRED(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node x) {
        if (isRED(x.left)) x = rotateRight(x); // make sure not lose element
        if (x.right == null) return null;
        // left-node is already black
        if (!isRED(x.right) && !isRED(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return balance(x);
    }

    public boolean contains(Key k) {
        return get(k) != null;
    }

    public void delete(Key k) {
        if (k == null) throw new IllegalArgumentException("k is null");
        if (isEmpty()) throw new IllegalArgumentException("Empty");
        if (!contains(k)) return;
        if (!isRED(root.left) && !isRED(root.right)) root.color = RED;
        root = delete(root, k);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node x, Key k) {
        if (k.compareTo(x.key) < 0) { // <
            if (!isRED(x.left) && !isRED(x.left.left)) x = moveRedLeft(x);
            x.left = delete(x.left, k);
        }
        else {
            if (isRED(x.left)) x = rotateRight(x);
            if (k.compareTo(x.key) == 0 && x.right == null) return null; // boundaries
            if (!isRED(x.right) && !isRED(x.right.left)) x = moveRedRight(x);
            if (k.compareTo(x.key) == 0) { // =
                // delete current key
                Node t = min(x.right);
                x.key = t.key;
                x.val = t.val;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(x.right, k); // >
        }
        return balance(x);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        // must have elements
        Queue<Key> que = new Queue<Key>();
        keys(root, que, lo, hi);
        return que;
    }

    private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        // must have elements
        if (x == null) return;
        int start = lo.compareTo(x.key), end = x.key.compareTo(hi);
        if (start < 0) {
            keys(x.left, q, lo, hi);
        }
        if (start <= 0 && end <= 0) {
            q.enqueue(x.key);
        }
        if (end < 0) {
            keys(x.right, q, lo, hi);
        }
    }

    // height
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    // copy **********************************************************************************
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (leftSize > rank) return select(x.left, rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else return x.key;
    }
    // copy **********************************************************************************

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x.key;
    }

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to ceiling() is too small");
        else return x.key;
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public static void main(String[] args) {
        BSTrb<Integer, String> st = new BSTrb<Integer, String>();
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
        StdOut.println(st.get(0) + "  " + st.get(5) + "  " + st.get(-2));
        st.deleteMin();
        st.draw();
        st.deleteMax();
        st.draw();
        st.delete(2);
        st.draw();

/*
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        BSTrb<String, Integer> st = new BSTrb<String, Integer>();
        for (int i = 0; i < keys.length; i++)
            st.put(keys[i], i);
        st.draw();

        StdOut.println("size = " + st.size());
        StdOut.println("min  = " + st.min());
        StdOut.println("max  = " + st.max());
        StdOut.println();


        // print keys in order using allKeys()
        StdOut.println("Testing keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // print keys in order using select
        StdOut.println("Testing select");
        StdOut.println("--------------------------------");
        for (int i = 0; i < st.size(); i++)
            StdOut.println(i + " " + st.select(i));
        StdOut.println();

        // test rank, floor, ceiling
        StdOut.println("key rank floor ceil");
        StdOut.println("-------------------");
        for (char i = 'A'; i <= 'X'; i++) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s\n", s, st.rank(s), st.floor(s), st.ceiling(s));
        }
        StdOut.println();

        // test range search and range count
        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to = { "Z", "A", "X", "Z", "G", "L" };
        StdOut.println("range search");
        StdOut.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                StdOut.print(s + " ");
            StdOut.println();
        }
        StdOut.println();

        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        st.draw();

        StdOut.println("After deleting the smallest " + st.size() / 2 + " keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
        st.draw();

        // delete all the remaining keys
        while (!st.isEmpty()) {
            st.delete(st.select(st.size() / 2));
        }
        st.draw();

        StdOut.println("After deleting the remaining keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        StdOut.println("After adding back n keys");
        StdOut.println("--------------------------------");
        for (int i = 0; i < keys.length; i++)
            st.put(keys[i], i);
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
        st.draw();

        StdOut.println();


        // insert N elements in order if one command-line argument supplied
        if (args.length == 0) return;
        int n = Integer.parseInt(args[0]);
        BSTrb<Integer, Integer> st2 = new BSTrb<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            st2.put(i, i);
            int h = st2.height();
            StdOut.println("i = " + i + ", height = " + h + ", size = " + st2.size());
        }

        // delete keys in random order
        StdOut.println("Deleting keys in random order");
        while (st2.size() > 0) {
            int i = StdRandom.uniform(n);
            if (st2.contains(i)) {
                st2.delete(i);
                int h = st2.height();
                StdOut.println("i = " + i + ", height = " + h + ", size = " + st2.size());
            }
        }

        StdOut.println("size = " + st2.size());
*/
    }
}
