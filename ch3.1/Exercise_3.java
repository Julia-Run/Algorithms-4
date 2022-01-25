/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise_3<Keys extends Comparable<Keys>, Vals> {

    /* variables**************************************************************************/
    private Node first;
    private int n;

    /* class node********************************************************************************************/
    private class Node {
        private Keys key;
        private Vals val;
        private Node next;


        public Node(Keys key, Vals val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /* methods******************************************************************************************************/
    public void put(Keys key, Vals val) {
        for (Node i = first; i != null; i = i.next) {
            if (key.compareTo(i.key) != 0) continue;
            else {
                i.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }


    public Vals get(Keys key) {
        for (Node i = first; i != null; i = i.next) {
            if (key.compareTo(i.key) != 0) continue;
            else return i.val;
        }
        return null;
    }

    public boolean contains(Keys key) {
        return get(key) != null;
    }

    public int size() {
        return n;
    }

    public void delet(Keys k) {
        if (k == null) return;
        if (first.key.compareTo(k) == 0) first = first.next;
        for (Node x = first; x.next != null; x = x.next) {
            if (k.compareTo(x.next.key) == 0) {
                x.next = x.next.next;
                n--;
                break;
            }
        }
    }

    public Iterable<Keys> keys() {
        Queue<Keys> queue = new Queue<Keys>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }


    // /* iterator() **************************************************************************************/
    // public Iterator<Keys> iterator() {
    //     return new OneIterator();
    // }
    //
    // private class OneIterator implements Iterator<Keys> {
    //     private Node curr = first;
    //
    //     public boolean hasNext() {
    //         return curr.next != null;
    //     }
    //
    //     public Keys next() {
    //         Keys temp = curr.key;
    //         curr = curr.next;
    //         return temp;
    //     }
    //
    //
    // }

    public static void main(String[] args) {
        Exercise_3<String, Integer> st = new Exercise_3<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        st.delet("E");
        st.delet("S");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}
