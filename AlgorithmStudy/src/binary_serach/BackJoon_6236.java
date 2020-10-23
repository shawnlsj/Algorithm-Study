package binary_serach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 용돈 관리
public class BackJoon_6236 {
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        int[] costs = new int[n];
        for (int i = 0; i < costs.length; i++) {
            costs[i] = Integer.parseInt(br.readLine());
        }

        int start = 1;
        int end = 100000;
        while (true) {
            if (start == end) {
                System.out.println(start);
                break;
            }
            int middle = (start + end) / 2;
            int cnt = 0;
            int wallet = 0;
            boolean possible = true;
            for (int i = 0; i < costs.length; i++) {
                if (wallet >= costs[i]) {
                    wallet -= costs[i];
                } else {
                    cnt++;
                    System.out.println("cnt = " + cnt);
                    System.out.println("costs[i] = " + costs[i]);
                    System.out.println();
                    wallet = middle - costs[i];
                    if (wallet < 0) {
                        possible = false;
                        break;
                    }
                }
            }
            System.out.println("start = " + start);
            System.out.println("middle = " + middle);
            System.out.println("end = " + end);
            System.out.println("possible = " + possible);
            System.out.println();
            if (cnt > m) {
                possible = false;
            }
            if (!possible) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
    }
}
