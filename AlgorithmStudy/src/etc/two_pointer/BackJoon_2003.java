package etc.two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BackJoon_2003 {
    static int n;
    static int m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        int[] arr = new int[n];
        int cnt = 0;

        stk = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int start = 0;
        int end = 0;

        while (start < arr.length && end < arr.length) {
            int sum = 0;
            System.out.println("start = " + start);
            System.out.println("end = " + end);
            System.out.println("---");
            for (int i = start; i <= end; i++) {
                sum += arr[i];
            }
            if (sum == m) {
                cnt++;
                end++;
            } else if (sum > m) {
                start++;
            } else if (sum < m) {
                end++;
            }
        }
        System.out.println(cnt);
    }
}
