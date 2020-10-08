import java.util.ArrayList;
import java.util.Arrays;

public class Programmers_60061 {
    static Node[][] board;
    static int boardSize;

    public static void main(String[] args) {
        Programmers_60061 p = new Programmers_60061();
        int n = 5;
        int[][] build_frame = {
                {0, 0, 0, 1},
                {2, 0, 0, 1},
                {4, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {2, 1, 1, 1},
                {3, 1, 1, 1},
                {2, 0, 0, 0},
                {1, 1, 1, 0},
                {2, 2, 0, 1}
        };
        System.out.println(Arrays.deepToString(p.solution(n, build_frame)));
    }

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        boardSize = n + 1;
        board = new Node[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Node(i, j);
            }
        }

        for (int i = 0; i < build_frame.length; i++) {
            int y = build_frame[i][0];
            int x = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3];
            
            Node node = board[x][y];
            // a 0 기둥 1 보
            // b 0 삭제 1 설치
            if (a == 0) {
                //기둥 삭제
                if (b == 0) {
                    if (isDeletable(node, 0)) {
                        node.isConnectedUp = false;
                        board[x + 1][y].isConnectedDown = false;
                    }
                    
                    //기둥 설치
                } else if (b == 1) {
                    if (isCreatable(node, 0)) {
                        node.isConnectedUp = true;
                        board[x + 1][y].isConnectedDown = true;
                    }
                }
            } else if (a == 1) {
                if (b == 0) {
                    if (isDeletable(node, 1)) {
                        node.isConnectedRight = false;
                        board[x][y + 1].isConnectedLeft = false;
                    }
                } else if (b == 1) {
                    if (isCreatable(node, 1)) {
                        node.isConnectedRight = true;
                        board[x][y + 1].isConnectedLeft = true;
                    }
                }
            }
        }

        ArrayList<int[]> result = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Node node = board[i][j];
                if (node.isConnectedUp) {
                    result.add(new int[]{j, i, 0});
                }
                if (node.isConnectedRight) {
                    result.add(new int[]{j, i, 1});
                }
            }
        }
        result.sort((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            } else {
                return o1[2] - o2[2];
            }
        });
        return result.toArray(answer);
    }

    boolean isCreatable(Node node, int x) {
        // 0 기둥 1 보

        if (x == 0) {
            if (node.row == 0 ||
                    node.isConnectedLeft || node.isConnectedRight || node.isConnectedDown) {
                return true;
            }
        } else {
            if (node.isConnectedDown ||
                    (board[node.row][node.col + 1].isConnectedDown) ||
                    (board[node.row][node.col + 1].isConnectedRight)) {
                return true;
            }
        }
        return false;
    }

    boolean isDeletable(Node node, int x) {
        // 0 기둥 1 보
        if (x == 0) {
            Node upNode = board[node.row + 1][node.col];
                if (!upNode.isConnectedLeft && !upNode.isConnectedRight && upNode.isConnectedUp) {
                    return false;
                }
                if (upNode.isConnectedLeft && !upNode.isConnectedRight) {
                    if (board[upNode.row][upNode.col - 1].isConnectedLeft ||
                            board[upNode.row][upNode.col - 1].isConnectedDown ) {
                        return true;
                    }
                }
                // ㅣ
                //  ㅡ
                if (upNode.isConnectedRight && !upNode.isConnectedLeft) {
                    if (board[upNode.row][upNode.col + 1].isConnectedRight ||
                            board[upNode.row][upNode.col + 1].isConnectedDown ) {
                        return true;
                    }
                }

                if (upNode.isConnectedLeft && upNode.isConnectedRight) {
                    if ( (board[upNode.row][upNode.col - 1].isConnectedLeft || board[upNode.row][upNode.col - 1].isConnectedDown) &&
                            (board[upNode.row][upNode.col + 1].isConnectedRight || board[upNode.row][upNode.col + 1].isConnectedDown)) {
                        return true;
                    }
                }
        } else {
            // 보 삭제
            Node rightNode = board[node.row][node.col + 1];
            if (node.isConnectedDown) {
                if (board[node.row][node.col + 1].isConnectedDown || board[node.row][node.col + 1].isConnectedRight) {
                    return true;
                }
            } else if (node.isConnectedLeft) {
                if ((board[node.row][node.col - 1].isConnectedDown || board[node.row][node.col - 1].isConnectedLeft)&&
                        (board[node.row][node.col + 1].isConnectedDown || board[node.row][node.col + 1].isConnectedRight)) {
                    return true;
                }
            }
        }
        return false;
    }

    class Node {
        int row;
        int col;
        boolean isConnectedUp = false;
        boolean isConnectedDown = false;
        boolean isConnectedLeft = false;
        boolean isConnectedRight = false;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
