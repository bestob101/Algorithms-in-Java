package Searching;

import javax.sound.sampled.Line;
import java.util.Comparator;
import java.util.List;

public class LinearSearch {

    //searches the every item in the array until found
    //returns index of item in array or -1 if not found
    public static <T extends Comparable<T>> int linearSearch(final T item, final T[] array, final Comparator<T> comparator) {

        for (int i = 0; i < array.length; i++) {
            if (item.compareTo(array[i]) == 0)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {

        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Comparator comparator = Comparator.naturalOrder();

        int index = linearSearch(8, arr, comparator);
        System.out.println(index);
    }
}
