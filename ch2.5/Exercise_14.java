/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Exercise_14 {

    /*  java Exercise_14 < e14.txt  */
    /* Domain class ******************************************************************************/
    public static class Domain implements Comparable<Domain> {
        // private String normal;
        private String reverse = "";
        private int len;

        public Domain(String s) {
            // normal = s;
            String[] helper = s.split("\\.");
            len = helper.length;
            for (int i = len - 1; i >= 0; i--) {
                reverse += helper[i];
                if (i != 0) reverse += ".";
            }
        }

        public String getReverse() {
            return reverse;
        }

        public int compareTo(Domain that) {
            if (this == that) return 0;
            return (this.getReverse().compareTo(that.getReverse()));
        }
    }

    /* Domain class ******************************************************************************/
    // public static class Domain implements Comparable<Domain> {
    //     // private String normal;
    //     private String[] normal;
    //     private String[] reverse;
    //     private int len;
    //
    //     public Domain(String s) {
    //         normal = s.split("\\.");
    //         len = normal.length;
    //         reverse = new String[len];
    //         int j = 0;
    //         for (int i = len - 1; i >= 0; i--) {
    //             reverse[j++] = normal[i];
    //         }
    //     }
    //
    //     public String getReverse() {
    //         String s = "";
    //         for (int i = 0; i < len; i++) {
    //             s += reverse[i];
    //             if (i < len - 1) s += ".";
    //         }
    //         return s;
    //     }
    //
    //     public int compareTo(Domain that) {
    //         if (this == that) return 0;
    //         int c;
    //         for (int i = 0; i < Math.min(this.len, that.len); i++) {
    //             c = this.reverse[i].compareTo(that.reverse[i]);
    //             if (c > 0) return 1;  /* 小数点隔开位先比较 */
    //             else if (c < 0) return -1;
    //         }
    //         return this.len - that.len;
    //     }
    // }

    /* main **************************************************************************************/
    public static void main(String[] args) {
        String[] s = StdIn.readAllLines();
        int n = s.length;

        Domain[] d = new Domain[n];
        int indexD = 0;
        for (int i = 0; i < n; i++) d[indexD++] = new Domain(s[i]);

        /* shell sort domain[]*/
        int h = 1;
        while (h < indexD / 3) h = 1 + 3 * h;
        while (h >= 1) {
            for (int i = h; i < indexD; i++) {
                for (int j = i; j - h >= 0 && d[j].compareTo(d[j - h]) < 0; j -= h) {
                    Domain less = d[j];
                    d[j] = d[j - h];
                    d[j - h] = less;
                }
            }
            h = (h - 1) / 3;
        }

        for (int i = 0; i < indexD; i++) System.out.println(d[i].getReverse());
    }
}

