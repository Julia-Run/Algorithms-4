/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Date;

import java.util.Arrays;
import java.util.Comparator;

public class Exercise21_Transaction implements Comparable<Exercise21_Transaction> {
    /* variables*/
    private String who;
    private Date when;
    private double amount;

    /* constructor*/
    public Exercise21_Transaction(String name, Date time, double money) {
        if (Double.isNaN(money) || Double.isInfinite(money))
            throw new IllegalArgumentException("Amount out of Range!");
        who = name;
        when = time;
        amount = money;
    }

    public Exercise21_Transaction(String s) {
        String[] a = s.split("\\s+");
        amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount out of Range!");
        who = a[0];
        when = new Date(a[1]);


    }

    /* API*/
    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        // return "Name: " + who + "\n" + "Date: " + when + "\n" + "Amount: " + amount;
        // return "Name: " + who + ",  " + "Date: " + when + ",  " + "Amount: " + amount;
        return who + "  " + "  " + when + "  " + amount;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        Exercise21_Transaction newThat = (Exercise21_Transaction) that;
        return (this.who == newThat.who && this.when == newThat.when
                && this.amount == newThat.amount);
    }

    public int compareTo(Exercise21_Transaction that) {
        return Double.compare(this.amount, that.amount);  /* 1,-1,0*/
    }

    public int hashCode() {
        /* not know*/
        int hash = 1;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }

    public static class WhoOrder implements Comparator<Exercise21_Transaction> {
        public int compare(Exercise21_Transaction v, Exercise21_Transaction w) {
            return v.who.compareTo(w.who);
        }
    }

    public static class WhenOrder implements Comparator<Exercise21_Transaction> {
        public int compare(Exercise21_Transaction v, Exercise21_Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    public static class AmountOrder implements Comparator<Exercise21_Transaction> {
        public int compare(Exercise21_Transaction v, Exercise21_Transaction w) {
            return Double.compare(v.amount, w.amount);
        }
    }


    /* examples*/
    public static void main(String[] args) {
        Exercise21_Transaction[] a = new Exercise21_Transaction[3];
        a[0] = new Exercise21_Transaction("Tome  11/09/2021 12356.7");
        a[1] = new Exercise21_Transaction("Marry  11/08/2021 45356.7");
        a[2] = new Exercise21_Transaction("Alison  1/08/2021 14258");

        System.out.println("Origin:");
        for (int i = 0; i < 3; i++) System.out.println(a[i]);

        System.out.println("Sorted by Name:");
        Arrays.sort(a, new Exercise21_Transaction.WhoOrder());
        for (int i = 0; i < 3; i++) System.out.println(a[i]);

        System.out.println("Sorted by Time:");
        Arrays.sort(a, new Exercise21_Transaction.WhenOrder());
        for (int i = 0; i < 3; i++) System.out.println(a[i]);

        System.out.println("Sorted by Money:");
        Arrays.sort(a, new Exercise21_Transaction.AmountOrder());
        for (int i = 0; i < 3; i++) System.out.println(a[i]);

    }
}
