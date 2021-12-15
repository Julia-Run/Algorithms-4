/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class Cat {
    public static void main(String[] args) {
        int N = args.length;
        Out out = new Out(args[N - 1]);  /*open out*/
        for (int i = 0; i < N - 1; i++) {
            In in = new In(args[i]);
            String s = in.readAll();
            out.print(s);
            in.close();
        }
        out.close();  /*close out*/
    }
}
