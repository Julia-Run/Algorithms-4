/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class BST<Key extends Comparable<Key>, Val> {

    /* variables****************************************/
    private Node root;

    private class Node {
        private Key key;
        private Val val;
        private Node left;
        private Node right;
        private int n;

        public Node(Key k, Val v, int n) {
            this.key = k;
            this.val = v;
            this.n = n;
        }
    }

    /* methods*****************************************/
    /* put，向上更新 get*/
    public int size(Node x) {
        if (x == null) return 0;
        return x.n;
    }


    public Val get(Key k) {
        return get(root, k).val;
    }

    public Node get(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com < 0) return get(x.left, k);
        else if (com > 0) return get(x.right, k);
        else return x;
    }

    public void put(Key k, Val v) {
        root = put(root, k, v);
    }

    public Node put(Node x, Key k, Val v) {
        if (x == null) return new Node(k, v, 1);  /* leaf add */
        int com = k.compareTo(x.key);
        if (com < 0) x = put(x.left, k, v);
        else if (com > 0) x = put(x.right, k, v);
        else x.val = v;
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    /* min / max key*/
    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /* floor / ceiling*/
    public Key floor(Key k) {
        Node x = floor(root, k);
        if (x == null) return null;
        return x.key;
    }

    public Node floor(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com == 0) return x;
        else if (com < 0) return floor(x.left, k);
        else {
            Node t = floor(x.right, k);
            if (t != null) return t;
            else return x;
        }
    }

    public Key ceiling(Key k) {
        Node x = ceiling(root, k);
        if (x == null) return null;
        return x.key;
    }

    public Node ceiling(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com == 0) return x;
        else if (com > 0) return ceiling(x.right, k);
        else { /* k < x.key */
            Node t = ceiling(x.left, k);
            if (t != null) return t;
            return x;
        }
    }

    /* select / rank*/
    public Key select(int ki) {
        Node x = select(root, ki);
        if (x == null) return null;
        return x.key;
    }

    public Node select(Node x, int ki) {
        if (x == null) return null;
        int i = size(x) - 1;
        if (ki == i) return x;
        else if (ki < i) return select(x.left, ki);
        else return select(x.right, ki - size(x.left) - 1);
    }

    public int rank(Key k) {
        return rank(root, k);
    }

    public int rank(Node x, Key k) {
        if (x == null) return 0;
        int com = k.compareTo(x.key);
        if (com == 0) return size(x.left);
        else if (com < 0) return rank(x.left, k);
        else return 1 + size(x.left) + rank(x.right, k);
    }

    /* delmin 向上更新*/
    public void delMin() {
        root = delMin(root);
    }

    public Node delMin(Node x) {
        if (x.left == null) return x.right;
        x.left = delMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    /* delete(Key k) 向上更新*/
    public void delet(Key k) {
        root = delet(root, k);
    }

    public Node delet(Node x, Key k) {
        if (x == null) return null;
        int com = k.compareTo(x.key);
        if (com < 0) x.left = delet(x.left, k);
        else if (com > 0) x.right = delet(x.right, k);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);  /* 右边最小*/
            x.right = delMin(t.right);
            x.left = t.left;

        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }


    public static void main(String[] args) {
    }
}
