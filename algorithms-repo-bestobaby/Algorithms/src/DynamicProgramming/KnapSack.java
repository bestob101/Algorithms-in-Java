package DynamicProgramming;

public class KnapSack {

    /**
     * @param W Maximum capacity
     * @param w array of weights of each value
     * @param vals array of value
     * @return max subset of values where sum of their weights is less than or equal to max Capacity
     *
     * <p>
     *     Brute Force 0-1 Knapsack
     * </p>
     * <p>
     *     Time Complexity: O(2^n) <br>
     *     Reason: Overlapping sub-problems are computed again and again.
     * </p>
     * <p>
     *     Space Complexity: O(1) <br>
     *     Reason: No extra auxiliary space used
     * </p>
     */
    public static int BF_knapsack(final int W, final int[] w, final int[] vals) {

        int n = vals.length;
        return BF_knapsackHelper(W, w, vals, n);
    }

    /*
     *  Helper function which implements the actual algorithm of the
     *  brute force knapsack problem
     */
    private static int BF_knapsackHelper(int W, int[] w, int[] vals, int n) {

        //base case
        if (n == 0 || W == 0)
            return 0;

            // if weight is greater than max capacity, ignore and move to next item
        else if (w[n-1] > W)
            return BF_knapsackHelper(W, w, vals, n-1);

        else {
            int var1 = BF_knapsackHelper(W, w, vals, n-1);
            int var2 = vals[n-1] + BF_knapsackHelper(W - w[n-1], w, vals, n-1);
            return max(var1, var2);
        }
    }

    /**
     * @param W Maximum capacity
     * @param w array of weights of each value
     * @param vals array of value
     * @return max subset of values where sum of their weights is less than or equal to max Capacity
     *
     * <p>
     *     Dynamic Programming 0-1 Knapsack
     * </p>
     * <p>
     *     Time Complexity: O(n*W) where n = size of array of values, W = maximum Capacity <br>
     *     Reason: For every element we traverse through all weight capacities w where 0 &lt; w &le; W
     * </p>
     * <p>
     *     Space Complexity: O(n*W) where n = size of array of values, W = maximum Capacity <br>
     *     Reason: 2d array of size n*W is created for storing computed sub-problems
     * </p>
     */
    public static int DP_knapsack(final int W, final int[] w, final int[] vals) {

        int n = vals.length;
        int[][] arr = new int[n+1][W+1]; // auxilary table
        return DP_knapsackHelper(W, w, vals, n, arr);
    }

    /*
     *  Helper function which implements the actual algorithm of the
     *  dynamic programming 0-1 knapsack problem
     */
    private static int DP_knapsackHelper(int W, int[] w, int[] vals, int n, int[][] arr) {

        // if already computed, return that value
        if (arr[n][W] != 0)
            return arr[n][W];

        //base case
        if (n == 0 || W == 0)
            return 0;

            // if weight is greater than max capacity, ignore and move to next item
        else if (w[n-1] > W) {
            arr[n][W] = DP_knapsackHelper(W, w, vals, n - 1, arr);
            return arr[n][W];
        }

        else {
            int var1 = DP_knapsackHelper(W, w, vals, n-1, arr);
            int var2 = vals[n-1] + DP_knapsackHelper(W - w[n-1], w, vals, n-1, arr);
            arr[n][W] = max(var1, var2);
            return arr[n][W];
        }
    }

    public static int max(int a, int b) {
        if (a >= b) return a;
        else return b;
    }

    public static void main(String[] args) {

        int W = 10;

        int[] v1 = {20, 50, 10, 30};
        int[] w1 = {3, 4, 6, 5};

        int[] v2 = {20, 50, 10, 30, 45, 25, 35, 60, 40, 50};
        int[] w2 = {3, 4, 6, 5, 1, 7, 9, 7, 4, 8};

        int[] v3 = {20, 50, 10, 30, 45, 25, 35, 60, 40, 50, 30, 45, 25, 35, 20, 50, 10, 30, 95, 75};
        int[] w3 = {3, 4, 6, 5, 1, 7, 9, 7, 4, 8, 4, 6, 5, 1, 7, 9, 3, 4, 6, 5};
        long s, f;
        double dur;

        s = System.nanoTime();
        int res1 = BF_knapsack(W, w1, v1);
        f = System.nanoTime();
        dur = (double) (f - s) / 1000000;
        printTiming("BF", v1.length, dur);

        s = System.nanoTime();
        int res2 = DP_knapsack(W, w1, v1);
        f = System.nanoTime();
        dur = (double) (f - s) / 1000000;
        printTiming("DP", v1.length, dur);

        System.out.println(res1);
        System.out.println(res2);
/*
        System.out.println();

        s = System.nanoTime();
        res1 = BF_knapsack(W, w2, v2);
        f = System.nanoTime();
        dur = (double) (f - s) / 1000000;
        printTiming("BF", v2.length, dur);

        s = System.nanoTime();
        res2 = DP_knapsack(W, w2, v2);
        f = System.nanoTime();
        dur = (double) (f - s) / 1000000;
        printTiming("DP", v2.length, dur);

        s = System.nanoTime();
        res1 = BF_knapsack(W, w3, v3);
        f = System.nanoTime();
        dur = (double) (f - s) / 1000000;
        printTiming("BF", v3.length, dur);

        s = System.nanoTime();
        res2 = DP_knapsack(W, w3, v3);
        f = System.nanoTime();
        dur = (double) (f - s) / 1000000;
        printTiming("DP", v3.length, dur);
*/
    }

    public static void printTiming(String type, int len, double duration) {

        System.out.println(type + "\tlength: " + len + "\tTime: " + duration);
    }
}
