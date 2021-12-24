/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Stopwatch {
    private long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double escapedTime() {
        long curr = System.currentTimeMillis();
        double deltaT = (curr - start) / 1000.0;
        return deltaT;
    }
}
