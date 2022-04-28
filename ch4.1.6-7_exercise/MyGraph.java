/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MyGraph {
    // java -classpath /home/dora/Codes/Algorithms-4/ch4.1.1-2:/home/dora/Codes/Algorithms-4/ch4.1.1-2/.lift/algs4.jar MyGraph mediumG.txt
    // variables
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    // Constructor
    public MyGraph(int x) {
        V = x;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[x];  // fix size
        for (int i = 0; i < V; ++i) {
            adj[i] = new Bag<Integer>();  // unknown size
        }
    }

    public MyGraph(In in) {
        this(in.readInt());  // to construct adj
        int e = in.readInt(); // E will be refresh during addEdge process
        for (int i = 0; i < e; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    // creat a g from another g;
    public MyGraph(MyGraph g) {
        V = g.V();
        E = g.E();
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < g.V(); ++i) {
            Stack<Integer> s = new Stack<Integer>();
            for (int w : g.adj(i)) {
                s.push(w);
            }
            for (int w : s) {
                adj[i].add(w);
            }
        }
    }


    // methods
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        ++E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private String toFormat(int i) {
        String t = "";
        if (i < 10) t = "00" + i;
        else if (i < 100) t = "0" + i;
        else t += i;
        return t;
    }

    public String toString() {
        String s = V + " Vertices, " + E + " Edges.\n";
        for (int i = 0; i < V; ++i) {
            s = s + toFormat(i) + ": ";
            for (int j : adj[i]) {
                s = s + toFormat(j) + " ";
            }
            s += "\n";
        }
        return s;
    }


    // other static methods

    public static int degree(MyGraph G, int v) {
        int d = 0;
        for (int i : G.adj[v]) ++d;
        return d;
    }

    public static int maxDegree(MyGraph g) {
        int m = 0;
        for (int i = 0; i < g.V; ++i) m = Math.max(m, degree(g, i));
        return m;
    }

    public static double avgDegree(MyGraph g) {
        return 2.0 * g.E() / g.V();
    }

    public static int numberOfSelfLoops(MyGraph g) {
        int n = 0;
        for (int i = 0; i < g.V(); ++i) {
            for (int j : g.adj(i)) if (j == i) ++n;
        }
        return n;
    }


    // instance
    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph g = new MyGraph(in);
        StdOut.print(g);
        GraphDraw.draw(g);
    }
}
