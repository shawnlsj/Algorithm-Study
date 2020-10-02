package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ex_16236_v2 {
    static int n;
    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};
    static Node[][] nodeBoard;
    static int distance;
    static int minDistance;
    static final int INF = (int)1e9;

    static int exp = 0;
    static int row;
    static int col;
    static int sharkSize = 2;
    static int time;
    static Queue<Node> q;
    static ArrayList<Node> fishes;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        nodeBoard = new Node[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(stk.nextToken());
                nodeBoard[i][j] = new Node(i, j, x);
                if (x == 9) {
                    row = i;
                    col = j;
                }
            }
        }


        while(true) {
            int startRow = row;
            int startCol = col;
            q = new LinkedList<>();
            fishes = new ArrayList<>();
            distance = 0;
            minDistance = INF;
            nodeBoard[row][col].setDistance(distance);
            nodeBoard[row][col].visited = true;
            q.add(nodeBoard[row][col]);
            bfs();
            if (fishes.isEmpty()) break;
            fishes.sort((o1, o2) -> {
                if (o1.distance != o2.distance) {
                    return o1.distance - o2.distance;
                } else if (o1.row != o2.row) {
                    return o1.row - o2.row;
                } else {
                    return o1.col - o2.col;
                }
            });
            Node node = fishes.get(0);
            row = node.row;
            col = node.col;
            nodeBoard[startRow][startCol].state = 0;
            node.state = 9;
            System.out.println("row = " + row);
            System.out.println("col = " + col);
            System.out.println(node.distance);
            time += node.distance;
            exp++;
            if (sharkSize == exp) {
                sharkSize++;
                exp = 0;
            }
            clean();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(nodeBoard[i][j].state + " ");
            }
            System.out.println();
        }
        System.out.println(time);

    }

    static void clean(){
        // 방문 여부 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nodeBoard[i][j].visited = false;
                nodeBoard[i][j].distance = 0;
            }
        }
    }

    static void bfs() {
        int afterRow;
        int afterCol;
        while (!q.isEmpty()) {
            Node node = q.poll();
            distance = node.distance;
            row = node.row;
            col = node.col;

            if (minDistance < distance) return;

            for (int i = 0; i < 4; i++) {
                afterRow = row + rowDir[i];
                afterCol = col + colDir[i];
                if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                    continue;
                }
                if (nodeBoard[afterRow][afterCol].visited == false ) {
                    if (nodeBoard[afterRow][afterCol].state != 0 && nodeBoard[afterRow][afterCol].state < sharkSize) {
                        fishes.add(nodeBoard[afterRow][afterCol]);
                        minDistance = Math.min(minDistance, distance + 1);
                        nodeBoard[afterRow][afterCol].visited = true;
                        nodeBoard[afterRow][afterCol].setDistance(distance + 1);
                    } else if (nodeBoard[afterRow][afterCol].state <= sharkSize){
                        q.add(nodeBoard[afterRow][afterCol]);
                        nodeBoard[afterRow][afterCol].visited = true;
                        nodeBoard[afterRow][afterCol].setDistance(distance + 1);
                    }
                }
            }
        }
    }

    static class Node{
        int row;
        int col;
        int state;
        boolean visited = false;
        int distance;
        public Node(int row, int col, int state) {
            this.row = row;
            this.col = col;
            this.state = state;
        }

        void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
