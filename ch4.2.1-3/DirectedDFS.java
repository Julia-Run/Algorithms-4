/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
// java -classpath /home/dora/Codes/Algorithms-4/ch4.2.1-3:/home/dora/Codes/Algorithms-4/ch4.2.1-3/.lift/algs4.jar DirectedDFS tinyDG.txt 1 2 6
public class DirectedDFS {

    private boolean[] marked;
    private int count;

    public DirectedDFS(DirectedGraph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    public DirectedDFS(DirectedGraph g, Iterable<Integer> sources) {
        marked = new boolean[g.V()];
        for (int x : sources)
            if (!marked(x)) dfs(g, x);
    }

    public void dfs(DirectedGraph g, int v) {
        marked[v] = true;
        for (int x : g.adj(v))
            if (!marked(x)) dfs(g, x);
    }

    private boolean marked(int v) {
        return marked[v];
    }


    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph(new In(args[0]));

        Bag<Integer> s = new Bag<Integer>();
        for (int i = 1; i < args.length; ++i)
            s.add(Integer.parseInt(args[i]));

        DirectedDFS connected = new DirectedDFS(g, s);
        for (int i = 0; i < g.V(); ++i)
            if (connected.marked(i)) StdOut.print(i + "   ");
        StdOut.println();
        GraphDraw.draw(g);
    }
}
