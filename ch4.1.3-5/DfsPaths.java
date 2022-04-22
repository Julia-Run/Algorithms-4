/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


// java -classpath /home/dora/Codes/Algorithms-4/ch4.1.1-2:/home/dora/Codes/Algorithms-4/ch4.1.1-2/.lift/algs4.jar DfsPaths tinyCG.txt 0
public class DfsPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;


    // constructor
    public DfsPaths(MyGraph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    public void dfs(MyGraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph g = new MyGraph(in);
        StdOut.print(g);
        int s = Integer.parseInt(args[1]);
        DfsPaths search = new DfsPaths(g, s);
        StdOut.println();
        for (int i = 0; i < g.V(); ++i) {
            if (search.hasPathTo(i)) {
                StdOut.print(s + " to " + i + ": ");
                for (int j : search.pathTo(i)) {
                    if (j == s) StdOut.print(s);
                    else StdOut.print(" --> " + j);
                }
                StdOut.println();
            }
        }
        GraphDraw.draw(g);
    }
}
