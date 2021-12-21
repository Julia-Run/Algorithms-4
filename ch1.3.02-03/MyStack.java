/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;

public class MyStack<Item> implements Iterable<Item> {
    // variables
    private Node first;
    private int N;

    //Node
    private class Node {
        Item item;
        Node next;
    }

    //
    public Iterator<Item> iterator() {

    }

    public static void main(String[] args) {
    }
}
