/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class LookCSV {
    // java -classpath /home/dora/Codes/Algorithms-4/ch3.5:/home/dora/Codes/Algorithms-4/ch3.5/.lift/algs4.jar LookCSV DJIA.csv 0 3
    public static void main(String[] args) {
        // thi is a dictionary
        In in = new In(args[0]);
        int k = Integer.parseInt(args[1]);
        int v = Integer.parseInt(args[2]);
        ST<String, String> st = new ST<String, String>();
        while (in.hasNextLine()) {
            String x = in.readLine();
            String[] s = x.split(",");
            String key = s[k];
            String val = s[v];
            st.put(key, val);
        }

        while (!StdIn.isEmpty()) {
            String target = StdIn.readString();
            if (st.contains(target)) StdOut.println(st.get(target));
        }
    }
}

