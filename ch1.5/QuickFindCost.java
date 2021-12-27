/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.Color;

public class QuickFindCost {

    /* variables*/
    private int[] id;
    private int count;
    private static int cost = 0;

    /* convector*/
    public QuickFindCost(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }

    /* method*/
    public int count() {
        return count;
    }

    public int find(int p) {
        cost++;
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
                cost++;
            }
            cost++;
        }
        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public static void plot(int i, int c, int total) {
        double avg = total * 1.0 / i;
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.filledCircle(i, c, 0.05);
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(i, avg, 0.05);
    }

    /* examples*/
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int total = 0;
        System.out.println(n);
        QuickFindCost id = new QuickFindCost(n);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1300);
        StdDraw.textLeft(10, 625, "625");
        
        int i = 1;
        while (!StdIn.isEmpty()) {
            cost = 0;
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (id.connected(p, q)) {
                total += cost;//compute total cost, for plot
                plot(i, cost, total);
                i++;
                continue;
            }
            id.union(p, q);
            total += cost;
            plot(i, cost, total);
            // System.out.println(p + "  " + q);
            i++;
        }
        System.out.println(id.count() + " components.");
    }
}
