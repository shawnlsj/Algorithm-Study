package etc.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BackJoon_5052 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfTestCase = Integer.parseInt(br.readLine());

        // 테스트 케이스 별로 반복 수행
        startTestCase :
        for (int i = 0; i < numOfTestCase; i++) {
            // 테스트 케이스 시작 시 루트 노드 초기화
            Node root = new Node();
            ArrayList<String> phoneNumbers = new ArrayList<>();
            int numOfPhoneNumber = Integer.parseInt(br.readLine());
            // trie 구조 만들기
            for (int j = 0; j < numOfPhoneNumber; j++) {
                String phoneNum = br.readLine();
                phoneNumbers.add(phoneNum);
                Node node = root;
                for (int k = 0; k < phoneNum.length(); k++) {
                    if (node.nodes[phoneNum.charAt(k) - '0'] == null) {
                        node.nodes[phoneNum.charAt(k) - '0'] = new Node();
                    }
                    node = node.nodes[phoneNum.charAt(k) - '0'];
                }
            }
            for (int j = 0; j < phoneNumbers.size(); j++) {
                String phoneNum = phoneNumbers.get(j);
                Node node = root;
                for (int k = 0; k < phoneNum.length(); k++) {
                    node = node.nodes[phoneNum.charAt(k) - '0'];
                    if (k == phoneNum.length() - 1 && !node.isLastNode()) {
                        System.out.println("NO");
                        continue startTestCase;
                    }
                }
            }
            System.out.println("YES");
        }
    }

    static class Node{
        Node[] nodes = new Node[10];
        boolean isLastNode() {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }
}
