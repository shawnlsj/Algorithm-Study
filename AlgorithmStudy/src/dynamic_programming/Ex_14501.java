package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_14501 {
    static int n;
    static int[] tTable;
    static int[] pTable;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tTable = new int[n + 1]; //시간 테이블
        pTable = new int[n + 1]; //보수 테이블
        dp = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            tTable[i] = Integer.parseInt(stk.nextToken());
            pTable[i] = Integer.parseInt(stk.nextToken());
        }

        int answer = 0;
        for (int i = n; i >= 1; i--) {
            //i번째 일에 일을 한다면 받을 수 있는 보수 
            //일을 못한다면 그대로 0원을 받는다
            int p = 0;
            int completeDay = i + tTable[i] - 1; // i일에 일을 한다면, 일을 마치는 날
            int completeDayP = 0; //dp[completeDay + 1] 와 같다

            // 일을 끝난 날이 n일 이하이면 일을 하고 보수를 받는다
            if (completeDay <= n) {
                p = pTable[i];
            }
            
            // 일을 끝난 다음 날이 n일 이하이면 
            // [일을 끝난 다음 날 부터] 벌 수 있는 최댓값 -> dp[일을 끝난 다음날]을 저장한다
            if (completeDay + 1 <= n) {
                completeDayP = dp[completeDay + 1];
            }
            
            // i일 기준으로 벌 수 있는 최댓값은
            // i일날 일하고 [일이 끝난 다음날 기준]으로 벌 수 있는 최댓값 
            // vs
            // i일날 일하지 않고 [그 다음날 기준]으로 벌 수 있는 최댓값
            // 중에서 큰 값이 최댓값이 된다
            dp[i] = Math.max(p + completeDayP, dp[i + 1]);

            System.out.println("Arrays.toString(pTable) = " + Arrays.toString(pTable));
            System.out.println("p = " + p);
            System.out.println("completeDay = " + completeDay);
            System.out.println("completeDayP = " + completeDayP);
            System.out.println("-----");

            answer = Math.max(answer, dp[i]);
        }


        System.out.println(Arrays.toString(dp));
        System.out.println(answer);
    }
}
