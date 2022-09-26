package Searching;

import java.io.*;
import java.util.Comparator;

import static Files.TextFiles.readNumbers;
import static Files.TextFiles.readString;
import static Searching.BinarySearch.binarySearch;
import static Sorting.QuickSort.quickSort2;
import static Searching.LinearSearch.*;

public class Searching_Analyses {

    public static <T extends Comparable<T>> double[] timeSearch(T item, T[] array, Comparator comparator) {

        quickSort2(array, comparator);

        double[] durations = new double[3];
        long start, finish;
        double duration;
        int index;

        start = System.nanoTime();
        index = linearSearch(item, array, comparator);
        finish = System.nanoTime();
        duration = (double) (finish - start)/1000000;
        printTiming("Linear Search (index=" + index, array.length, duration);
        durations[0] = duration;

        start = System.nanoTime();
        index = binarySearch(item, array, comparator);
        finish = System.nanoTime();
        duration = (double) (finish - start)/1000000;
        printTiming("Binary Search (index=" + index, array.length, duration);
        durations[1] = duration;

        start = System.nanoTime();
        index = linearSearch(item, array, comparator);
        finish = System.nanoTime();
        duration = (double) (finish - start)/1000000;
        printTiming("Binary Search Recursive (index=" + index, array.length, duration);
        durations[2] = duration;

        System.out.println();

        return durations;
    }

    public static void printTiming(String type, int len, double duration) {

        System.out.println(type + "\tlength: " + len + "\tTime: " + duration);
    }

    public static void main(String[] args) throws IOException {

        Integer[] num10 = readNumbers("integers10.txt");
        Integer[] numHundred = readNumbers("integers100.txt");
        Integer[] numThousand = readNumbers("integers1000.txt");
        Integer[] num10Thousand = readNumbers("integers10000.txt");
        Integer[] num100Thousand = readNumbers("integers100000.txt");

        Comparator comparator = Comparator.naturalOrder();

        double[] input10 = timeSearch(num10[num10.length-1], num10, comparator);
        double[] inputHundred = timeSearch(numHundred[numHundred.length-1], numHundred, comparator);
        double[] inputThousand = timeSearch(numThousand[numThousand.length-1], numThousand, comparator);
        double[] input10Thousand = timeSearch(num10Thousand[num10Thousand.length-1], num10Thousand, comparator);
        double[] input100Thousand = timeSearch(num100Thousand[num100Thousand.length-1], num100Thousand, comparator);

        writeToCVS(input10, 10);
        writeToCVS(inputHundred, 100);
        writeToCVS(inputThousand, 1000);
        writeToCVS(input10Thousand, 10000);
        writeToCVS(input100Thousand, 100000);
    }

    public static void writeToCVS(double[] array, int inputSize) throws IOException {

        String filename = "Search_Timing_Analyses.csv";
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(fw);

        if (new File(filename).length() == 0)
            bw.write("Input Size,Linear Search,Binary Search,Binary Search Recursive\n");

        bw.write(inputSize + ",");
        int i;
        for (i = 0; i < array.length-1; i++) {
            bw.write(Double.toString(array[i]) + ",");
        }
        bw.write(Double.toString(array[i]));
        bw.write('\n');
        bw.close();
    }

}
