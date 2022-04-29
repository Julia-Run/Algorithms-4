/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// java -classpath /home/dora/Codes/Algorithms-4/ch4.2.4:/home/dora/Codes/Algorithms-4/ch4.2.4/.lift/algs4.jar DirectedCycleM tinyDG.txt
public class DirectedCycleM {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycleM(Digraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        onStack = new boolean[g.V()];
        for (int v = 0; v < g.V(); ++v) {
            if (!marked(v)) dfs(g, v);
        }
    }

    public void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int x : g.adj(v)) {
            if (hasCycle()) return;
            else if (!marked(x)) {
                edgeTo[x] = v;
                dfs(g, x);
            }
            else if (onStack(x)) {
                cycle = new Stack<Integer>();
                for (int w = v; w != x; w = edgeTo[w])
                    cycle.push(w);
                cycle.push(x);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public boolean onStack(int v) {
        return onStack[v];
    }

    public Iterable<Integer> cycle() {
        if (!hasCycle()) return null;
        return cycle;
    }


    public static void main(String[] args) {
        Digraph g = new Digraph(new In(args[0]));
        DirectedCycleM c = new DirectedCycleM(g);
        if (c.hasCycle()) {
            for (int x : c.cycle()) StdOut.print(x + " ");
        }
        else StdOut.println("No Directed Cycles");
    }
}
