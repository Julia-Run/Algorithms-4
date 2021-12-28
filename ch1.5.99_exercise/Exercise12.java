/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise12 {

    /* variables*/

    private int[] id;  /* 对应int返回的id，linked则对应id值相等*/
    private int count; /* 多少个component，初始为n，link一对，减少一个*/

    /* convectors*/
    public Exercise12(int n) {  /* 构造未链接时的初始id*/
        id = new int[n];
        count = n;
        for (int i = 0; i < n; i++) id[i] = i;
    }

    /* method*/
    public int count() {
        /* 返回linked group的个数*/
        return count;
    }

    public int find(int p) {
        /* return int p 对应的root节点（id[root] = root）*/
        /* */
        int root = p;
        while (root != id[root]) root = id[root];  /* root value*/
        while (p != root) {
            int temp = p;  /* 当前leaf*/
            id[p] = root;  /* to root*/
            p = id[temp];  /* update leaf*/
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        /* 每个int对应一个root，将rootP树，接为rootQ的一个子树 */
        id[rootP] = rootQ;
        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getLength(int p) {
        int leng = 1;
        while (p != id[p]) {
            p = id[p];
            leng++;
        }
        return leng;
    }

    /* examples*/
    public static void main(String[] args) {
        // int n = StdIn.readInt();
        // Exercise12 parent = new Exercise12(n);
        //
        // while (!StdIn.isEmpty()) {
        //     int p = StdIn.readInt();
        //     int q = StdIn.readInt();
        //     if (parent.connected(p, q)) continue;
        //     parent.union(p, q);
        //     StdOut.println(p + " --> " + q);
        // }
        // StdOut.println(parent.count());
        Exercise12 wqu = new Exercise12(10);

        // Create size 4 group:   0 -- (1)
        wqu.union(0, 1);
        wqu.union(1, 2);
        wqu.union(2, 3);
        System.out.printf("length(3) = %d\n", wqu.getLength(0));
    }
}
