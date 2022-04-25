/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Cycle {

    //  java -classpath /home/dora/Codes/Algorithms-4/ch4.1.6-7:/home/dora/Codes/Algorithms-4/ch4.1.6-7/.lift/algs4.jar Cycle tinyG.txt 0

    private boolean[] marked;
    private boolean has;
    private int count;

    public Cycle(MyGraph g) {
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); ++i) {
            if (!marked[i])
                dfs(g, i, i);
        }
    }

    public void dfs(MyGraph g, int v, int w) {
        marked[v] = true;
        for (int x : g.adj(v)) {
            if (!marked(x))
                dfs(g, x, w);
            else if (x != w) {
                has = true;
                ++count;
            }
        }
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public boolean hasCycle() {
        return has;
    }

    // public int count() { // nonsense
    //     return count;
    // }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph g = new MyGraph(in);
        Cycle c = new Cycle(g);

        StdOut.println("Is there any cycle? ---" + c.hasCycle());
        // StdOut.println("How many cycles? ---" + c.count()); // nonsense
        GraphDraw.draw(g);
    }
}
