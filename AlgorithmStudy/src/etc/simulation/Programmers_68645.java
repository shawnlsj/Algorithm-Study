package etc.simulation;

import java.util.Arrays;

public class Programmers_68645 {
    int[][] board;
    int row;
    int col;
    int now;
    int size;

    public static void main(String[] args) {
        Programmers_68645 p = new Programmers_68645();
        System.out.println(Arrays.toString(p.solution(3)));
    }

    public int[] solution(int n) {
        int len = (n * (n + 1)) / 2;
        int[] answer = new int[len];
        size = n;
        row = 1;
        col = 1;
        now = 1;
        board = new int[size + 1][size + 1];

        board[row][col] = 1;
        now++;
        while (now <= len) {
            goDown();
            goRight();
            goLeftUp();
        }

        int k = 0;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (board[i][j] != 0) {
                    answer[k++] = board[i][j];
                }
            }
        }
        return answer;
    }

    void goDown() {
        while (true) {
            if (row + 1 <= size && board[row + 1][col] == 0) {
                row++;
                board[row][col] = now;
                now++;
            } else {
                return;
            }
        }
    }

    void goRight() {
        while (true) {
            if (col + 1 <= size && board[row][col + 1] == 0) {
                col++;
                board[row][col] = now;
                now++;
            } else {
                return;
            }
        }
    }

    void goLeftUp() {
        while (true) {
            if (board[row - 1][col - 1] == 0) {
                row--;
                col--;
                board[row][col] = now;
                now++;
            } else {
                return;
            }
        }
    }
}
