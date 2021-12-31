/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;

public class test {
    public static void main(String[] args) {
        StdDraw.setXscale(-3, 100);
        StdDraw.setYscale(-3, 3);
        for (int i = 0; i < 13; i++) {
            StdDraw.text(7 * i, 0, i + "g");
            StdDraw.text(7 * i, 2, i + "ood");
        }
        System.out.println(Double.compare(2, 2));
    }
}
