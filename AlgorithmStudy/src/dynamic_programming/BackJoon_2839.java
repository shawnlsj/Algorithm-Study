package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BackJoon_2839 {
    static int n;
    static int[] dp;
    static final int INF = (int)1e9;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[5000 + 1];
        dp[1] = INF;
        dp[2] = INF;
        dp[3] = 1;
        dp[4] = INF;
        dp[5] = 1;

        for (int i = 6; i <= n; i++) {
            dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;
        }
        if (dp[n] >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(dp[n]);
        }

    }
}

