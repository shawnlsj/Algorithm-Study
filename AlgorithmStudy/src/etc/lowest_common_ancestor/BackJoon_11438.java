package etc.lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BackJoon_11438 {
    static Node[] nodes;
    static boolean[] visited;
    static Node[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        visited = new boolean[n + 1];
        dp = new Node[n + 1][30];

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
        setParent();

        int m = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < m; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            Node node1 = nodes[Integer.parseInt(stk.nextToken())];
            Node node2 = nodes[Integer.parseInt(stk.nextToken())];
            bw.write(lca(node1, node2) + "");
            bw.newLine();
            bw.flush();
        }
    }

    static int lca(Node node1, Node node2) {
        Node a = node1;
        Node b = node2;
        if (node1.level > node2.level) {
            a = node2;
            b = node1;
        }

        int gap = b.level - a.level;

        while (b.level != a.level) {
            b = dp[b.num][(int) (Math.log10(gap) / Math.log10(2))];
            gap = b.level - a.level;
        }

        while(a != b){
            for (int i = dp[1].length - 1; ; i--) {
                if (dp[b.num][i] == null) continue;
                if (dp[b.num][i] != dp[a.num][i] || i == 0) {
                    a = dp[a.num][i];
                    b = dp[b.num][i];
                    break;
                }
            }
        }
        return b.num;
    }

    static void dfs(Node parent, int level) {
        for (int i = 0; i < parent.nodes.size(); i++) {
            if (visited[parent.nodes.get(i).num]) continue;

            parent.nodes.get(i).level = level;
            dp[parent.nodes.get(i).num][0] = parent;
            visited[parent.nodes.get(i).num] = true;

            dfs(parent.nodes.get(i), level + 1);
        }
    }

    static void setParent() {
        for (int i = 1; i <  dp[1].length; i++) {
            for (int j = 2; j < nodes.length; j++) {
                if (dp[j][i - 1] == null || dp[dp[j][i - 1].num][i - 1] == null) {
                    continue;
                }
                dp[j][i] = dp[dp[j][i - 1].num][i - 1];
            }
        }
    }

    static class Node{
        int num;
        int level;
        ArrayList<Node> nodes = new ArrayList<>();

        Node(int num) {
            this.num = num;
        }
        void add(Node node) {
            nodes.add(node);
        }
    }
}
