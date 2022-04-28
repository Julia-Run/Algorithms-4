/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DfsSearch {

    //  java -classpath /home/dora/Codes/Algorithms-4/ch4.1.6-7:/home/dora/Codes/Algorithms-4/ch4.1.6-7/.lift/algs4.jar DfsSearch tinyG.txt 0

    private boolean[] marked;
    private int count;

    public DfsSearch(MyGraph g, int s) {
        marked = new boolean[g.V()];
        count = 0;
        dfs(g, s);
    }

    public void dfs(MyGraph g, int v) {
        marked[v] = true;
        ++count;
        for (int x : g.adj(v)) {
            if (!marked(x)) dfs(g, x);
        }
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public int count() {
        return count;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph g = new MyGraph(in);
        int s = Integer.parseInt(args[1]);
        DfsSearch search = new DfsSearch(g, s);
        StdOut.println("Following are connected vertices:");
        for (int i = 0; i < g.V(); ++i) {
            if (search.marked[i]) StdOut.print(i + "  ");
        }
        GraphDraw.draw(g);
    }
}
