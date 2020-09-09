package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ch8_4 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int width = Integer.parseInt(br.readLine());
        int[] dp = new int[width+1];
        //i가 2일때 dp[i-2] * 2의 값이 2가 나오기 위해서
        //길이가 0일 때는 1가지로 간주한다
        dp[0] = 1;
        
        //길이가 1일 때는 1가지이다
        dp[1] = 1;

        //미리 나머지를 구해서 저장해도 마지막에 나머지를 구한 것과 결과는 똑같다
        for(int i=2; i<=width; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2)%796796;
        }

        System.out.println(dp[width]);
    }
}
