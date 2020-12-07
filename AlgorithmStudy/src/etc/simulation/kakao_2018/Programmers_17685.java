package etc.simulation.kakao_2018;

public class Programmers_17685 {
    public static void main(String[] args) {
        Programmers_17685 p = new Programmers_17685();
        p.solution(new String[]{"abc", "abcd"});
    }
    public int solution(String[] words) {
        int answer = 0;
        Node root = new Node();

        // trie 구조 만들기
        for (String word : words) {
            Node prev = root;
            for (int i = 0; i < word.length(); i++) {

                if (prev.nodes[word.charAt(i) - 'a'] == null) {
                    prev.nodes[word.charAt(i) - 'a'] = new Node();
                }

                Node now = prev.nodes[word.charAt(i) - 'a'];
                now.countOfWord++;

                if (i == word.length() - 1) {
                    now.registered = true;
                } else {
                    prev = now;
                }
            }
        }

        // 입력해야 하는 단어 개수 세기
        for (String word : words) {
            int deep = 0;
            Node prev = root;
            for (int i = 0; i < word.length(); i++) {
                deep++;
                Node now = prev.nodes[word.charAt(i) - 'a'];
                if (now.countOfWord == 1 || i == word.length() - 1) {
                    answer += deep;
                    break;
                }
                prev = now;
            }
        }

        return answer;
    }

    class Node{
        int countOfWord = 0;
        boolean registered = false;
        Node[] nodes = new Node[26];
    }
}
