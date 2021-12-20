/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BagQueueStack {
    public static void main(String[] args) {
        Bag<Double> bag = new Bag<Double>();
        while (!StdIn.isEmpty()) {
            bag.add(StdIn.readDouble());
        }
        double sum = 0.0;
        for (double d : bag) {
            sum += d;
        }
        int N = bag.size();
        StdOut.println("Bag --- mean = " + String.format("%.3f", sum / N));


        String[] a = In.readStrings("ch1_3_01.txt");

        Stack<String> stack = new Stack<String>();
        for (String i : a) {
            stack.push(i);
        }
        StdOut.print("Stack --- ");
        while (!stack.isEmpty()) {
            StdOut.print(stack.pop() + " ");
        }
        StdOut.println(" ");
        StdOut.print("Queue --- ");
        Queue<String> que = new Queue<String>();
        for (String j : a) {
            que.enqueue(j);
        }
        while (!que.isEmpty()) {
            StdOut.print(que.dequeue() + " ");
        }

    }
}
