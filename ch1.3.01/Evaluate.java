/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> nums = new Stack<Double>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double num = nums.pop();
                if (op.equals("+")) num = nums.pop() + num;
                else if (op.equals("-")) num = nums.pop() - num;
                else if (op.equals("*")) num = nums.pop() * num;
                else if (op.equals("/")) num = nums.pop() / num;
                else if (op.equals("sqrt")) num = Math.sqrt(num);
                nums.push(num);
            }
            else {
                nums.push(Double.parseDouble(s));
            }
        }
        double out = nums.pop();
        StdOut.println(out);

    }
}
