package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch8_3_TopDown {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        int[] cache = new int[size];

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < size; i++) {
          arr[i] = Integer.parseInt(stk.nextToken());
        }
        
        // 창고가 하나일때는 비교할 것이 없기 때문에 바로 대입
        cache[0] = arr[0];
        for (int i = 1; i < size; i++) {
            cache[i] = -1;
        }

        System.out.println(dp(size-1,arr,cache));
    }

    public static int dp(int i, int[] arr, int[] cache) {

        if (i == 0) {
             return cache[0];
        }
        if (i == 1) {
            if (cache[1] == -1) {
                cache[1] = Math.max(arr[0],arr[1]);
            }
            return cache[1];
        }

        if (cache[i-2] == -1) {
            cache[i-2] = dp(i-2,arr,cache);
        }

        int a = cache[i-2];

        if (cache[i - 1] == -1) {
            cache[i-1] = dp(i-1,arr,cache);
        }

        int b = cache[i-1];

        return Math.max( a + arr[i] , b);
    }
}
