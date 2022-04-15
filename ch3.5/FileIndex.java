/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class FileIndex {
    // java -classpath /home/dora/Codes/Algorithms-4/ch3.5:/home/dora/Codes/Algorithms-4/ch3.5/.lift/algs4.jar LookIndex movies.txt "/"
    public static void main(String[] args) {

        ST<String, SET<File>> st = new ST<String, SET<File>>();
        for (String filename : args) {
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()) {
                String words = StdIn.readString();
                if (!st.contains(words)) st.put(words, new SET<File>());
                st.get(words).add(file);
            }
        }
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (st.contains(s)) {
                for (File f : st.get(s)) StdOut.println("   " + f.getName());
            }
        }
    }
}
