/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class RBbst<Key extends Comparable<Key>, Val> {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node implements TreeDrawer.PrintableNode {
        private Key key;
        private Val val;
        private int nodes;
        private boolean color;
        private Node left, right;

        public Node(Key k, Val v, int n, boolean c) {
            key = k;
            val = v;
            nodes = n;
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

    public void draw() {
        TreeDrawer.draw(root);
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.nodes;
    }


    public Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        y.nodes = x.nodes;
        x.nodes = size(x.left) + size(x.right) + 1;
        return y;
    }

    public Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.color = x.color;
        x.color = RED;
        y.nodes = x.nodes;
        x.nodes = size(x.left) + size(x.right) + 1;
        return y;
    }

    public void flipColor(Node x) {
        x.left.color = BLACK;
        x.right.color = BLACK;
        x.color = RED;
    }

    public void put(Key k, Val v) {
        root = put(root, k, v);
        root.color = BLACK;
    }

    public Node put(Node x, Key k, Val v) {
        if (x == null) return new Node(k, v, 1, RED);
        int com = k.compareTo(x.key);
        if (com < 0) x.left = put(x.left, k, v);
        else if (com > 0) x.right = put(x.right, k, v);
        else x.val = v;
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);
        x.nodes = size(x.left) + size(x.right) + 1;
        return x;
    }


    public static void main(String[] args) {
        RBbst<Integer, String> st = new RBbst<Integer, String>();
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
    }
}
