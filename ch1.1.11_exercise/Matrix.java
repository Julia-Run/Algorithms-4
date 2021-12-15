/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Matrix {
    // 向量点乘
    public static double dot(double[] x, double[] y) {
        double re = 0.0;
        for (int i = 0; i < x.length; i++) {
            re += x[i] * y[i];
        }
        return re;
    }

    // 矩阵相乘
    public static double[][] mult(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        int p = b[0].length;
        double[][] re = new double[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    re[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return re;
    }

    // 矩阵转置
    public static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] re = new double[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                re[j][i] = a[i][j];
            }
        }
        return re;
    }

    //矩阵与向量的乘积---线性方程组
    public static double[] multLinear(double[][] a, double[] x) {
        int m = a.length;
        int n = x.length;
        double[] re = new double[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) re[i] += a[i][j] * x[j];
        }
        return re;
    }

    public static void main(String[] args) {
        // test dot ///////////////////////////////
        // double[] a = { 1, 2, 3 };
        // double[] b = { -1, -1, -1 };
        // double k = Matrix.dot(a, b);
        // StdOut.print(k);
        // test mult ///////////////////
        // double[][] a = { { 1, 2, 3 }, { 2, 4, 6 } };
        // double[][] b = { { 1, 1, }, { -1, -1 }, { 0, 0 } };
        // double[][] k = Matrix.mult(a, b);
        // System.out.print(k[1][0]);
        // test transpose ///////////////////////////////////
        // double[][] a = { { 1, 2, 3 }, { 2, 4, 6 } };
        // double[][] k = Matrix.transpose(a);
        // System.out.print(k[2][0]);
        // test multLinear ///////////////////////////////////
        double[][] a = { { 1, 2, 3 }, { 2, 4, 6 } };
        double[] b = { 1, 1, 1 };
        double[] k = Matrix.multLinear(a, b);
        System.out.print(k[1]);
    }
}
