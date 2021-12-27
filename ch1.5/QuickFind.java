/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class QuickFind {

    /* variables*/
    private int[] id;
    private int count;

    /* convector*/
    public QuickFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }

    /* method*/
    public int count() {
        return count;
    }

    public int find(int p) {
        return id[p];
        /* 认为输入value是index，返回id
         * 如果两个index相连，则认为他们的id相等，如果不相等，则修该为相等*/
    }

    public void union(int p, int q) {
        int idP = id[p];
        int idQ = id[q];
        if (idP == idQ) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == idP) {
                id[i] = idQ;
            }
        }
        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /* examples*/
    public static void main(String[] args) {
        int n = StdIn.readInt();
        System.out.println(n);
        QuickFind id = new QuickFind(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (id.connected(p, q)) {
                continue;
            }
            id.union(p, q);
            System.out.println(p + "  " + q);
        }
        System.out.println(id.count() + " components.");
    }
}
