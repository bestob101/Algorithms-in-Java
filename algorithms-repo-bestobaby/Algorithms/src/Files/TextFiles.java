package Files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TextFiles {

    public static void randomNumbers(String filename, int n, int range) throws IOException {

        PrintWriter writer = new PrintWriter(new File(filename));
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int num = rand.nextInt(range) + 1;
            writer.print(num);
            writer.println();
        }
        writer.close();
    }

    public static Integer[] readNumbers(String filename) throws FileNotFoundException {

        ArrayList<Integer> list = new ArrayList<>();
        FileInputStream file = new FileInputStream(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            int num = Integer.valueOf(scanner.next());
            list.add(num);
        }
        Integer[] arr = new Integer[list.size()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = list.get(i);

        return arr;
    }

    public static String[] readString(String filename) throws IOException {

        ArrayList<String> list = new ArrayList<>();
        FileInputStream file = new FileInputStream(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String s = scanner.next();
            list.add(s);
        }
        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = list.get(i);

        return arr;
    }

    public static void main(String[] args) throws IOException {

        randomNumbers("integers10.txt", 10, 50);
        randomNumbers("integers100.txt", 100, 1000);
        randomNumbers("integers1000.txt", 1000, 5000);
        randomNumbers("integers10000.txt", 10000, 100000);
        randomNumbers("integers100000.txt", 100000, 500000);
        randomNumbers("integersMillion.txt", 1000000, 1000000);

    }
}
