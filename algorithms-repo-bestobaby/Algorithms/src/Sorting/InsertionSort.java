package Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InsertionSort {

    public static <T extends Comparable<T>> void insertionSort(final T[] array, final Comparator<T> comparator) {

        for (int i = 1; i < array.length; i++) {

            T curr = array[i];
            int j = i-1;
            while ( j >= 0 && array[j].compareTo(curr) > 0) {

                array[j+1] = array[j];
                j--;
            }
            array[j+1] = curr;
        }
    }

    public static <T extends Comparable<T>> void printList(T[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Integer[] arr = {7, 8, 5, 2, 4, 6, 3};
        insertionSort(arr, Comparator.naturalOrder());
        printList(arr);
    }
}
