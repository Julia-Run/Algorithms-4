/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Exercise_12 {

    /*  java Exercise_12_input 100 | java Exercise_12 */

    public static class Jobs implements Comparable<Jobs> {
        private String name;
        private int time;

        public Jobs(String n, int t) {
            this.name = n;
            this.time = t;
        }

        public int compareTo(Jobs that) {
            if (this.time > that.time) return 1;
            else if (this.time < that.time) return -1;
            else return 0;
        }

        public String toString() {
            return String.format("%-6s", this.name) + String.format("%4d", this.time) + " s";
        }

    }

    public static void main(String[] args) {

        String[] s = StdIn.readAllLines();
        int n = s.length;
        Jobs[] box = new Jobs[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            String[] temp = s[i].split("\\s+");  /* \s表示匹配任何空白字符，+表示匹配一次或多次。*/
            String name = temp[0];
            int time = Integer.parseInt(temp[1]);
            box[index++] = new Jobs(name, time);
        }

        /* select sort*/
        int min = -1;
        for (int i = 0; i < index; i++) {
            min = i;
            for (int j = i; j < index; j++) {
                if (box[j].compareTo(box[min]) < 0) min = j;
            }
            Jobs exch = box[i];
            box[i] = box[min];
            box[min] = exch;
        }

        for (int i = 0; i < index; i++) System.out.println(box[i]);

    }
}

