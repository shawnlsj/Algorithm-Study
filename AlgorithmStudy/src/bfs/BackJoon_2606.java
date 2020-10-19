package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 바이러스
public class BackJoon_2606 {
    static int n;
    static int pair;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pair = Integer.parseInt(br.readLine());
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(null);
        for (int i = 1; i <= n; i++) {
            nodes.add(new Node());
        }

        for (int i = 0; i < pair; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            nodes.get(a).list.add(nodes.get(b));
            nodes.get(b).list.add(nodes.get(a));
        }

        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        nodes.get(1).visited = true;
        q.add(nodes.get(1));
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < node.list.size(); i++) {
                if (node.list.get(i).visited) {
                    continue;
                }
                node.list.get(i).visited = true;
                cnt++;
                q.add(node.list.get(i));
            }
        }
        System.out.println(cnt);
    }

    static class Node{
        ArrayList<Node> list = new ArrayList<>();
        boolean visited = false;
    }
}
