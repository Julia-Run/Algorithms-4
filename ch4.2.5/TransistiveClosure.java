/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

//   java -classpath /home/dora/Codes/Algorithms-4/ch4.2.5:/home/dora/Codes/Algorithms-4/ch4.2.5/.lift/algs4.jar TransistiveClosure tinyDG.txt 1 7

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TransistiveClosure {
    // reachable
    private DirectedDFS[] all;

    public TransistiveClosure(Digraph g) {
        all = new DirectedDFS[g.V()];
        for (int v = 0; v < g.V(); ++v) {
            all[v] = new DirectedDFS(g, v);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(new In(args[0]));
        TransistiveClosure t = new TransistiveClosure(g);
        int s = Integer.parseInt(args[1]);
        StdOut.println(t.reachable(s, Integer.parseInt(args[2])));
    }
}
