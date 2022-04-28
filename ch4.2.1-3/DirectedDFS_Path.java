/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// java -classpath /home/dora/Codes/Algorithms-4/ch4.2.1-3:/home/dora/Codes/Algorithms-4/ch4.2.1-3/.lift/algs4.jar DirectedDFS_Path tinyDG.txt 1 2 6
public class DirectedDFS_Path {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DirectedDFS_Path(DirectedGraph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    // public DirectedDFS_Path(DirectedGraph g, Iterable<Integer> sources) {
    //     marked = new boolean[g.V()];
    //     for (int x : sources)
    //         if (!marked(x)) dfs(g, x);
    // }

    public void dfs(DirectedGraph g, int v) {
        marked[v] = true;
        for (int x : g.adj(v))
            if (!marked(x)) {
                edgeTo[x] = v;
                dfs(g, x);
            }
    }

    private boolean marked(int v) {
        return marked[v];
    }

    private boolean hasPathTo(int v) {
        return marked[v];
    }

    private Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DirectedDFS_Path p = new DirectedDFS_Path(g, s);
        for (int i = 0; i < g.V(); ++i)
            if (p.hasPathTo(i)) {
                StdOut.print(s + " to " + i + ": ");
                for (int x : p.pathTo(i))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("->" + x);
                StdOut.println();
            }

        GraphDraw.draw(g);
    }
}
