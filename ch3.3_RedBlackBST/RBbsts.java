/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class RBbsts<Key extends Comparable<Key>, Val> {

    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node implements TreeDrawer.PrintableNode {
        private Key key;
        private Val val;
        private int nodeSize;
        private Node left, right;
        private boolean color;

        public Node(Key k, Val v, int n, boolean c) {
            key = k;
            val = v;
            nodeSize = n;
            color = c;
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

    // general func.
    public void draw() {
        TreeDrawer.draw(root);
    }

    public boolean isRED(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.nodeSize;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int height() {
        return height(root);
    }

    public int height(Node x) {
        if (x == null) return -1;
        return height(x.left) > height(x.right) ? height(x.left) + 1 : height(x.right) + 1;
        // return 1 + Math.max(height(x.left), height(x.right));
    }


    // prepare for put, rotateL, rotateR, flipColor
    public Node rotateLeft(Node x) {
        Node t = x.right;
        x.right = t.left;
        t.left = x;
        t.color = x.color;
        x.color = RED;
        t.nodeSize = x.nodeSize;
        x.nodeSize = size(x.left) + size(x.right) + 1;
        return t;
    }

    public Node rotateRight(Node x) {
        Node t = x.left;
        x.left = t.right;
        t.right = x;
        t.color = x.color;
        x.color = RED;
        t.nodeSize = x.nodeSize;
        x.nodeSize = size(x.left) + size(x.right) + 1;
        return t;
    }

    public void flipColor(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    // put
    public void put(Key k, Val v) {
        if (k == null) throw new IllegalArgumentException("k is null");
        root = put(root, k, v);
        root.color = BLACK;
    }

    public Node put(Node x, Key k, Val v) {
        if (x == null) return new Node(k, v, 1, RED);
        int com = k.compareTo(x.key);
        if (com < 0) x.left = put(x.left, k, v);
        else if (com > 0) x.right = put(x.right, k, v);
        else x.val = v;
        if (!isRED(x.left) && isRED(x.right)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColor(x);
        x.nodeSize = size(x.left) + size(x.right) + 1;
        return x;
    }

    // get: not related to colors
    public Val get(Key k) {
        if (k == null) throw new IllegalArgumentException("Key is null");
        return get(root, k);
    }

    public Val get(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com < 0) return get(x.left, k);
        else if (com > 0) return get(x.right, k);
        else return x.val;
    }

    // deleteMin -- change 2-, --> recursion --> refresh
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("empty"); // boundary
        // make sure root is not 2-
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }
        // to do it;
        root = deleteMin(root);
        // end boundary
        if (!isEmpty()) root.color = BLACK;
    }

    public Node deleteMin(Node x) {
        if (x.left == null) return null;  // boundary
        // make sure there is no 2- during the process
        if (!isRED(x.left) && !isRED(x.right)) {
            flipColor(x);  // 01, brother is 2-: 2- to temp 4-
            if (isRED(x.right.left)) { // 02: brother > 2-
                x.right = rotateRight(x.right); // split one element, and leave it on left;
                x = rotateLeft(x); // move this element to left, -- left: 2- to 3-
                flipColor(x); // flip twice = do nothing
            }
        }
        // recursion
        x.left = deleteMin(x.left);
        // refresh -- make sure left-lean BR tree
        if (!isRED(x.left) && isRED(x.right)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColor(x);
        x.nodeSize = size(x.left) + size(x.right) + 1;
        return x;
    }

    // deleteMax: left-lean to right-lean; change 2-, --> recursion --> refresh
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("empty");
        if (!isRED(root.left) && !isRED(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node x) {
        if (isRED(x.left)) x = rotateRight(x);  // left-lean to right-lean // move red to right
        if (x.right == null) return null;
        if (!isRED(x.right) && !isRED(x.right.left)) { // if left, right are both black;
            flipColor(x);  // x: 2- to >2-tree
            if (isRED(x.left.left)) {
                x = rotateRight(x);  // already splited to right
                flipColor(x);
            }
        }
        x.right = deleteMax(x.right);
        if (!isRED(x.left) && isRED(x.right)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.left.left)) flipColor(x);
        x.nodeSize = size(x.left) + size(x.right) + 1;
        return x;
    }

    // delete prepare
    private Node moveRedLeft(Node x) {
        flipColor(x);  // 01, brother is 2-: 2- to temp 4-
        if (isRED(x.right.left)) { // 02: brother > 2-
            x.right = rotateRight(x.right); // split one element, and leave it on left;
            x = rotateLeft(x); // move this element to left, -- left: 2- to 3-
            flipColor(x); // flip twice = do nothing
        }
        return x;
    }

    public Node moveRedRight(Node x) {
        flipColor(x);  // x: 2- to >2-tree
        if (isRED(x.left.left)) {
            x = rotateRight(x);  // already split to right
            flipColor(x);
        }
        return x;
    }

    public Node balance(Node x) {
        if (!isRED(x.left) && isRED(x.right)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.left.left)) flipColor(x);
        x.nodeSize = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else return max(x.right);
    }

    // delete: change 2-, --> recursion --> refresh
    // recursion: < = >
    public void delete(Key k) {
        if (k == null) throw new NoSuchElementException("k is null");
        if (!contains(k)) return; // later: k have to in the tree;
        if (!isRED(root.left) && !isRED(root.right)) root.color = RED;
        root = delete(root, k);
        if (!isEmpty()) root.color = BLACK;
    }

    public Node delete(Node x, Key k) {
        // key must be in, so you will not end at null;
        if (k.compareTo(x.key) < 0) {  // <
            // go to left;
            if (!isRED(x.left) && !isRED(x.left.left))
                x = moveRedLeft(x);
            x.left = delete(x.left, k);
        }
        else { // >=
            if (isRED(x.left)) x = rotateRight(x); // right-lean only at current Node;
            // 2 situations at leaf: 1-- null, null. 2-- red+null null, null
            // rotate first, or the next step may cause element lost (2--)
            if (k.compareTo(x.key) == 0 && x.right == null)
                return null;  // boundary  =, .right=null;
            if (!isRED(x.right) && !isRED(x.right.left)) { // if 2-Node
                // left:must red, since it rotated at the first step
                // right.right must be black; if right: black; right.left black
                x = moveRedRight(x);
            }
            if (k.compareTo(x.key) == 0) {                 // use right min
                Node t = min(x.right);
                x.key = t.key;
                x.val = t.val;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(x.right, k); // >
        }
        return balance(x);
    }

    public static void main(String[] args) {

        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        RBbsts<String, Integer> st = new RBbsts<String, Integer>();
        for (int i = 0; i < keys.length; i++)
            st.put(keys[i], i);

        StdOut.println("size = " + st.size());
        st.draw();

    }
}
