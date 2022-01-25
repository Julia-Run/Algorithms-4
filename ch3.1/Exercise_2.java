/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise_2 {
    public static class UnorderedListST<Key extends Comparable<Key>, Val> {
        private Key[] keys;
        private Val[] vals;
        private int n;

        public UnorderedListST(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            vals = (Val[]) new Comparable[capacity];
        }

        public void put(Key k, Val v) {
            keys[n] = k;
            vals[n] = v;
            n++;
        }

        public Val get(Key k) {
            for (int i = 0; i < n; i++) {
                if (k.compareTo(keys[i]) == 0) return vals[i];
            }
            return null;
        }

        private boolean isEmpty() {
            return n == 0;
        }

        public void del(Key k) {
            if (isEmpty()) return;
            int i = 0;
            for (i = 0; i < n; i++) {
                if (k.compareTo(keys[i]) == 0) break;
            }
            for (int j = i; j < n - 1; j++) {
                keys[i] = keys[i + 1];
                vals[i] = vals[i + 1];
            }
            n--;
            keys[n] = null;
            vals[n] = null;
        }

        public Iterable<Key> keys() {
            Queue<Key> q = new Queue<Key>();
            for (int i = 0; i < n; i++) q.enqueue(keys[i]);
            return q;
        }


    } /* this is the end of this class*/


    public static void main(String[] args) {
        UnorderedListST<String, Integer> st = new UnorderedListST<String, Integer>(20);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
