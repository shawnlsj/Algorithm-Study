package etc.lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BackJoon_11437 {
    static Node[] nodes;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        visited = new boolean[n + 1];
        Node root = new Node(1);
        nodes[1] = root;

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            if (nodes[a] == null) {
                nodes[a] = new Node(a);
            }
            if (nodes[b] == null) {
                nodes[b] = new Node(b);
            }
            nodes[a].add(nodes[b]);
            nodes[b].add(nodes[a]);
        }

        root.level = 1;
        visited[root.num] = true;
        dfs(root, root.level + 1);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            Node node1 = nodes[Integer.parseInt(stk.nextToken())];
            Node node2 = nodes[Integer.parseInt(stk.nextToken())];
            System.out.println(lca(node1, node2));
        }
    }
    static int lca(Node node1, Node node2) {
        // 두 노드의 레벨 맞추기
        while (node1.level != node2.level) {
            if (node1.level > node2.level) {
                node1 = node1.parent;
            } else {
                node2 = node2.parent;
            }
        }

        // 두 노드가 같아질때까지 거슬러 올라가기
        while (node1 != node2) {
            node1 = node1.parent;
            node2 = node2.parent;
        }
        return node1.num;
    }

    static void dfs(Node parent, int level) {
        for (int i = 0; i < parent.nodes.size(); i++) {
            if (visited[parent.nodes.get(i).num]) continue;
            parent.nodes.get(i).level = level;
            parent.nodes.get(i).parent = parent;
            visited[parent.nodes.get(i).num] = true;
            dfs(parent.nodes.get(i), level + 1);
        }
    }

    static class Node{
        int num;
        int level;
        Node parent;
        ArrayList<Node> nodes = new ArrayList<>();

        Node(int num) {
            this.num = num;
        }
        void add(Node node) {
            nodes.add(node);
        }
    }
}
