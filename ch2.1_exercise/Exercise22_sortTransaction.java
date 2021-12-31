/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class Exercise22_sortTransaction {
    public static Exercise21_Transaction[] readTransactions() {
        Queue<Exercise21_Transaction> a = new Queue<Exercise21_Transaction>();
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            a.enqueue(new Exercise21_Transaction(line));
        }
        int n = a.size();
        Exercise21_Transaction[] t = new Exercise21_Transaction[n];
        for (int i = 0; i < n; i++) t[i] = a.dequeue();
        return t;
    }

    public static void main(String[] args) {
        Exercise21_Transaction[] t = readTransactions();
        Arrays.sort(t);
        for (int i = 0; i < t.length; i++) System.out.println(t[i]);
    }
}

