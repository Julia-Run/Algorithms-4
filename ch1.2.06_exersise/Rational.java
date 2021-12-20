/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Rational {
    // variables
    private final long numerator;
    private final long denominator;

    // constructors
    public Rational(long n, long d) {
        if (d == 0) {
            throw new RuntimeException("Denominator is zero");
        }
        long t = gcd(n, d);
        if (d < 0) t = -t;
        numerator = n / t;
        denominator = d / t;
    }

    // methods
    public Rational plus(Rational b) {
        long pd = this.denominator * b.denominator;
        long pn = this.numerator * b.denominator + b.numerator * this.denominator;
        Rational p = new Rational(pn, pd);
        return p;
    }

    public Rational minus(Rational b) {
        long pd = this.denominator * b.denominator;
        long pn = this.numerator * b.denominator - b.numerator * this.denominator;
        Rational p = new Rational(pn, pd);
        return p;
    }

    public Rational times(Rational b) {
        long pd = this.denominator * b.denominator;
        long pn = this.numerator * b.numerator;
        return new Rational(pn, pd);
    }

    public Rational divides(Rational b) {
        long pd = this.denominator * b.numerator;
        long pn = this.numerator * b.denominator;
        return new Rational(pn, pd);
    }

    public boolean equals(Rational that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        // return this.compareTo(that) == 0;
        if (this.numerator != that.numerator) return false;
        if (this.denominator != that.denominator) return false;
        return true;
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    // gcd gcd gcd gcd
    // public static long gcd(long n, long d) {
    //     if (n < 0) n = -n;
    //     if (d < 0) d = -d;
    //     while (n != 0) {
    //         long t = d % n;
    //         d = n;
    //         n = t;  /*必须有吗间值*/
    //
    //     }
    //     return d;
    // }

    public static long gcd(long n, long d) {
        if (n < 0) n = -n;
        if (d < 0) d = -d;
        if (n == 0) return d;
        return gcd(d % n, n);  /* while (n!=0) {n,d = d%n, n} */
    }
    // examples

    public static void main(String[] args) {
        Rational a = new Rational(2, -30);
        Rational b = new Rational(-2, -40);
        StdOut.println(a);
        StdOut.println(b);  /* constructor*/
        StdOut.println(a == b); /* equals*/
        Rational c = a.plus(b);
        StdOut.println("a + b = c   --->   " + a + " + " + b + " = " + c); /* plus*/
        Rational d = a.minus(b);
        StdOut.println("a - b = d   --->   " + a + " - " + b + " = " + d); /* minus*/
        Rational e = a.times(b);
        StdOut.println("a * b = e   --->   " + a + " * " + b + " = " + e); /* times*/
        Rational f = a.divides(b);
        StdOut.println("a / b = f   --->   " + a + " / " + b + " = " + f); /* divides*/
    }
}
