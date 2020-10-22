package etc.queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BackJoon_2075 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                q.add(Integer.parseInt(stk.nextToken()));
            }
        }

        for (int i = 0; i < n - 1; i++) {
            q.poll();
        }
        System.out.println(q.peek());
    }
}
