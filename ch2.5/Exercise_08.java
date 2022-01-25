/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class Exercise_08 {

    public static class Record implements Comparable {
        private String s;
        private int f;

        public Record(String s, int f) {
            this.s = s;
            this.f = f;
        }

        public int compareTo(Object t) {
            Record that = (Record) t;
            if (this.f > that.f) return -1;
            else if (this.f < that.f) return 1;
            else return 0;
        }

    }

    public static void main(String[] args) {

        String[] s = StdIn.readAllStrings();
        int n = s.length;
        Arrays.sort(s);
        Record[] r = new Record[n];

        String init = s[0];
        int f = 1;
        int index = 0;

        for (int i = 1; i < n; i++) {
            if (s[i].compareTo(init) != 0) { /* new*/
                r[index++] = new Record(init, f);
                init = s[i];
                f = 0;
            }
            f++;
        }
        r[index] = new Record(init, f);


        // for (int i = 0; i <= index; i++) {
        //     for (int j = i; j - 1 >= 0; j--) {
        //         if (r[j].f < r[j - 1].f) {
        //             Record larger = r[j - 1];
        //             r[j - 1] = r[j];
        //             r[j] = larger;
        //         }
        //     }
        // }

        Arrays.sort(r, 0, index + 1);  /* [lo,hi) */

        for (int i = 0; i <= index; i++) {
            System.out.printf("%-10s", r[i].s);
            System.out.printf("%4d", r[i].f);
            System.out.println();
        }
    }
}

