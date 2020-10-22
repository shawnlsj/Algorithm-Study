package floyd_warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드 워셜로 풀이가 가능한 이유
// 1. n번 학생의 등수를 확정짓기 위해선 n번 학생은 모든 학생과 비교가 가능해야 한다
// 2. 비교가 가능하다는 것은 -> x번 노드에서 n번 노드로 가는 경로나 반대의 경로가 존재한다는 것
// 3. 경로가 존재하는 지 판단하는 방법 -> [플로이드 워셜]을 이용해 x->n or n->x 가 INF 인지 아닌지 구한다
public class Ch17_38 {
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        // 학생 수
        int n = Integer.parseInt(stk.nextToken());
        Node[] students = new Node[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            students[i] = new Node(i);
        }
        // 비교 회수
        int m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            students[a].nextNodes.add(students[b]);
            students[b].prevNodes.add(students[a]);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visited, false);
            int countNext = students[i].getNumNextNodes();
            Arrays.fill(visited, false);
            int countPrev = students[i].getNumPrevNodes();
            if (countNext + countPrev == n - 1) {
                answer++;
            }
        }
        System.out.println(answer);
    }
    static class Node{
        int num;
        ArrayList<Node> nextNodes = new ArrayList<>();
        ArrayList<Node> prevNodes = new ArrayList<>();

        Node(int num) {
            this.num = num;
        }

        int getNumNextNodes() {
            int cnt = 0;
            for (int i = 0; i < nextNodes.size(); i++) {
                if (visited[nextNodes.get(i).num]) continue;
                visited[nextNodes.get(i).num] = true;
                cnt += nextNodes.get(i).getNumNextNodes();
                cnt++;
            }
            return cnt;
        }
        int getNumPrevNodes() {
            int cnt = 0;
            for (int i = 0; i < prevNodes.size(); i++) {
                if (visited[prevNodes.get(i).num]) continue;
                visited[prevNodes.get(i).num] = true;
                cnt += prevNodes.get(i).getNumPrevNodes();
                cnt++;
            }
            return cnt;
        }
    }
}
