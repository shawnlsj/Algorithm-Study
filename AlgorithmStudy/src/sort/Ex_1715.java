package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 표준 라이브러리 우선순위 큐 버전
public class Ex_1715 {
    static int n;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 카드 뭉치 수

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            q.add(x);
        }

        for (int i = 0; i < n - 1; i++) {
            int merge = 0;
            merge += q.poll();
            merge += q.poll();
            q.add(merge);
            answer += merge;
        }
        System.out.println(answer);
    }
}




