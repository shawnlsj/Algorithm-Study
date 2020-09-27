package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_18353 {
    static int[] arr;
    static int n;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        arr = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        //dp[i] 는 arr[i]를 마지막 요소로 가지는 가장 긴 감소하는 부분 수열
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            //arr[i] 보다 큰 수 arr[j] 중에서도, dp[j]가 가장 큰 값을 구하고 + 1 을 한다
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    max = Math.max(dp[j], max);
                }
                dp[i] = max + 1;
            }
        }

        // 열외 후 남을 수 있는 최대 병사 수
        int remain = 0;
        for (int i = 0; i < n; i++) {
            remain = Math.max(remain, dp[i]);
        }

        System.out.println("remain = " + remain);
        System.out.println("n = " + n);
        // 전체 병사 - 남은 병사 수 = 열외 병사 수
        System.out.println(n - remain);
    }
}
