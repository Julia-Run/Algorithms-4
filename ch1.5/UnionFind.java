/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.Color;

public class UnionFind {

    /* variables*/
    private int[] id;
    private int count;

    private static int cost; /* for find cost drawing*/

    /* convector*/
    public UnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }

    /* method*/
    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
            cost++; /* for find cost drawing*/
        }
        cost++;
        return p;
        /* find root (index and value)*/
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        id[rootP] = rootQ;

        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public static void plot(int i, int c, int total) {   /* for find cost drawing*/
        double avg = total * 1.0 / i;
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.point(i, c);
        StdDraw.setPenColor(Color.RED);
        StdDraw.point(i, avg);
    }


    /* examples*/
    public static void main(String[] args) {
        int n = StdIn.readInt();
        UnionFind id = new UnionFind(n);
        StdDraw.setPenRadius(0.005);
        StdDraw.setXscale(0, 1000); /* for find cost drawing*/
        StdDraw.setYscale(0, 1000); /* for find cost drawing*/

        int i = 1;  /* for find cost drawing*/
        int total = 0; /* for find cost drawing*/
        while (!StdIn.isEmpty()) {
            cost = 0; /* for find cost drawing*/
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (id.connected(p, q)) {
                total += cost; /* for find cost drawing*/
                plot(i, cost, total); /* for find cost drawing*/
                i++; /* for find cost drawing*/
                continue;
            }
            id.union(p, q);
            total += cost; /* for find cost drawing*/
            plot(i, cost, total); /* for find cost drawing*/
            i++; /* for find cost drawing*/

            // System.out.println(p + "  " + q);
        }
        System.out.println(id.count() + " components.");
    }
}
