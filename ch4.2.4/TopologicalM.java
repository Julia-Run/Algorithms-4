/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;
// java -classpath /home/dora/Codes/Algorithms-4/ch4.2.4:/home/dora/Codes/Algorithms-4/ch4.2.4/.lift/algs4.jar TopologicalM jobs.txt /

public class TopologicalM {
    private Iterable<Integer> order;

    public TopologicalM(Digraph g) {
        DirectedCycleM c = new DirectedCycleM(g);
        DFSorder path = new DFSorder(g);
        if (!c.hasCycle()) order = path.reversePost();
    }

    public boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String sp = args[1];
        SymbolDigraph g = new SymbolDigraph(filename, sp);
        TopologicalM topo = new TopologicalM(g.G());
        for (int x : topo.order()) StdOut.println(g.nameOf(x));
    }
}
