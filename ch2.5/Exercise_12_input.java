/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Exercise_12_input {

    /*  java Exercise_12_input 100 | java Exercise_12 */

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int time;
        for (int i = 0; i < n; i++) {
            time = StdRandom.uniform(1, 500);
            System.out.println("job" + i + "    " + time);
        }
    }
}

