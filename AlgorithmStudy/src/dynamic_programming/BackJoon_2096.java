package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BackJoon_2096 {
    static final int INF = (int)1e9;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] tmp = new int[3];
        int[] minDp = new int[3];
        int[] maxDp = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                tmp[j] = Integer.parseInt(stk.nextToken());
            }

            int[] maxCopy = Arrays.copyOf(maxDp, maxDp.length);
            int[] minCopy = Arrays.copyOf(minDp, minDp.length);

            for (int j = 0; j < 3; j++) {
                int max = 0;
                int min = INF;
                for (int k = -1; k <= 1; k++) {
                    if (j + k < 0 || j + k >= 3 ) continue;
                    max = Math.max(maxCopy[j + k], max);
                    min = Math.min(minCopy[j + k], min);
                }
                maxDp[j] = tmp[j] + max;
                minDp[j] = tmp[j] + min;
            }
        }
        Arrays.sort(maxDp);
        Arrays.sort(minDp);
        System.out.println(Arrays.toString(maxDp));
        System.out.println(Arrays.toString(minDp));
        System.out.println(maxDp[2] + " " + minDp[0]);
    }
}
