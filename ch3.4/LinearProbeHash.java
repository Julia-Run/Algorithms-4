/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class LinearProbeHash<Key, Val> {
    // variables
    private int n; // key's nums
    private int m = 16; // array size
    private Key[] keys;
    private Val[] vals;

    public LinearProbeHash() {
        keys = (Key[]) new Object[m];
        vals = (Val[]) new Object[m];
        n = 0;
    }

    public int hash(Key k) {
        return (k.hashCode() & 0x7fffffff) % m;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Val get(Key k) {
        if (isEmpty()) return null;
        for (int i = hash(k); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(k)) return vals[i];
        }
        return null;
    }

    public boolean contains(Key k) {
        return get(k) != null;
    }

    public void put(Key k, Val v) {
        if (n >= m / 2) resize(2 * m);
        int i;
        for (i = hash(k); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(k)) {
                vals[i] = v;
                return;
            }
        }
        keys[i] = k;
        vals[i] = v;
        ++n;
    }

    public void delete(Key k) {
        if (!contains(k)) return;
        // must exist in the following set;
        int i;
        for (i = hash(k); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(k)) {
                keys[i] = null;
                vals[i] = null;
                break;
            }
        }
        i = (i + 1) % m; // re-put following elements
        for (; keys[i] != null; i = (i + 1) % m) {
            Key tempk = keys[i];
            Val tempv = vals[i];
            keys[i] = null;
            vals[i] = null;
            --n;
            put(tempk, tempv);
        }
        --n;
        if (n > 0 && n <= m / 8) resize(m / 2);
    }

    public void resize(int x) {
        Key[] tempk = (Key[]) new Object[x];
        Val[] tempv = (Val[]) new Object[x];
        for (int i = 0; i < n; ++i) {
            if (keys[i] != null) {
                tempk[i] = keys[i];
                tempv[i] = vals[i];
            }
        }
        keys = tempk;
        vals = tempv;
    }


    public static void main(String[] args) {
        LinearProbeHash<Integer, Integer> t = new LinearProbeHash<Integer, Integer>();
        t.put(1, 100);
        t.put(2, 200);
        t.put(3, 300);
        t.put(4, 400);
        t.delete(3);
        StdOut.println(t.get(2));
        StdOut.println(t.get(3));
        StdOut.println(t.get(1));
        StdOut.println(t.contains(3));

    }
}
