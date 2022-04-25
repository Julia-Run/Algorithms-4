/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CC {

    //  java -classpath /home/dora/Codes/Algorithms-4/ch4.1.6-7:/home/dora/Codes/Algorithms-4/ch4.1.6-7/.lift/algs4.jar CC tinyG.txt

    private boolean[] marked;
    private int count;
    private int[] id;

    public CC(MyGraph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        // count = 0;
        for (int i = 0; i < g.V(); ++i) {
            if (!marked[i]) {
                dfs(g, i);
                ++count;
            }
        }
    }

    public void dfs(MyGraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int x : g.adj(v)) {
            if (!marked(x))
                dfs(g, x);
        }
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph g = new MyGraph(in);
        CC search = new CC(g);
        int n = search.count();
        Bag<Integer>[] b = (Bag<Integer>[]) new Bag[n];
        for (int i = 0; i < n; ++i) {
            b[i] = new Bag<Integer>();
        }
        for (int i = 0; i < g.V(); ++i) {
            b[search.id[i]].add(i);
        }
        StdOut.println("subGraphs: ");
        for (int i = 0; i < n; ++i) {
            StdOut.print(i + ": ");
            for (Integer x : b[i]) {
                StdOut.print(x + " ");
            }
            StdOut.println();
        }


        GraphDraw.draw(g);
    }
}
