/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

public class SeperateChainHash<Key, Val> {
    // global variables
    private int n;  // total size
    private int m;  // chain nums
    private SequentialSearchST<Key, Val>[] st;

    // constructors
    public SeperateChainHash() {
        this(997);
    }

    public SeperateChainHash(int x) {
        m = x;
        st = (SequentialSearchST<Key, Val>[]) new SequentialSearchST[m];   // m chains
        for (int i = 0; i < m; ++i) {
            st[i] = new SequentialSearchST<Key, Val>();  // init each chain;
        }
    }

    public int hash(Key k) {
        return (k.hashCode() & 0x7fffffff) % m;  // get hash of k which will be index in st
    }

    public Val get(Key k) {
        return st[hash(k)].get(k);
    }

    public void put(Key k, Val v) {
        st[hash(k)].put(k, v);
    }


    public static void main(String[] args) {
        SeperateChainHash<Integer, Integer> t = new SeperateChainHash<Integer, Integer>();
        t.put(1, 100);
        t.put(2, 200);
        t.put(3, 300);
        StdOut.println(t.get(2));
    }
}
