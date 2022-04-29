/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// java -classpath /home/dora/Codes/Algorithms-4/ch4.2.4:/home/dora/Codes/Algorithms-4/ch4.2.4/.lift/algs4.jar DFSorder tinyDAG.txt
public class DFSorder {

    private boolean[] marked;
    private int[] edgeTo;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DFSorder(Digraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        for (int v = 0; v < g.V(); ++v) {
            if (!marked(v)) dfs(g, v);
        }
    }

    public void dfs(Digraph g, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (int x : g.adj(v)) {
            if (!marked(x)) {
                edgeTo[x] = v;
                dfs(g, x);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }


    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }


    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DFSorder dfs = new DFSorder(G);

        StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();


    }
}
