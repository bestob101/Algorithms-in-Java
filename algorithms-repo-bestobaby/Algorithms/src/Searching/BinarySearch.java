package Searching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearch {

    public static <T extends Comparable<T>> int binarySearch(final T item, final T[] array, final Comparator<T> comparator) {

        int left = 0;
        int right = array.length-1;

        while (left <= right) {

            int mid = left + (right - left)/2;

            if (array[mid].compareTo(item) == 0)
                return mid;

            if (item.compareTo(array[mid]) > 0)
                left = mid + 1;

            else
                right = mid - 1;
        }
        return -1;
    }
    public static <T extends Comparable<T>> int binarySearchRecursive(final T item, final T[] array, final Comparator<T> comparator) {

        int l = 0;
        int r = array.length - 1;
        return binarySearchRecursiveHelper(item, array, l, r, comparator);
    }

    public static <T extends Comparable<T>> int binarySearchRecursiveHelper(T item, T[] array, int left, int right, Comparator comparator) {

        if (left <= right) {

            int mid =  left + (right - left) / 2;

            if (item.compareTo(array[mid]) == 0)
                return mid;

            if (item.compareTo(array[mid]) > 0)
                return binarySearchRecursiveHelper(item, array, mid+1, right, comparator);

            else
                return binarySearchRecursiveHelper(item, array, left, mid-1, comparator);
        }
        return -1;
    }

    public static void main(String[] args) {

        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        int index = binarySearchRecursive(3, arr, Comparator.naturalOrder());
        int index2 = binarySearch(0, arr, Comparator.naturalOrder());

        System.out.println(index);
        System.out.println(index2);
    }
}
