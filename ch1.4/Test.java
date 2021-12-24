/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Test {
    public static void main(String[] args) {
        Integer a1 = 100;
        Integer a2 = 100;
        System.out.println(a1 == a2);   // true
        Integer b1 = -128;
        Integer b2 = -128;
        System.out.println(b1 == b2);
        Integer c1 = 127;
        Integer c2 = 127;
        System.out.println(c1 == c2);
        Integer d1 = 128;
        Integer d2 = 128;
        System.out.println(d1 == d2);
        String k = "good";
        for (int i = 0; i < 4; i++) {
            System.out.println(k.charAt(i));
        }
    }
}
