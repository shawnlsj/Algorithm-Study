package etc.simulation;

import java.util.ArrayList;
import java.util.Arrays;

// 길 찾기 게임
public class Programmers_42892 {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Programmers_42892().solution(
                new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}}

        )));
    }

    ArrayList<Integer> preorderList = new ArrayList<>();
    ArrayList<Integer> postorderList = new ArrayList<>();

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][0];

        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            Node node = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
            nodes.add(node);
        }
        nodes.sort((o1, o2) -> {
            if (o1.y != o2.y) {
                return o2.y - o1.y;
            } else
                return o1.x - o2.x;
        });

        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            Node parent = root;
            while (true) {
                if (node.x < parent.x) {
                    if (parent.left != null) {
                        parent = parent.left;
                    } else {
                        parent.left = node;
                        break;
                    }
                } else {
                    if (parent.right != null) {
                        parent = parent.right;
                    } else {
                        parent.right = node;
                        break;
                    }
                }
            }
        }

        preorder(root);
        postorder(root);

        int[] preArr = new int[preorderList.size()];
        int[] postArr = new int[postorderList.size()];

        for (int i = 0; i < preorderList.size(); i++) {
            preArr[i] = preorderList.get(i);
            postArr[i] = postorderList.get(i);
        }

        answer[0] = preArr;
        answer[1] = postArr;

        return answer;
    }

    //전위 순회 root left right
    void preorder(Node node) {
        preorderList.add(node.num);
        if (node.left != null) {
            preorder(node.left);
        }
        if (node.right != null) {
            preorder(node.right);
        }
    }

    //후위 순회 left right root
    void postorder(Node node) {
        if (node.left != null) {
            postorder(node.left);
        }
        if (node.right != null) {
            postorder(node.right);
        }
        postorderList.add(node.num);
    }

    class Node {
        int x;
        int y;
        int num;
        Node left;
        Node right;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
