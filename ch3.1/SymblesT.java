/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;

public class SymblesT<Keys extends Comparable<Keys>, Vals> implements Iterable<Keys> {

    /* variables**************************************************************************/
    private Node first;

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

    /* iterator() **************************************************************************************/
    public Iterator<Keys> iterator() {
        return new OneIterator();
    }

    private class OneIterator implements Iterator<Keys> {
        private Node curr = first;

        public boolean hasNext() {
            return curr.next != null;
        }

        public Keys next() {
            Keys temp = curr.key;
            curr = curr.next;
            return temp;
        }
    }

    public static void main(String[] args) {
    }
}
