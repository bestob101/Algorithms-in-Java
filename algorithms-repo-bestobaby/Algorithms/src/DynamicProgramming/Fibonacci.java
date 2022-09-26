package DynamicProgramming;

public class Fibonacci {

    /**
     * @param n nth series of Fibonacci series
     * @return nth series of Fibbonaci series
     *
     * <p>
     *     Recursive Fibonacci
     * </p>
     * <p>
     *     Time Complexity: O(2^n) <br>
     *     Reason: Function calls itself twice each time and overlapping sub-problems are computed again and again.
     *     Number of function calls is approximately 2^n times
     *</p>
     * <p>
     *     Space Complexity: O(1) <br>
     *     Reason: No extra auxiliary space used
     * </p>
    public static int fib(final int n) {

        // base case
        if (n <= 1)
            return n;

        return fib(n-1) + fib(n-2);
    }

    /**
     * @param n = nth series of Fibonacci series
     * @return nth series of Fibonaci series
     *
     * <p>
     *     Dynamic Programming Fibonacci
     * </p>
     * <p>
     *     Time Complexity: O(n) <br>
     *     Reason: looping through the series once
     * </p>
     * <p>
     *     Space Complexity: O(1) <br>
     *     Reason: No extra auxiliary space used
     * </p>
     */
    public static int fibDP(final int n) {

        int a = 0, b = 1;
        int fibSum;

        if (n < 1)
            return 0;
        else {
            for (int i = 1; i < n; i++) {
                fibSum = a + b;
                a = b;
                b = fibSum;
            }
            return b;
        }
    }

    public static void main(String[] args) {

        System.out.println(fibDP(3));
        System.out.println(fibDP(10));
        System.out.println(fibDP(42));
    }
}
