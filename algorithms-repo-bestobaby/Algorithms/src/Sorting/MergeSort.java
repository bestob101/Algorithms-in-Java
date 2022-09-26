package Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static Sorting.InsertionSort.insertionSort;

public class MergeSort {

    /**
     *
     *
     *
     * <p>
     *     Time Complexity: <br>
     *              Best Case: O(N logN) <br>
     *              Average Case: O(N logN) <br>
     *              Worst Case: O(N logN)
     * </p>
     *
     * <p>
     *      Reason: <br>
     *             Algorithm uses a divide and conquer approach. Divides array into two halves
     *             and conquer the halve arrays recursively. Then combine two arrays. Since the
     *             arrays are repeatedly halved, merge sort uses less than or equal to  N logN comparisons to sort array
     *             of length N. Hence, time complexity of N logN
     * </p>
     *
     * <p>
     *      Space Complexity: <br>
     *          O(N) for all cases
     * </p>
     * <p>
     *      Reason: <br>
     *      Array is divided into 2 halves. Space of these sub array is N/2.
     *              Hence, the total space of all sub arrays is N.
     * </p>
     * <p>
     *      Improvements: <br>
     *          Merge sort has too much overhead for small sub-arrays. This can be
     *          sovled by using insertion sort for arrays of length less than or equal to 10
     * </p>
     *
     */
    public static <T extends Comparable<T>> void mergeSort(final T[] array, final Comparator<T> comparator) {

        if (array.length > 1) {

            T[] left = (T[]) new Comparable[array.length / 2];
            T[] right = (T[]) new Comparable[array.length - left.length];

            for (int i = 0; i < left.length; i++) {
                left[i] = array[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = array[left.length + i];
            }

            mergeSort(left, comparator);
            mergeSort(right, comparator);
            merge(array, left, right, comparator);
        }
    }

    public static <T extends Comparable<T>> void mergeSort2(final T[] array, final Comparator<T> comparator) {

        if (array.length > 10) {

            T[] left = (T[]) new Comparable[array.length / 2];
            T[] right = (T[]) new Comparable[array.length - left.length];

            for (int i = 0; i < left.length; i++) {
                left[i] = array[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = array[left.length + i];
            }

            mergeSort(left, comparator);
            mergeSort(right, comparator);
            merge(array, left, right, comparator);
        }
        else {
            insertionSort(array, comparator);
        }
    }


    //merges two arrays left and right into the arr array.
    //merged array is sorted provided left and right is sorted
    public static <T extends Comparable<T>> void merge(T[] arr, T[] left, T[] right, final Comparator<T> comparator) {

        int i = 0; //counts elements in left
        int j = 0; //counts elements in right
        int k = 0; //counts elements in arr
        while (i < left.length && j < right.length) {

            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length)
            arr[k++] = left[i++];

        while (j < right.length)
            arr[k++] = right[j++];
    }

    public static <T extends Comparable<T>> void printList(T[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = {7, 8, 5, 2, 12, 13, 4, 6, 3, 3, 4, 7, 61};
        mergeSort2(arr, Comparator.naturalOrder());
        printList(arr);
    }

}
