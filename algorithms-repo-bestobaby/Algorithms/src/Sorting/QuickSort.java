package Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import static Sorting.InsertionSort.insertionSort;

public class QuickSort {

    /**
     *
     *
     *  <p>
     *     Time Complexity: <br>
     *              Best Case: O(N logN) <br>
     *              Average Case: O(N logN) <br>
     *              Worst Case: O(N^2) <br>
     * </p>
     * <p>
     *      Reason:  <br>
     *      Quick sort repeatedly partitions array of size N into two sub-arrays
     *              based on a pivot. On average the sub-arrays will be of size approximately N/2.
     *              Therefore, on average quicksort uses N logN comparisons. However,
     *              if the array is sorted then the time complexity is O(N^2). This is because,
     *              the divided sub-arrays will have a size ratio of N-1:1 during each partition.
     * </p>
     * <p>
     *      Space Complexity: <br>
     *              O(N) for all cases
     * </p>
     * <p>
     *      Reason:  <br>
     *      Quick sort partitions and array of size N into two sub-arrays
     *              repeatedly of size k and n where k + n = N.
     *              Hence total space of all the sub-arrays is N
     *  </p>
     *  <p>
     *       Improvements: <br>
     *              - Problem 1: Too much overhead for small sub-arrays <br>
     *              - Solution: Cutoff to insertion sort for arrays of size less than or equal to 10
     *  <br><br>
     *              - Problem 2: Bad pivot selection will cause increased time complexity.<br>
     *              - Solution: Select median of 3 random elements to be median.
     *<br><br>
     *              - Problem 3: Performs poorly on sorted arrays. <br>
     *              - Solution: Check if array is sorted before sorting.
     *<br><br>
     *              - Problem 4: The below implementation of quicksort is not in-place. <br>
     *              - Solution 4: Use high, low variables to store start and end of sub-arrays
     *                            within the array. This will better optimise space complexity.
     *
     *                            </p>
     */
    public static <T extends Comparable<T>> void quickSort(final T[] array, final Comparator<T> comparator) {

        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(array, i, j);
        }

        if (array.length > 1) {
            int index = partition(array, comparator);

            T[] left = (T[]) new Comparable[index+1];
            T[] right = (T[]) new Comparable[array.length - left.length];

            for (int i = 0; i < left.length; i++) {
                left[i] = array[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = array[left.length + i];
            }

            quickSort(left, comparator);
            quickSort(right, comparator);
            merge(array, left, right, comparator);
        }
    }
    public static <T extends Comparable<T>> void quickSort2(final T[] array, final Comparator<T> comparator) {

        //shuffles array for performance guarantee
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(array, i, j);
        }

        if (array.length > 10) {
            int index = partition(array, comparator);

            T[] left = (T[]) new Comparable[index+1];
            T[] right = (T[]) new Comparable[array.length - left.length];

            for (int i = 0; i < left.length; i++) {
                left[i] = array[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = array[left.length + i];
            }

            quickSort(left, comparator);
            quickSort(right, comparator);
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

    //partitions an array based on the pivot and returns the index of the pivot
    public static <T extends Comparable<T>> int partition(T[] array, Comparator comparator) {

        T pivot = array[0]; //first element is pivot

        //moves elements smaller than pivot to the right and elements greater than pivot to the left
        int i = 1;
        for (int j = i; j < array.length; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i-1, 0); //placed the pivot element in the correct index
        return i-1; //returns index of pivot
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

        Integer[] arr = {326, 2, 3 ,3, 324, 1, 2,5 , 3, 2, 234, 24,23, 42,4, 2234, 2342, 234, 242,3, 4};
        quickSort(arr, Comparator.naturalOrder());
        printList(arr);
    }
}
