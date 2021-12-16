/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class VisualAccumulator {
    //variables
    private double sum;
    private int N;

    //constructors
    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials + 1);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(0.005);
    }

    //method
    // public void addDataValue(double val) {
    //     N += 1;
    //     sum += val;
    // }
    public void addDataValue(double val) {
        N += 1;
        sum += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);  /*画出一个点*/
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, sum / N);
    }

    public double mean() {
        return sum / N;
    }

    public String toString() {
        return "Sum of these " + String.format("%d", N) + " numbers = " + String.format("%.3f", sum)
                + ", mean = " + String
                .format("%.3f", mean());
    }

    //example
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        /*10 1 测试更清晰，第一个点的时候，平均值和原数据重合，最终显示为红色*/
        VisualAccumulator a = new VisualAccumulator(Integer.parseInt(args[0]),
                                                    Double.parseDouble(args[1]));
        for (int i = 0; i < T; i++) {
            a.addDataValue(StdRandom.random());
        }
        StdOut.println(a);
    }
}
