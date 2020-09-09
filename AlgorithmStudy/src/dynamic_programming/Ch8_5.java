package dynamic_programming;

import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ch8_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] dp = new int[m+1];
        int[] coinArr = new int[n];

        for (int i = 0; i < n; i++) {
            coinArr[i] = Integer.parseInt(br.readLine());
        }
        //dp테이블 값을 전부 INF로 채운다
        Arrays.fill(dp, 10001);

        //0번 인덱스에는 0을 대입
        //화폐를 하나도 사용하지 않아도 0을 만들 수 있기 때문
        dp[0] = 0;


        //점화식에 따라 각 화폐별로 dp테이블을 채워나간다
        for (int i = 0; i < coinArr.length; i++) {
            for (int j = coinArr[i] ; j <= m; j++) {
                dp[j] = Math.min(dp[j],dp[j-coinArr[i]]+1);
            }
        }

        if (dp[m] != 10001) {
            System.out.println(dp[m]);
        } else {
            System.out.println(-1);
        }
    }
}
