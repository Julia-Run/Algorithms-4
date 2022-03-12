/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BSTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node implements TreeDrawer.PrintableNode {
        private Key key;
        private Value value;
        private int size;
        private Node left;
        private Node right;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
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

    public void draw() {
        draw(root);
    }

    private void draw(Node x) {
        TreeDrawer.draw(root);
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value get(Key key) {
        return get(root, key).value;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor(x.left, key);
        else if (cmp == 0) return x;
        else {
            Node t = floor(x.right, key);
            if (t == null) return x;
            else return t;
        }
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return ceiling(x.right, key);
        else if (cmp == 0) return x;
        else {
            Node t = ceiling(x.left, key);
            if (t == null) return x;
            else return t;
        }
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return rank(x.right, key) + size(x.left) + 1;
        else return size(x.left);
    }

    public Key select(int idx) {
        return select(root, idx).key;
    }

    private Node select(Node x, int idx) {
        if (x == null) return null;
        if (idx < size(x.left)) return select(x.left, idx);
        else if (idx > size(x.left)) return select(x.right, idx - size(x.left) - 1);
        else return x;
    }

    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node x) {
        if (x.left == null) return x.right;
        else x.left = delMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delMax() {
        root = delMax(root);
    }

    private Node delMax(Node x) {
        if (x.right == null) return x.left;
        else x.right = delMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void del(Key key) {
        root = del(root, key);
    }

    private Node del(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = del(x.left, key);
        else if (cmp > 0) x.right = del(x.right, key);
        else {
            if (x.left == null) return x.right;
            else if (x.right == null) return x.left;
            Node t = min(x.right);
            t.right = delMin(x.right);
            t.left = x.left;
            t.size = size(t.left) + size(t.right) + 1;
            return t;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void print() {
        print(root);
        StdOut.println();
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        StdOut.print(x.key + " ");
        print(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> result = new Queue<>();
        keys(root, result, lo, hi);
        return result;
    }

    public void keys(Node x, Queue<Key> result, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, result, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) result.enqueue(x.key);
        if (cmphi > 0) keys(x.right, result, lo, hi);
    }

    public static void main(String[] args) {
        BSTree<String, Double> bst = new BSTree<String, Double>();
        String filename = "./simple_character.txt";
        In infile = new In(filename);
        while (!infile.isEmpty()) {
            bst.put(infile.readString(), infile.readDouble());
            bst.draw();
        }
        StdOut.println(bst.get("X"));
        StdOut.println(bst.floor("F"));
        StdOut.println(bst.ceiling("Y"));
        StdOut.println(bst.ceiling("Z"));
        StdOut.println(bst.select(4));
        StdOut.println(bst.rank("X"));
        bst.delMin();
        bst.draw();
        bst.del("E");
        bst.draw();
        bst.del("T");
        bst.draw();
        bst.del("S");
        bst.draw();
        for (String s : bst.keys("D", "X")) {
            StdOut.println(s);
        }
        bst.print();
    }
}
