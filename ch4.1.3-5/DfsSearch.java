/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
// java -classpath /home/dora/Codes/Algorithms-4/ch4.1.1-2:/home/dora/Codes/Algorithms-4/ch4.1.1-2/.lift/algs4.jar DfsSearch tinyG.txt
public class DfsSearch {
    private boolean[] marked;
    private int count;

    // constructor
    public DfsSearch(MyGraph g, int s) {
        marked = new boolean[g.V()];
        count = 0;
        dfs(g, s);
    }

    public void dfs(MyGraph g, int v) {
        marked[v] = true;
        ++count;
        for (int w : g.adj(v)) {
            if (!marked[w]) dfs(g, w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph g = new MyGraph(in);
        StdOut.print(g);
        DfsSearch search = new DfsSearch(g, 0);
        StdOut.println();
        StdOut.println("Following vertices Connected with 0");
        for (int i = 0; i < g.V(); ++i) {
            if (search.marked[i]) StdOut.print(i + " ");
        }
        StdOut.println();
        if (search.count() != g.V())
            StdOut.print("NOT ");
        StdOut.println("Connected!");
        GraphDraw.draw(g);
    }
}
