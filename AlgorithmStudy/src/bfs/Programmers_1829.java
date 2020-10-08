package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_1829 {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] rowDir = {0, 1, 0, -1};
        int[] colDir = {1, 0, -1, 0};
        int[] answer = new int[2];
        Node[][] board = new Node[m + 1][n + 1];

        Queue<Node> q = new LinkedList<>();
        Queue<Node> wait = new LinkedList<>();

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = new Node(i, j, picture[i - 1][j - 1]);
                if (board[i][j].num != 0) {
                    wait.offer(board[i][j]);
                }
            }
        }
        while (!wait.isEmpty()) {
            Node startNode = wait.poll();
            if (startNode.visited) {
                continue;
            } else {
                startNode.visited = true;
                q.offer(startNode);
            }
            int unionSize = 1;
            int unionNum = startNode.num;

            while (!q.isEmpty()) {
                Node node = q.poll();
                for (int i = 0; i < 4; i++) {
                    int afterRow = node.row + rowDir[i];
                    int afterCol = node.col + colDir[i];

                    if (afterRow > m || afterCol > n || afterRow < 1 || afterCol < 1) {
                        continue;
                    }
                    Node connectedNode = board[afterRow][afterCol];

                    if (connectedNode.num == 0 || connectedNode.visited) {
                        continue;
                    }
                    if (connectedNode.num == unionNum) {
                        unionSize++;
                        connectedNode.visited = true;
                        q.offer(connectedNode);
                    }
                }
            }
            numberOfArea++;
            maxSizeOfOneArea = Math.max(maxSizeOfOneArea, unionSize);
        }
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    class Node{
        int row;
        int col;
        int num;
        boolean visited;

        Node(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
            visited = false;
        }
    }
}
