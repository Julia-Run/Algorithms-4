/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

//  java -classpath /home/dora/Codes/Algorithms-4/ch4.2.5_exercise:/home/dora/Codes/Algorithms-4/ch4.2.5_exercise/.lift/algs4.jar EulerCycle mediumDG.txt

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class EulerCycle {
    // go each edge once;  in = out; connected;
    // --- it euler cycle exists, you can start at any vertex.
    private Stack<Integer> cycle; // if euler exist, return this iterable;

    public int startPoint(Digraph g) {
        for (int v = 0; v < g.V(); ++v)
            if (g.outdegree(v) > 0) return v;
        return -1;
    }

    public EulerCycle(Digraph g) {
        if (g.E() == 0) return;
        for (int v = 0; v < g.V(); ++v) {
            if (g.indegree(v) != g.outdegree(v)) return;
        }

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[g.V()];
        // adj[v] iterator of out-edges from v;
        for (int v = 0; v < g.V(); ++v) {
            adj[v] = g.adj(v).iterator();
        }

        Stack<Integer> all = new Stack<Integer>(); // put next v to go into
        int s = startPoint(g);
        all.push(s);
        cycle = new Stack<Integer>();
        while (!all.isEmpty()) {
            int w = all.pop();
            while (adj[w].hasNext()) {  // current w's edge
                all.push(w);
                w = adj[w].next(); // forwards all edges;
            } // w done
            cycle.push(w);
        }
        if (cycle.size() != g.E() + 1) cycle = null;
    }

    public boolean hasEulerCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        if (!hasEulerCycle()) return null;
        return cycle;
    }

    public static void main(String[] args) {
        Digraph digraphWithDirectedEulerPath1 = new Digraph(4);
        digraphWithDirectedEulerPath1.addEdge(0, 1);
        digraphWithDirectedEulerPath1.addEdge(1, 2);
        digraphWithDirectedEulerPath1.addEdge(2, 3);
        digraphWithDirectedEulerPath1.addEdge(3, 0);
        digraphWithDirectedEulerPath1.addEdge(3, 2);

        EulerCycle directedEulerianCycle = new EulerCycle(digraphWithDirectedEulerPath1);
        Stack<Integer> eulerCycle1 = directedEulerianCycle.cycle;

        if (eulerCycle1 != null) {
            for (int x : eulerCycle1) StdOut.print(x + " ");
            StdOut.println();
        }
        else {
            StdOut.println("There is no directed Eulerian cycle");
        }
        StdOut.println("Expected: There is no directed Eulerian cycle\n");

        Digraph digraphWithDirectedEulerCycle1 = new Digraph(4);
        digraphWithDirectedEulerCycle1.addEdge(0, 1);
        digraphWithDirectedEulerCycle1.addEdge(1, 2);
        digraphWithDirectedEulerCycle1.addEdge(2, 3);
        digraphWithDirectedEulerCycle1.addEdge(3, 0);

        EulerCycle c = new EulerCycle(digraphWithDirectedEulerCycle1);
        Stack<Integer> eulerCycle2 = c.cycle;

        if (eulerCycle2 != null) {
            for (int x : eulerCycle2) StdOut.print(x + " ");
            StdOut.println();
        }
        else {
            StdOut.println("There is no directed Eulerian cycle");
        }
        StdOut.println("Expected: 3->0 0->1 1->2 2->3\n");

        //Note that vertex 5 is an isolated vertex
        Digraph digraphWithDirectedEulerCycle2 = new Digraph(5);
        digraphWithDirectedEulerCycle2.addEdge(0, 1);
        digraphWithDirectedEulerCycle2.addEdge(1, 2);
        digraphWithDirectedEulerCycle2.addEdge(2, 0);
        digraphWithDirectedEulerCycle2.addEdge(1, 3);
        digraphWithDirectedEulerCycle2.addEdge(3, 1);
        digraphWithDirectedEulerCycle2.addEdge(3, 2);
        digraphWithDirectedEulerCycle2.addEdge(2, 4);
        digraphWithDirectedEulerCycle2.addEdge(4, 3);

        EulerCycle d = new EulerCycle(digraphWithDirectedEulerCycle2);
        Stack<Integer> eulerCycle3 = d.cycle;


        if (eulerCycle3 != null) {
            for (int x : eulerCycle3) StdOut.print(x + " ");
            StdOut.println();
        }
        else {
            StdOut.println("There is no directed Eulerian cycle");
        }
        StdOut.println("Expected: 4->3 3->2 2->0 0->1 1->3 3->1 1->2 2->4\n");

        Digraph digraphWithDirectedEulerPath2 = new Digraph(4);
        digraphWithDirectedEulerPath2.addEdge(0, 1);
        digraphWithDirectedEulerPath2.addEdge(1, 2);
        digraphWithDirectedEulerPath2.addEdge(2, 3);
        digraphWithDirectedEulerPath2.addEdge(3, 0);
        digraphWithDirectedEulerPath2.addEdge(3, 1);


        EulerCycle e = new EulerCycle(digraphWithDirectedEulerPath2);
        Stack<Integer> eulerCycle4 = e.cycle;


        if (eulerCycle4 != null) {
            for (int x : eulerCycle4) StdOut.print(x + " ");
            StdOut.println();
        }
        else {
            StdOut.println("There is no directed Eulerian cycle");
        }
        StdOut.println("Expected: There is no directed Eulerian cycle");
    }

    private void printCycle(Stack<Integer> eulerCycle) {
        StdOut.println("Euler cycle:");

        while (!eulerCycle.isEmpty()) {
            int vertex = eulerCycle.pop();

            if (!eulerCycle.isEmpty()) {
                StdOut.print(vertex + "->" + eulerCycle.peek());

                if (eulerCycle.size() > 1) {
                    StdOut.print(" ");
                }
            }
        }
        StdOut.println();
    }
}
