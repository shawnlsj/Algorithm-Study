package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Combination {

    static int n; // nCr 에서 n
    static int r; // nCr 에서 r
    static int cnt = 0; // 현재까지 뽑은 요소의 수를 카운트할 변수
    static LinkedList<Integer> combinations = new LinkedList<>(); // 뽑은 요소를 저장할 리스트

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(stk.nextToken()); // 1부터 n까지 자연수 중에서
        r = Integer.parseInt(stk.nextToken()); // m개를 뽑는다

        combination();


    }
    static void combination() {
        for (int i = 1; i <= n; i++) {
            dfs(i);
        }
    }
    static void dfs(int x){

        if(x + (r - (cnt+1))> n) return;

        combinations.add(x);
        cnt++;

        //요소를 r개만큼 뽑았으면 조합을 출력
        if (cnt == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(combinations.get(i)+" ");
            }
            System.out.println();
            combinations.removeLast();
            cnt--;
            return;
        }

        for (int i = x + 1; i <= n; i++) {
            dfs(i);
        }

        cnt--;
        combinations.removeLast();
    }
}

