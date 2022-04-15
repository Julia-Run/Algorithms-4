/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LookIndex {
    // java -classpath /home/dora/Codes/Algorithms-4/ch3.5:/home/dora/Codes/Algorithms-4/ch3.5/.lift/algs4.jar LookIndex movies.txt "/"
    public static void main(String[] args) {

        ST<String, Queue<String>> st = new ST<String, Queue<String>>();
        ST<String, Queue<String>> ts = new ST<String, Queue<String>>();
        In in = new In(args[0]);
        String sp = args[1];

        while (in.hasNextLine()) {
            String x = in.readLine();
            String[] s = x.split(sp);
            String key = s[0];
            for (int i = 1; i < s.length; ++i) {
                String val = s[i];
                if (!st.contains(key)) st.put(key, new Queue<String>());
                if (!ts.contains(val)) ts.put(val, new Queue<String>());
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }
        while (!StdIn.isEmpty()) {
            String tar = StdIn.readLine();
            if (st.contains(tar)) {
                for (String x : st.get(tar)) StdOut.println("  " + x);
            }
            if (ts.contains(tar)) {
                if (ts.contains(tar)) {
                    // while (!ts.get(tar).isEmpty()) StdOut.println("  " + ts.get(tar).dequeue());
                    for (String x : ts.get(tar)) StdOut.println("  " + x);
                }
            }
        }
    }
}
