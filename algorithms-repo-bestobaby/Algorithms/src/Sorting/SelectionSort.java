package Sorting;

import java.util.Comparator;
import java.util.List;

public class SelectionSort {

    /**
     *
     *
     *     <p>
     *     Time Complexity: <br>
     *              Best Case: O(N^2) <br>
     *              Average Case: O(N^2) <br>
     *              Worst Case: O(N^2) <br>
     *      </p>
     *
     *      <p>
     *     Reason: <br>
     *     Selection Sort uses 2 nested loops. The algorithms makes N^2 / 2 comparisons
     *              and N exchanges. As N gets infinitely bigger, constants are negligible.
     *              Hence, the time complexity of O(N^2).
     *      </p>
     *
     *      <p>
     *     Space Complexity: <br>
     *              O(1) for all cases
     *      </p>
     *      <p>
     *     Reason:  <br>
     *     Fixed number of additional variables used for indexing and swapping.
     *     </p>
     */
    public static <T extends Comparable<T>> void selectionSort(final T[] array, final Comparator<T> comparator) {
        // TODO

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }

    }

    //swaps index i and j of an array
    public static <T extends Comparable<T>> void swap(T[] array, int i, int j) {

        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T extends Comparable<T>> void printList(T[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Integer[] arr = {7, 8, 5, 2, 4, 6, 3};
        selectionSort(arr, Comparator.naturalOrder());
        printList(arr);
    }
}
