/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BfsPath {

    //   java -classpath /home/dora/Codes/Algorithms-4/ch4.1.6-7_exercise:/home/dora/Codes/Algorithms-4/ch4.1.6-7_exercise/.lift/algs4.jar BfsPath tinyCG.txt 0
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private final int s;

    public BfsPath(MyGraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int i = 0; i < g.V(); ++i) {
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[s] = 0;
        this.s = s;
        dfs(g, s);
    }

    public void dfs(MyGraph g, int w) {
        for (int i = 0; i < g.V(); ++i) {
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[w] = 0;
        Queue<Integer> q = new Queue<Integer>();
        marked[w] = true;
        q.enqueue(w);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int x : g.adj(v)) {
                if (!marked(x)) {
                    marked[x] = true;
                    edgeTo[x] = v;
                    distTo[x] = distTo[v] + 1;
                    q.enqueue(x);
                }
            }
        }
    }

    public int distTo(int v) {
        return distTo[v];
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
        BfsPath path = new BfsPath(g, s);
        StdOut.println("Paths from " + s + " to connected vertices: ");
        for (int i = 0; i < g.V(); ++i) {
            if (path.hasPathTo(i)) {
                StdOut.print(s + " to " + i + ": ");
                for (int x : path.pathTo(i)) {
                    if (x == s) StdOut.print(s);
                    else StdOut.print(" --> " + x);
                }
                // StdOut.print("test");
                StdOut.print("    Distance from " + s + " to " + i + " is " + path.distTo(i));
                StdOut.println();
            }
        }
        StdOut.println();
        GraphDraw.draw(g);
    }
}
