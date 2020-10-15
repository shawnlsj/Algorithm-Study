import java.util.*;

public class Programmers_60053 {
    Node[][] board;
    int[] rowDir = {0, 1, 0, -1}; //동쪽부터 시계방향 순서
    int[] colDir = {1, 0, -1, 0};
    int n;
    // 방문여부를 동남서북 순서 4자리 숫자로 나타냄 방문했으면 1 없으면 0
    // 해당 노드에서 동쪽이랑 서쪽으로 노드가 연결된 적 있으면 1010
    int[][] visited;
    Queue<Node> heads = new LinkedList<>();
    Queue<Node> tails = new LinkedList<>();
    Queue<Integer> times = new LinkedList<>();
    Queue<Integer> dirs = new LinkedList<>(); // head 를 기준으로 연결된 방향을 저장할 큐

    public static void main(String[] args) {
        Programmers_60053 p = new Programmers_60053();
        System.out.println( p.solution(new int[][]{
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}}));
    }
    public int solution(int[][] map) {
        n = map.length;
        board = new Node[n + 1][n + 1];
        visited = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = new Node(i, j, map[i - 1][j - 1]);
            }
        }

        visited[1][1] =  visited[1][1] | 1; // 2 pow 1
        visited[1][2] =  visited[1][2] | 4; // 2 pow 2
        heads.add(board[1][2]);
        tails.add(board[1][1]);
        times.add(0);
        dirs.add(2);

        while (true) {
            Node head = heads.poll();
            Node tail = tails.poll();
            int time = times.poll();
            int dir = dirs.poll();

            // 목표지점에 도달했는지 확인
            if ((head.row == n && head.col == n) || (tail.row == n && tail.col == n)) {
                return time;
            }

            int headAfterRow;
            int headAfterCol;
            int tailAfterRow;
            int tailAfterCol;
            //상하좌우 이동
            for (int i = 0; i < 4; i++) {
                headAfterRow = head.row + rowDir[i];
                tailAfterRow = tail.row + rowDir[i];
                headAfterCol = head.col + colDir[i];
                tailAfterCol = tail.col + colDir[i];

                // 이동 가능한 지역인지 확인
                if (!isMovable(headAfterRow, headAfterCol) || 
                        !isMovable(tailAfterRow, tailAfterCol)) {
                    continue;
                }

                // 방문한적이 있는지 확인
                if (isVisited(board[headAfterRow][headAfterCol], dir)) {
                    continue;
                }

                // 방문처리 후 큐에 추가
                visiting(board[headAfterRow][headAfterCol], board[tailAfterRow][tailAfterCol], dir);
                heads.add(board[headAfterRow][headAfterCol]);
                tails.add(board[tailAfterRow][tailAfterCol]);
                times.add(time + 1);
                dirs.add(dir);
            }

            // head 를 기준으로 회전
            for (int i = 0; i <= 3; i++) {
                if (i == 2) {
                    //head tail 스왑하기
                    dir = (dir + 2) % 4;
                    Node tmp = head;
                    head = tail;
                    tail = tmp;
                }
                int afterDir = dir;
                // 시계방향 회전
                if (i == 0 || i == 2) {
                    switch (dir) {
                        case 0:
                            // 45도 회전
                            tailAfterRow = tail.row + 1;
                            tailAfterCol = tail.col;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow -=1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterRow -=1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol +=1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                        case 1:
                            // 45도 회전
                            tailAfterRow = tail.row;
                            tailAfterCol = tail.col - 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                        case 2:
                            // 45도 회전
                            tailAfterRow = tail.row - 1;
                            tailAfterCol = tail.col;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                        case 3:
                            // 45도 회전
                            tailAfterRow = tail.row;
                            tailAfterCol = tail.col + 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                    }
                    // 반시계 방향 회전
                } else {
                    switch (dir) {
                        case 0:
                            // 45도 회전
                            tailAfterRow = tail.row - 1;
                            tailAfterCol = tail.col;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow +=1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterRow +=1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol +=1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                        case 1:
                            // 45도 회전
                            tailAfterRow = tail.row;
                            tailAfterCol = tail.col + 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                        case 2:
                            // 45도 회전
                            tailAfterRow = tail.row + 1;
                            tailAfterCol = tail.col;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                        case 3:
                            // 45도 회전
                            tailAfterRow = tail.row;
                            tailAfterCol = tail.col - 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }

                            // 90도 회전 후 큐에 추가
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);

                            // 135도 회전
                            tailAfterRow += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 180도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 225도 회전
                            tailAfterCol += 1;
                            if (!isMovable(tailAfterRow, tailAfterCol)) {
                                continue;
                            }
                            // 270도 회전
                            afterDir = (afterDir + 1) % 4;
                            tailAfterRow -= 1;
                            if (!isMovable(tailAfterRow, tailAfterCol) ||
                                    isVisited(head, afterDir)) {
                                continue;
                            }
                            visiting(head, board[tailAfterRow][tailAfterCol], afterDir);
                            heads.add(head);
                            tails.add(board[tailAfterRow][tailAfterCol]);
                            times.add(time + 1);
                            dirs.add(afterDir);
                            break;
                    }
                }
            } // for i
        }
    }

    boolean isMovable(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            return false;
        }
        if (board[row][col].isWall) {
            return false;
        }
        return true;
    }
    boolean isVisited(Node head, int dir) {
        int a = visited[head.row][head.col];
        int b = (int)Math.pow(2, dir);
        if ((a + b) == (a | b)) {
            return false;
        } else {
            return true;
        }
    }

    void visiting(Node head, Node tail, int dir) {
        // head 에서 dir 쪽으로 방문처리
        visited[head.row][head.col] |= (int) Math.pow(2, dir);

        // tail 에서 dir 반대 방향으로 방문처리
        visited[tail.row][tail.col] |= (int) Math.pow(2, (dir + 2) %  4);
    }

    class Node {
        int row;
        int col;
        boolean isWall;

        Node(int row, int col, int wall) {
            this.row = row;
            this.col = col;
            if (wall == 1) {
                isWall = true;
            } else {
                isWall = false;
            }
        }
    }
}
