/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Exercise14 {

    /* variables*/
    private int[] id;  /* 对应int返回的id，linked则对应id值相等*/
    private int count; /* 多少个component，初始为n，link一对，减少一个*/
    private int[] height;

    private int maxHeight;


    /* convectors*/
    public Exercise14(int n) {  /* 构造未链接时的初始id*/
        id = new int[n];
        height = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            height[i] = 0;
        }
    }

    /* method*/
    public int count() {
        /* 返回linked group的个数*/
        return count;
    }

    public int find(int p) {
        /* return int p 对应的root节点（id[root] = root）*/
        validate(p);
        while (p != id[p]) p = id[p];  /* root value*/
        return p;
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        /* 每个int对应一个root，将rootP树，接为rootQ的一个子树 */
        if (height[rootP] < height[rootQ]) id[rootP] = rootQ;
        else if (height[rootP] > height[rootQ]) id[rootQ] = rootP;
        else {
            id[rootQ] = rootP;
            height[rootP] += 1;
            if (height[rootP] > maxHeight) maxHeight = height[rootP];
        }
        count--;
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }


    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    public int getHeight(int p) {
        return height[p];
    }

    /* examples*/
    public static void main(String[] args) {
        // Exercise14 wqu = new Exercise14(10);
        // wqu.union(0, 1);   /* 0---(1)*/
        // wqu.union(2, 3);   /* 0---(1)   2---(3)*/
        // wqu.union(0, 2);   /* 0---(1) ( 2---(3) )*/
        // System.out.printf("height(0) = %d\n", wqu.getHeight(0));
        // System.out.printf("height(2) = %d\n", wqu.getHeight(2));
        // System.out.printf("height(1) = %d\n", wqu.getHeight(1));
        // System.out.printf("height(3) = %d\n", wqu.getHeight(3));

        Exercise14 weightedQuickUnionByHeight = new Exercise14(19);

        weightedQuickUnionByHeight.union(0, 1);
        weightedQuickUnionByHeight.union(0, 2);
        weightedQuickUnionByHeight.union(0, 3);
        weightedQuickUnionByHeight.union(6, 7);
        weightedQuickUnionByHeight.union(8, 9);
        weightedQuickUnionByHeight.union(6, 8);
        weightedQuickUnionByHeight.union(0, 6);
        weightedQuickUnionByHeight.union(10, 11);
        weightedQuickUnionByHeight.union(10, 12);
        weightedQuickUnionByHeight.union(10, 13);
        weightedQuickUnionByHeight.union(10, 14);
        weightedQuickUnionByHeight.union(10, 15);
        weightedQuickUnionByHeight.union(10, 16);
        weightedQuickUnionByHeight.union(10, 17);
        weightedQuickUnionByHeight.union(10, 18);
        weightedQuickUnionByHeight.union(0, 10);

        StdOut.println("Components: " + weightedQuickUnionByHeight.count() + " Expected: 3");
        StdOut.println("Maximum height: " + weightedQuickUnionByHeight.maxHeight
                               + " Expected: Equal or less than 5 for N = 19" +
                               " (lg 19 = 5)");
    }
}
