package dynamic_programming;

// 2n 타일링
public class Programmers_12900 {
    public int solution(int n) {
        long[] dp = new long[n + 3];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] * 2 + dp[i - 3]) % 1_000_000_007;
        }
        return (int)dp[n];
    }
}
