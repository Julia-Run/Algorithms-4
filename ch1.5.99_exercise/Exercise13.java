/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise13 {

    /* variables*/
    private int[] id;  /* 对应int返回的id，linked则对应id值相等*/
    private int count; /* 多少个component，初始为n，link一对，减少一个*/
    private int[] size;

    /* convectors*/
    public Exercise13(int n) {  /* 构造未链接时的初始id*/
        id = new int[n];
        size = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    /* method*/
    public int count() {
        /* 返回linked group的个数*/
        return count;
    }

    public int find(int p) {
        /* return int p 对应的root节点（id[root] = root）*/
        /* */
        validate(p);
        int root = p;
        /* 数组实现，只能向上更改，即如果p一直为root，则树高会增加，并不会一直扁平，见测试*/
        while (root != id[root]) root = id[root];  /* root value*/
        while (p != root) {
            int temp = p;  /* 当前leaf*/
            id[p] = root;  /* to root*/
            p = id[temp];  /* update leaf*/
        }
        return p;
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        /* 每个int对应一个root，将rootP树，接为rootQ的一个子树 */
        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];

        }
        count--;
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public int getLength(int p) {
        int lens = 1;
        while (p != id[p]) {
            p = id[p];
            lens++;
        }
        return lens;
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    /* examples*/
    public static void main(String[] args) {
        // int n = StdIn.readInt();
        // WQuickUnionPathCompression parent = new WQuickUnionPathCompression(n);
        //
        // while (!StdIn.isEmpty()) {
        //     int p = StdIn.readInt();
        //     int q = StdIn.readInt();
        //     if (parent.connected(p, q)) continue;
        //     parent.union(p, q);
        //     StdOut.println(p + " --> " + q);
        // }
        // StdOut.println(parent.count());
        Exercise13 wqu = new Exercise13(10);

        // Create size 4 group:   0 -- (1) (2) (3)
        wqu.union(0, 1);
        wqu.union(0, 2);
        wqu.union(0, 3);
        // Create size 2 group    0 -- (1) (2) (3)     4--(5)
        wqu.union(4, 5);
        // Create size 2 group, make 7 child of 6      0 -- (1) (2) (3)      4--(5)  6--(7)
        wqu.union(6, 7);
        System.out.printf("length(7) = %d\n", wqu.getLength(7));
        // Create size 4 group    0 -- (1) (2) (3)      4--(5) ( 6--(7) )
        wqu.union(4, 6);
        System.out.printf("length(7) = %d\n", wqu.getLength(7));
        // Create size 8 group    0 -- (1) (2) (3) ( 4--(5) ( 6--(7) ) )
        wqu.union(0, 4);
        System.out.printf("length(7) = %d\n", wqu.getLength(7));
    }
}
