/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DoubleTest {
    // this is a experiment to find out ---  the running time vs N

    /* 将输入数组中 和为0的三个数字的组合 总数 寻找出来,return*/
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) count++;
                }
            }
        }
        return count;
    }

    /* 生成一个数组： 大小n， 包含正负的随机数组
     * 调用cont，stopwatch，
     * 返回计算cont所用的时间*/
    public static double timeTrial(int n) {
        // Stopwatch() 必须另写; 如果Stopwatch()是子class，当前函数是static，则无法调用Stopwatch，
        // 当前不是static，当前函数无法被static main引用
        int limit = 10000;
        int[] b = new int[n];
        for (int i = 0; i < n; i++) b[i] = StdRandom.uniform(-limit, limit);
        Stopwatch t = new Stopwatch();
        count(b);
        return t.escapedTime();
    }


    public static void main(String[] args) {
        /* 不能直接引用Non-static的方法，*/
        int n = Integer.parseInt(args[0]);
        for (int i = n; true; i = 2 * i) {
            double t = timeTrial(i);
            StdOut.println("N = " + i + ", time = " + t);
        }
    }
}
