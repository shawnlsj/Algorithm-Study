package etc.trie;

import java.util.*;

//가사 검색
public class Programmers_60060 {
    public static void main(String[] args) {
        Programmers_60060 p = new Programmers_60060();
        String[] words = {"aca","ca"};
        String[] queries = {"??"};

        System.out.println(Arrays.toString(p.solution(words, queries)));
    }
    public int[] solution(String[] words, String[] queries) {
        int[] wordLength = new int[100000 + 1];
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            wordLength[words[i].length()]++;
        }

        int[] answer = new int[queries.length];
        Node root = new Node();
        Node reverseRoot = new Node();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Node node = root;
            for (int j = 0; j < word.length(); j++) {
                if (node.nodes[word.charAt(j) - 'a'] == null) {
                    node.nodes[word.charAt(j) - 'a'] = new Node();
                }
                node = node.nodes[word.charAt(j) - 'a'];
            }
            node.registered = true;
            node = reverseRoot;
            for (int j = word.length() - 1; j >= 0; j--) {
                if (node.nodes[word.charAt(j) - 'a'] == null) {
                    node.nodes[word.charAt(j) - 'a'] = new Node();
                }
                node = node.nodes[word.charAt(j) - 'a'];
            }
            node.registered = true;
        }
            for (int i = 0; i < queries.length; i++) {
                String query = queries[i];
                int cnt = 0;
                if (query.charAt(0) == '?' && query.charAt(query.length() - 1) != '?') {
                    Node node = reverseRoot;
                    for (int j = query.length() - 1; j >= 0; j--) {
                        if (query.charAt(j) == '?') {
                            if (hashMap.get(query) == null) {
                                cnt = dfs(node, j + 1);
                                hashMap.put(query, cnt);
                            } else {
                                cnt = hashMap.get(query);
                            }
                            break;
                        } else {
                            node = node.nodes[query.charAt(j) - 'a'];
                        }
                        if (node == null) {
                            break;
                        }
                    }
                } else if (query.charAt(0) == '?' && query.charAt(query.length() - 1) == '?') {
                    cnt = wordLength[query.length()];
                } else {
                    Node node = root;
                    for (int j = 0; j < query.length(); j++) {
                        if (query.charAt(j) == '?') {
                            if (hashMap.get(query) == null) {
                                cnt = dfs(node, query.length() - j);
                                hashMap.put(query, cnt);
                            } else {
                                cnt = hashMap.get(query);
                            }
                            break;
                        } else {
                            node = node.nodes[query.charAt(j) - 'a'];
                        }
                        if (node == null) {
                            break;
                        }
                    }
                }
                answer[i] = cnt;
            }

        return answer;
    }

    int dfs(Node node, int depth) {
        if (depth == 0) {
            if (node.registered) {
                return 1;
            } else
                return 0;
        }
        int sum = 0;
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null) {
                sum += dfs(node.nodes[i], depth - 1);
            }
        }
        return sum;
    }
    class Node{
        boolean registered = false;
        Node[] nodes = new Node[26];
    }
}
