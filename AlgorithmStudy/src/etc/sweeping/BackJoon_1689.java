package etc.sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BackJoon_1689 {
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Edge> edges = new ArrayList<>();

        int numEdge = Integer.parseInt(br.readLine());
        for (int i = 0; i < numEdge; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            Edge edge = new Edge(start, end);
            edges.add(edge);
        }
        edges.sort((o1, o2) -> o1.start - o2.start);

        PriorityQueue<Edge> q = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
        q.add(edges.get(0));
        answer = q.size();

        for (int i = 1; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            while (!q.isEmpty() && edge.start >= q.peek().end) {
                q.poll();
            }
            q.add(edge);
            answer = Math.max(q.size(), answer);
        }
        System.out.println(answer);
    }

    static class Edge{
        int start;
        int end;

        Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
