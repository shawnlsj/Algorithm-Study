package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ch16_31 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numTestCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < numTestCase; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            int row = Integer.parseInt(stk.nextToken());
            int col = Integer.parseInt(stk.nextToken());
            int[][] board = new int[row + 1][col + 1];
            int[] dp = new int[row + 1];
            int[] tmp = new int[row + 1];

            stk = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= row; j++) {
                for (int k = 1; k <= col; k++) {
                    board[j][k] = Integer.parseInt(stk.nextToken());
                }
            }

            for (int j = 1; j <= col; j++) {
                for (int k = 1; k <= row; k++) {
                    int max = 0;
                    for (int m = -1; m <= 1; m++) {
                        if (k + m < 1 || k + m > row) {
                            continue;
                        }
                        max = Math.max(max, tmp[k + m]);
                    }
                    dp[k] = max + board[k][j];
                }
                for (int k = 1; k <= row; k++) {
                    tmp[k] = dp[k];
                }
            }
            Arrays.sort(dp);
            System.out.println(dp[dp.length - 1]);
        }
    }
}
