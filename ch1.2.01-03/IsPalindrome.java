/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class IsPalindrome {
    public static void main(String[] args) {
        StdOut.println(IsPalindrome.is(args[0]));
        String s = args[0];
        // . + $ * | 等转义符必须加\\
        //  | 用来连接多个转义符
        String[] a = s.split("\\.|\\+");
        StdOut.println(a[0]);
        StdOut.println(a[1]);
        StdOut.println(a[2]);
        int k = "a".compareTo(args[1]);
        StdOut.println(k);

    }

    public static boolean is(String s) {
        int N = s.length();
        for (int i = 0; i < N / 2; i++) {
            if (s.charAt(i) != s.charAt(N - i - 1))
                return false;
        }
        return true;
    }
}
