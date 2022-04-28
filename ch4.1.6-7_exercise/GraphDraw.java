/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

// import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Out;

public class GraphDraw {
    public static void writeCache(MyGraph g) {
        Out outfile = new Out("./GraphCache.txt");
        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v))
                outfile.println(v + ", " + w);
        }
        outfile.close();
    }

    public static void draw(MyGraph g) {
        writeCache(g);
        try {
            Runtime.getRuntime()
                   .exec("/home/dora/anaconda3/bin/python3 GraphDraw.py")
                   .waitFor();
            // .exec("/home/wlxing/Libraries/anaconda3/envs/algorithm4th/bin/python GraphDraw.py")
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
