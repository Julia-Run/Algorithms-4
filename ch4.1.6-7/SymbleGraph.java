/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class SymbleGraph {
    // st<string,int>, int -- st.size();   keys[int, string]
    // Graph<int,int>
    // input: .txt + "delim"
    private ST<String, Integer> st;
    private String[] keys;
    private MyGraph g;
    private int V;

    public SymbleGraph(String stream, String sp) {
        st = new ST<String, Integer>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String s = in.readLine();
            String[] sa = s.split(sp);
            for (int i = 0; i < sa.length; ++i) {
                if (!st.contains(sa[i])) st.put(sa[i], st.size());
            }
        }

        V = st.size();
        keys = new String[V];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        g = new MyGraph(V);
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; ++i)
                g.addEdge(v, st.get(a[i]));
        }
    }

    public int V() {
        return V;
    }

    public String name(int v) {
        return keys[v];
    }

    public int index(String s) {
        return st.get(s);
    }

    public MyGraph G() {
        return g;
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public static void main(String[] args) {
        SymbleGraph sg = new SymbleGraph(args[0], args[1]);
        MyGraph g = sg.G();

        StdOut.println();
        StdOut.println("Total vertices are " + sg.V());
        int a = Integer.parseInt(args[2]);
        StdOut.println(sg.name(a));
        for (Integer i : g.adj(a)) {
            StdOut.println("    " + sg.name(i));
        }
    }
}
