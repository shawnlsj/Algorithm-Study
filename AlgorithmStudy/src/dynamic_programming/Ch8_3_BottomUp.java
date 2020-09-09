package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch8_3_BottomUp {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        int[] dp = new int[size];

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        // 창고가 1개 일때는 비교할 값이 없으므로 무조건 대입
        dp[0] = arr[0];

        // 창고가 2개일 때는 점화식이 아닌 직접 if문으로 비교를 하여 최적해를 구한다
        // 점화식으로 구하려다간 dp[i-2]에서 i-2 가 음수가 나오기 때문이다
        if (arr[1] > arr[0]) {
            dp[1] = arr[1];
        } else {
            dp[1] = arr[0];
        }

        // 창고가 3개일 때부터는 반복문으로 최적해를 채워나갈 수 있다.
        // i가 1일 때는 dp[i-2]의 인덱스가 음수가 나와서 문제였지만
        // i가 2일 때 부터는 dp[i-2] 와 dp[i-1] 모두 양수이기 때문이다
        for (int i = 2 ;i<size;i++){
            dp[i] = Math.max(dp[i-2]+arr[i], dp[i-1]);
        }

        System.out.println(dp[size-1]);
    }
}
