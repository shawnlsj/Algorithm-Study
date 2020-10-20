package etc.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BackJoon_14425 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        Node root = new Node();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            Node node = root;
            for (int j = 0; j < s.length(); j++) {
                if (node.nodes[s.charAt(j) - 'a'] == null) {
                    node.nodes[s.charAt(j) - 'a'] = new Node();
                }
                node = node.nodes[s.charAt(j) - 'a'];
            }
            node.registered = true;
        }

        int cnt = 0;

        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            Node node = root;
            for (int j = 0; j < s.length(); j++) {
                if (node.nodes[s.charAt(j) - 'a'] == null) {
                    break;
                }
                node = node.nodes[s.charAt(j) - 'a'];
                if (j == s.length() - 1 && node.registered) {
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }

    static class Node {
        Node[] nodes = new Node[26];
        boolean registered = false;
    }
}
