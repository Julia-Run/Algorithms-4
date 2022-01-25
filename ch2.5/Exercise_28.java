/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;

public class Exercise_28 {

    public static String[] sortFileName(String s) {
        File directory = new File(s);
        if (!directory.exists()) throw new IllegalArgumentException(s + " NOT exists!");
        if (!directory.isDirectory())
            throw new IllegalArgumentException(s + " is NOT a directory!");
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) return null;
        String[] fileNames = new String[files.length];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = files[i].getName();
        }
        /* quick sort a String[] filenames*/
        /* 将k位置的值每次放在对应位置。 然后分成两份。二分法。分别将左右两边部分数列k位置的元素放在对应位置*/
        StdRandom.shuffle(fileNames);
        // Arrays.sort(fileNames);
        quickSort(fileNames, 0, fileNames.length - 1);
        return fileNames;
    }

    public static void quickSort(String[] s, int lo, int hi) {
        if (lo >= hi) return;

        /* partion*/
        String v = s[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (s[++i].compareTo(v) < 0) if (i >= hi) break;
            while (s[--j].compareTo(v) > 0) ;
            if (i >= j) break;
            exch(s, i, j);
        }
        exch(s, lo, j);
        quickSort(s, lo, j - 1);
        quickSort(s, j + 1, hi);
    }

    public static void exch(String[] x, int i, int j) {
        String temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }


    public static void main(String[] args) {
        String directoryPath = args[0];
        String[] sortedFiles = new Exercise_28().sortFileName(directoryPath);

        if (sortedFiles == null) {
            return;
        }

        for (String fileName : sortedFiles) {
            StdOut.println(fileName);
        }
    }
}
