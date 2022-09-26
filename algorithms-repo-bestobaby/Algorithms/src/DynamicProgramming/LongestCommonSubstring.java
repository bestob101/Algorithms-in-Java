package DynamicProgramming;

public class LongestCommonSubstring {

    /**
     * @param sOne String 1
     * @param sTwo Sttring 2
     * @return Longest Common Substring
     *
     * <p>
     *     Time Complexity: O(m*n) where m = length of String 1, n = length of String 2 <br>
     *     Reason: loops through String 1 of length m, n times where n is length of String 2
     * </p>
     * <p>
     *     Space Complexity: O(m*n) where m = length of String 1, n = length of String 2 <br>
     *     Reason: Creates 2d array of size m*n for storing count of substring lengths
     * </p>
     */
    public static int LCS(final String sOne, final String sTwo) {

        int M = sOne.length();
        int N = sTwo.length();
        int[][] arr = new int[M+1][N+1];
        int max = 0;

        for (int i = 1; i < M+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (sOne.charAt(i-1) == sTwo.charAt(j-1))
                    arr[i][j] = 1 + arr[i-1][j-1];

                if (arr[i][j] > max)
                    max = arr[i][j];
            }
        }
        return max;
    }

    public static void main(String[] args) {


        String s1 = "GATATACAGCCGTA";
        String s2 = "GTATGGTATACG";


        long st = System.nanoTime();
        int len = LCS(s1, s2);
        long fin = System.nanoTime();
        double dur = (double) (fin - st)/1000000;
        System.out.println("LCS length: " + len + "\ttime: " + dur);
    }
}
