/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_25 {
    public static class CubeSum implements Comparable<CubeSum> {
        private int sum;
        private int a;
        private int b;

        public CubeSum(int i, int j) {
            a = i;
            b = j;
            sum = a * a * a + b * b * b;
        }

        public int compareTo(CubeSum that) {
            return Integer.compare(this.sum, that.sum);
        }

        public String toString() {
            return sum + " = " + a + "^3" + " + " + b + "^3";
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        MinPQmy<CubeSum> pq = new MinPQmy<CubeSum>(n + 1);
        for (int i = 0; i <= n; i++) pq.insert(new CubeSum(i, 0));
        while (!pq.isEmpty()) {
            CubeSum s = pq.delMin();
            System.out.println(s);
            if (s.b < n) pq.insert(new CubeSum(s.a, s.b + 1));
        }
    }

}
