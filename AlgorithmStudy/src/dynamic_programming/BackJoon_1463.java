package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BackJoon_1463 {
    static int n;
    static final int INF = (int) 1e9;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            int div2 = INF;
            int div3 = INF;
            if (i % 2 == 0) {
                div2 = dp[i / 2];
            }
            if (i % 3 == 0) {
                div3 = dp[i / 3];
            }
            dp[i] = Math.min(dp[i - 1], Math.min(div2, div3)) + 1;
        }
        System.out.println(dp[n]);
    }
}
