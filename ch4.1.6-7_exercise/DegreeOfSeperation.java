/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// java -classpath /home/dora/Codes/Algorithms-4/ch4.1.6-7_exercise:/home/dora/Codes/Algorithms-4/ch4.1.6-7_exercise/.lift/algs4.jar DegreeOfSeperation movies.txt  '/'  'Bacon, Kevin'
public class DegreeOfSeperation {
    public static void main(String[] args) {
        SymbleGraph sg = new SymbleGraph(args[0], args[1]);
        MyGraph g = sg.G();  // the network connected actors and movies
        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(" ' " + source + " ' " + " is not in database. ");
            return;
        }
        int s = sg.index(source);
        BfsPath path = new BfsPath(g, s);
        while (!StdIn.isEmpty()) {
            String end = StdIn.readLine();
            if (!sg.contains(end)) {
                StdOut.println(" ' " + end + " ' " + " is not in database. ");
                StdOut.println();
                continue;
            }
            int e = sg.index(end);
            int len = -1;
            if (path.hasPathTo(e)) {
                for (int x : path.pathTo(e)) {
                    StdOut.println("   " + sg.name(x));
                    ++len;
                }
            }
            StdOut.println(
                    "The total length from " + " ' " + source + " ' " + " to " + " ' " + end + " ' "
                            + " is " + len);
            StdOut.println();
        }
    }
}
