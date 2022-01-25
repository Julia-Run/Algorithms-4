/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class FrequencyCounter {
    public static void main(String[] args) {

        SymblesT<String, Integer> st = new SymblesT<String, Integer>();
        int minlen = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            if (!st.contains(key)) st.put(key, 1);
            else st.put(key, st.get(key) + 1);
        }
        String max = " ";
        st.put(max, 0);
        for (String word : st) {
            if (st.get(word) > st.get(max)) max = word;
        }
        System.out.println("'" + max + "' appears for " + st.get(max) + " times");
    }
}
