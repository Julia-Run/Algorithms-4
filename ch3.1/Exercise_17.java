/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

public class Exercise_17<Keys extends Comparable<Keys>, Vals> {

    /* variables**************************************************************************/
    private Keys[] keyBox;
    private Vals[] valBox;
    private int n;

    /* class node********************************************************************************************/


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
    

    /* iterator() **************************************************************************************/
    public Iterable<Keys> keysRange(Keys lo, Keys hi) {
        Queue<Keys> k = new Queue<Keys>();
        for (int i = rank(lo); i < rank(hi); i++) k.enqueue(keyBox[i]);
        if (contains(hi)) k.enqueue(hi);
        return k;
    }

    public static void main(String[] args) {
    }
}
