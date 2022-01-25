/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise_1 {
    public static void main(String[] args) {
        SymblesT<String, Double> s = new SymblesT<String, Double>();
        /* A+    A     A-    B+    B     B-    C+    C     C-    D     F
          4.33  4.00  3.67  3.33  3.00  2.67  2.33  2.00  1.67  1.00  0.00 */
        s.put("A+", 4.33);
        s.put("A", 4.0);
        s.put("A-", 3.67);
        s.put("B+", 3.33);
        s.put("B", 3.0);
        s.put("B-", 2.67);
        s.put("C+", 2.33);
        s.put("C", 2.0);
        s.put("C-", 1.67);
        s.put("D", 1.0);
        s.put("F", 0.0);

        double total = 0.0;
        int n = 0;
        while (!StdIn.isEmpty()) {
            String i = StdIn.readString();
            total += s.get(i);
            n++;
        }
        double avg = total / n;
        StdOut.println("GPA = " + avg);
        StdOut.println("GPA = " + avg);
    }
}
