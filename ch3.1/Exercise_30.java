/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Exercise_30<Keys extends Comparable<Keys>, Vals> {

    /* variables**************************************************************************/
    private Keys[] keyBox;
    private Vals[] valBox;
    private int n;

    public Exercise_30(int c) {
        keyBox = (Keys[]) new Comparable[c];
        valBox = (Vals[]) new Comparable[c];
        n = 0;
    }

    /* methods******************************************************************************************************/
    private boolean isEmpty() {
        return n == 0;
    }

    private int rank(Keys k) {
        int lo = 0, hi = n - 1;
        int mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (k.compareTo(keyBox[mid]) < 0) hi = mid - 1;
            else if (k.compareTo(keyBox[mid]) > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public Vals get(Keys key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && key.compareTo(keyBox[i]) == 0) return valBox[i];
        return null;
    }

    public void put(Keys key, Vals val) {
        int i = rank(key);  /* empty的时候返回0*/
        if (i < n && key.compareTo(keyBox[i]) == 0) {
            valBox[i] = val;
            return;
        }
        for (int j = n; j > i; j--) {
            keyBox[j] = keyBox[j - 1];  /* i+1 取得了i的值*/
            valBox[j] = valBox[j - 1];
        }
        keyBox[i] = key;
        valBox[i] = val;
        n++;

        assert check();

    }

    public void del(Keys k) {
        if (k == null) throw new NoSuchElementException(k + " : not in the ST");
        if (isEmpty()) return;
        int i = rank(k);
        if (i == n || k.compareTo(keyBox[i]) != 0) return;
        for (int j = i; j < n - 1; j++) {
            keyBox[j] = keyBox[j + 1];
            valBox[j] = valBox[j + 1];
        }
        n--;
        keyBox[n] = null;
        valBox[n] = null;
        assert check();
    }


    public boolean contains(Keys key) {
        return get(key) != null;
    }

    public Keys ceilling(Keys k) {
        int i = rank(k);
        return keyBox[i];
    }

    public Keys floor(Keys k) {
        if (k == null) throw new IllegalArgumentException("Keys cannot be 'null'. ");
        int i = rank(k);
        if (i < n && k.compareTo(keyBox[i]) == 0) return keyBox[i];
        else if (i == 0) throw new NoSuchElementException("The key is too small");
        else return keyBox[i - 1];
    }

    public Keys min() {
        return keyBox[0];
    }

    public Keys max() {
        return keyBox[n - 1];
    }

    public Keys select(int i) {
        // if (i < 0 || i >= n) throw new IllegalArgumentException("Underflow");
        return keyBox[i];
    }

    public int size() {
        return n;
    }

    public int size(Keys lo, Keys hi) {
        int i = rank(lo);
        if (contains(hi)) {
            int j = rank(hi);
            return j - i + 1;
        }
        else {
            int k = 0;
            for (int temp = rank(lo); temp < rank(hi); temp++) {
                k++;
            }
            return k;
        }
    }


    /* iterator() **************************************************************************************/
    public Iterable<Keys> keys(Keys lo, Keys hi) {
        Queue<Keys> k = new Queue<Keys>();
        for (int i = rank(lo); i < rank(hi); i++) k.enqueue(keyBox[i]);
        if (contains(hi)) k.enqueue(hi);
        return k;
    }

    public Iterable<Keys> keys() {
        Queue<Keys> k = new Queue<Keys>();
        for (int i = 0; i < n; i++) k.enqueue(keyBox[i]);
        return k;
    }

    /***************************************************************************
     *  Check internal invariants.
     ***************************************************************************/

    private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keyBox[i].compareTo(keyBox[i - 1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keyBox[i].compareTo(select(rank(keyBox[i]))) != 0) return false;
        return true;
    }


    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split("\\s+");
        int n = keys.length;

        Exercise_30<String, Integer> st = new Exercise_30<String, Integer>(n);
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);

        StdOut.println("size = " + st.size());
        StdOut.println("min  = " + st.min());
        StdOut.println("max  = " + st.max());
        StdOut.println();

        // print keys in order using keys()
        StdOut.println("Testing keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
    }
}
