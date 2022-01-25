/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Exercise_16 {

    /* 已重新定义的字母顺序 RWQOJMVAHBSGZXNTCIEKUPDYFL 代替原有26字母排序
     * 输入一列候选人姓名，全部大写。--- 依据自定义的字母顺序给候选人排序
     * compareTo
     * 两个候选人的排序：0-hi，依次对比每个字母在 自定义序列中的index 差别出现时（1 or -1）即可判定前后。
     * 如果一直==0，最后判定整体长度。
     * Sort
     * 所有候选人sort*/

    /* java Exercise_16 < e16.txt*/

    /* class candidate **************************************************************************************/
    private static class Candidate implements Comparable<Candidate> {

        private String name;
        private int len;
        private String order;


        public Candidate(String name) {
            this.name = name;
            this.len = name.length();
            order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
        }

        public int compareTo(Candidate that) {

            int minlen = Math.min(this.len, that.len);
            for (int i = 0; i < minlen; i++) {
                char me = this.name.charAt(i);
                char you = that.name.charAt(i);
                if (order.indexOf(me) < order.indexOf(you)) return -1;
                else if (order.indexOf(me) > order.indexOf(you)) return 1;
            }
            return this.len - that.len;
        }
    }


    /* main **************************************************************************************/
    public static void main(String[] args) {
        String[] s = StdIn.readAllLines();
        int n = s.length;

        Candidate[] c = new Candidate[n];
        int cIndex = 0;
        for (int i = 0; i < n; i++) c[cIndex++] = new Candidate(s[i].toUpperCase());

        int min;
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i; j < n; j++) {
                if (c[j].compareTo(c[min]) < 0) min = j;
            }
            Candidate less = c[min];
            c[min] = c[i];
            c[i] = less;
        }
        System.out.println(n);
        for (int i = 0; i < n; i++) System.out.println(c[i].name);
    }
}

