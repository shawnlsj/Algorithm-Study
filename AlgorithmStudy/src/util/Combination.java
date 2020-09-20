package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Combination {

    static int n; // nCr 에서 n
    static int r; // nCr 에서 r
    static int cnt = 0; // 카운트할 변수
    static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(stk.nextToken()); // 1부터 n까지 자연수 중에서
        r = Integer.parseInt(stk.nextToken()); // m개를 뽑는다

        for (int i = 1; i <= n; i++) {
            dfs(i);
        }

    }
    static void dfs(int x){

        if(x + (r - (cnt+1))> n) return;

        list.add(x);
        cnt++;

        if (cnt == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(list.get(i)+" ");
            }
            System.out.println();
            list.removeLast();
            cnt--;
            return;
        }

        for (int i = x + 1; i <= n; i++) {
            dfs(i);
        }

        cnt--;
        list.removeLast();
    }
}

