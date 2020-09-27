package etc.longest_incresing_subsequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

//O(N^2) 알고리즘
public class Ex_11053 {
    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        Arrays.fill(dp, 1);

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        //dp[0]은 항상 1이므로 dp[1] 부터 갱신해 나간다
        int answer = 1;
        for (int i = 1; i < n; i++) {
        int max = 0;
            for (int j = i; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
            answer = Math.max(dp[i], answer);
        }
        System.out.println(answer);
    }
}
