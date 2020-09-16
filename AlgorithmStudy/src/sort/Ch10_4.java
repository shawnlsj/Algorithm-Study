package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ch10_4 {
    static int v; // 강의의 개수
    static int[] time = new int[501]; // 강의별 소요 시간
    static int[] result = new int[501]; // 해당 강의까지의 누적 소요 시간
    static int[] indegree = new int[501];

    // 노드 리스트 <인접 노드 리스트<노드 번호>>
    static ArrayList<ArrayList<Integer>> nodeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());


        for (int i = 0; i <= v; i++) {
            nodeList.add(new ArrayList<Integer>());
        }

        for (int i = 1; i <= v; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(stk.nextToken());
            time[i] = x;

            while (true) {
                x = Integer.parseInt(stk.nextToken());
                if (x == -1) break;
                nodeList.get(x).add(i);
                indegree[i]++;
            }
        }
        topologicalSort();
    }

    static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                result[i] = time[i];
            }
        }

        while(!q.isEmpty()){
            Integer now = q.poll();

            for (int i = 0; i < nodeList.get(now).size(); i++) {
                indegree[nodeList.get(now).get(i)]--;

                //동시에 여러개의 강의를 들을 수 있기 때문에
                // 선수 강의로 10시간 강의와 4시간 강의를 동시에 들어야 한다면 4시간 강의는 무시해도 된다
                // 따라서 max()로 가장 오래걸리는 시간만을 가져온다
                result[nodeList.get(now).get(i)] =
                        Math.max(result[nodeList.get(now).get(i)], result[now] + time[nodeList.get(now).get(i)]);

                if (indegree[nodeList.get(now).get(i)] == 0) {
                    q.offer(nodeList.get(now).get(i));
                }

            }
        }

        for (int i = 1; i <= v; i++) {
            System.out.println(result[i]);
        }


    }
}
