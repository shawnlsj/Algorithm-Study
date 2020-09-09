package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ch8_2_BottomUp {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());

        int[] dp = new int[x+1];
        int min = 0;
        for(int i=2; i<=x; i++){
            min = dp[i-1] + 1;

            if (i % 5 == 0) {
                min = Math.min(dp[i/5]+1,min);
            }

            if (i % 3 == 0) {
                min = Math.min(dp[i/3]+1,min);
            }
            if (i % 2 == 0) {
                min = Math.min(dp[i/2]+1,min);
            }
            dp[i] = min;

            System.out.println("i = " + i + "dp[i] = "+dp[i]);
        }


        System.out.println(dp[x]);
    }
}