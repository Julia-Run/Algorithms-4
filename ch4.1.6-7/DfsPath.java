/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DfsPath {

    //  java -classpath /home/dora/Codes/Algorithms-4/ch4.1.6-7:/home/dora/Codes/Algorithms-4/ch4.1.6-7/.lift/algs4.jar DfsPath tinyCG.txt 0
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DfsPath(MyGraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    public void dfs(MyGraph g, int v) {
        marked[v] = true;
        for (int x : g.adj(v)) {
            if (!marked(x)) {
                edgeTo[x] = v;
                dfs(g, x);
            }
        }
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>(); // first in first out;
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph g = new MyGraph(in);
        int s = Integer.parseInt(args[1]);
        DfsPath path = new DfsPath(g, s);
        StdOut.println("Paths from " + s + " to connected vertices: ");
        for (int i = 0; i < g.V(); ++i) {
            if (path.hasPathTo(i)) {
                StdOut.print(s + " to " + i + ": ");
                for (int x : path.pathTo(i)) {
                    if (x == s) StdOut.print(s);
                    else StdOut.print(" --> " + x);
                }
                StdOut.println();
            }
        }
        GraphDraw.draw(g);
    }
}
