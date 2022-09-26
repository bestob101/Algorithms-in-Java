package DynamicProgramming;

public class LongestCommonSubsequence {

    public static int lcs(final String sOne, final String sTwo) {

        int m = sOne.length();
        int n = sTwo.length();
        int arr[][] = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sOne.charAt(i-1) == sTwo.charAt(j-1))
                    arr[i][j] = arr[i - 1][j - 1] + 1;

                else
                    arr[i][j] = max(arr[i - 1][j], arr[i][j - 1]);
            }
        }
        return arr[m][n];
    }

    public static  int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static void main(String[] args) {

        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        String s3 = "Goose with the golden eggs";
        String s4 = "Once upon a time";

        long st = System.nanoTime();
        int len = lcs(s4, s3);
        long fin = System.nanoTime();
        double dur = (double) (fin - st)/1000000;
        System.out.println("LCS: " + len + "\ttime: " + dur);
    }
}
