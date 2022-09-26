package Sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static Files.TextFiles.readNumbers;
import static Sorting.InsertionSort.insertionSort;
import static Sorting.MergeSort.mergeSort;
import static Sorting.MergeSort.mergeSort2;
import static Sorting.QuickSort.quickSort;
import static Sorting.QuickSort.quickSort2;
import static Sorting.SelectionSort.selectionSort;

public class SortingAnalyses {

    public static <T extends Comparable<T>> double[] timeSort(T[] array, Comparator comparator) {

        String[] sortTypes = {"Insertion Sort", "Selection Sort", "Merge Sort", "Improved Merge Sort", "Quick Sort", "Improved Quick Sort"};
        double[] durations = new double[6];

        long start, finish, duration;

        start = System.nanoTime();
        insertionSort(array, comparator);
        finish = System.nanoTime();
        duration = finish - start;
        printTiming("Insertion Sort", array.length, duration);
        durations[0] = (double) duration/1000000;

        start = System.nanoTime();
        selectionSort(array, comparator);
        finish = System.nanoTime();
        duration = finish - start;
        printTiming("Selection Sort", array.length, duration);
        durations[1] = (double) duration/1000000;

        start = System.nanoTime();
        mergeSort(array, comparator);
        finish = System.nanoTime();
        duration = finish - start;
        printTiming("Merge Sort", array.length, duration);
        durations[2] = (double) duration/1000000;

        start = System.nanoTime();
        mergeSort2(array, comparator);
        finish = System.nanoTime();
        duration = finish - start;
        printTiming("Improved Merge Sort", array.length, duration);
        durations[3] = (double) duration/1000000;

        start = System.nanoTime();
        quickSort(array, comparator);
        finish = System.nanoTime();
        duration = finish - start;
        printTiming("Quick Sort", array.length, duration);
        durations[4] = (double) duration/1000000;

        start = System.nanoTime();
        quickSort2(array, comparator);
        finish = System.nanoTime();
        duration = finish - start;
        printTiming("Improved Quick Sort", array.length, duration);
        durations[5] = (double) duration/1000000;

        System.out.println();
        System.out.println();

        return durations;
    }

    public static void printTiming(String sortingType, int len, long duration) {

        System.out.println(sortingType + "\tlength: " + len + "\tTime: " + (double)duration/1000000);
    }
    public static void main(String[] args) throws IOException {

        Integer[] num10 = readNumbers("integers10.txt");
        Integer[] numHundred = readNumbers("integers100.txt");
        Integer[] numThousand = readNumbers("integers1000.txt");
        Integer[] num10Thousand = readNumbers("integers10000.txt");
        Integer[] num100Thousand = readNumbers("integers100000.txt");
        Integer[] numMillion = readNumbers("integersMillion.txt");

        Comparator comparator = Comparator.naturalOrder();


        double[] input10 = timeSort(num10, comparator);
        double[] inputHundred = timeSort(numHundred, comparator);
        double[] inputThousand = timeSort(numThousand, comparator);
        double[] input10Thousand = timeSort(num10Thousand, comparator);
        double[] input100Thousand = timeSort(num100Thousand, comparator);

        writeToCVS(input10, 10);
        writeToCVS(inputHundred, 100);
        writeToCVS(inputThousand, 1000);
        writeToCVS(input10Thousand, 10000);
        writeToCVS(input100Thousand, 100000);
        //timeSort(numMillion, comparator);
    }

    public static void writeToCVS(double[] array, int inputSize) throws IOException {

        String filename = "Timing_Analyses.csv";
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(fw);

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
