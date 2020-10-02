package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex_1783 {
    static int n;
    static int m;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        int answer = solution();
        System.out.println(answer);
    }

    static int solution() {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            if (m % 2 == 1) {
                return Math.min(4, (m / 2) + 1);
            } else {
                return Math.min(4, m / 2);
            }
        }
        if (n >= 3) {
            if (m <= 6) {
                return Math.min(4, m);
            }
            if (m >= 7) {
                return m - 7 + 5;
            }
        }
        return 0;
    }
}
