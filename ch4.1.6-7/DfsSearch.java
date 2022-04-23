/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class DfsSearch {
    private boolean[] marked;

    public void dfs(MyGraph g, int v) {
        marked[v] = true;
        for (int x : g.adj(v)) {
            if (!marked(x)) dfs(g, x);
        }
    }

    public boolean marked(int s) {
        return marked[s];
    }


    public static void main(String[] args) {
    }
}
