/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Date {
    /*variables*/
    private final int year;
    private final int month;
    private final int day;

    /*constructor*/
    public Date(int m, int d, int y) {
        year = y;
        month = m;
        day = d;
    }

    /*methord*/
    public int day() {
        return day;
    }

    public int month() {
        return month;
    }

    public int year() {
        return year;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year;
    }

    public static void main(String[] args) {
        Date d = new Date(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                          Integer.parseInt(args[0]));
        System.out.println(d);

    }
}
