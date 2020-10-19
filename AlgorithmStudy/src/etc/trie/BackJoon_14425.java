package etc.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BackJoon_14425 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        Node root = new Node();

        // trie 구조 만들기
        for (int i = 0; i < n; i++) {
            Node node = root;
            String string = br.readLine();
            for (int j = 0; j < string.length(); j++) {
                String s = Character.toString(string.charAt(j));
                if (node.map.get(s) == null) {
                    node.map.put(s, new Node());
                }
                node = node.map.get(s);
            }
        }
        int cnt = 0;
       outer :
       for (int i = 0; i < m; i++) {
            Node node = root;
            String string = br.readLine();
            for (int j = 0; j < string.length(); j++) {
                String s = Character.toString(string.charAt(j));
                if (node.map.get(s) == null) {
                    continue outer;
                }
                if (j == string.length() - 1 && node.map.get(s).map.size() != 0) {
                    continue outer;
                }
                node = node.map.get(s);
            }
            cnt++;
        }

        System.out.println(cnt);
    }

    static class Node{
        TreeMap<String, Node> map = new TreeMap<>();
    }
}
