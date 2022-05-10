/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class EdgeM implements Comparable<EdgeM> {

    private int v;
    private int w;
    private double weight;

    public EdgeM(int x, int y, double c) {
        v = x;
        w = y;
        weight = c;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int a) {
        if (a == v) return w;
        else if (a == w) return v;
        else throw new IllegalArgumentException("Illegal Arguments");
    }

    public String toString() {
        return String.format("%d--%d %.5f", v, w, weight);
    }

    public int compareTo(EdgeM that) {
        return Double.compare(this.weight, that.weight);
    }

    public static void main(String[] args) {
    }
}
