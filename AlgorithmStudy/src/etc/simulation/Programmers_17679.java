package etc.simulation;

public class Programmers_17679 {
    char[][] newBoard;
    boolean[][] markBoard;
    int answer;
    int row;
    int col;

    public static void main(String[] args) {
        Programmers_17679 p = new Programmers_17679();
        System.out.println(p.solution(4, 5, new String[]
                {"CCBDE", "AAADE", "AAABF", "CCBBF"}
        ));
    }
        public int solution(int m, int n, String[] board) {
            answer = 0;
            newBoard = new char[m + 1][n + 1];
            markBoard = new boolean[m + 1][n + 1];
            row = m;
            col = n;

            for (int i = 1; i <= row; i++) {
                String s = board[i - 1];
                for (int j = 1; j <= col; j++) {
                    newBoard[i][j] = s.charAt(j - 1);
                }
            }

            while (true) {
                for (int i = 1; i <= row - 1; i++) {
                    for (int j = 1; j <= col - 1; j++) {
                        int[] cnt = new int[27];
                        boolean isFour = false;
                        for (int k = 0; k <= 1; k++) {
                            cnt[newBoard[i][j + k] - 'A']++;
                            cnt[newBoard[i + 1][j + k] - 'A']++;
                        }

                        for (int k = 0; k <= 25; k++) {
                            if (cnt[k] == 4) {
                                isFour = true;
                            }
                        }
                        if (isFour) {
                            marking(i, j);
                        }
                    }
                }
                if (!delete()) {
                    break;
                } else {
                    moveDown();
                }
            }
            return answer;
        }
        void marking(int i, int j) {
            markBoard[i][j] = true;
            markBoard[i][j + 1] = true;
            markBoard[i + 1][j] = true;
            markBoard[i + 1][j + 1] = true;
        }
        boolean delete() {
            boolean isDeleted = false;
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    if (markBoard[i][j]) {
                        answer++;
                        newBoard[i][j] = 'Z' + 1;
                        markBoard[i][j] = false;
                        isDeleted = true;
                    }
                }
            }
            return isDeleted;
        }
        void moveDown() {
            for (int i = 1; i <= col; i++) {
                int blankRow = row;
                boolean isBlank = false;
                for (int j = row; j >= 1; j--) {
                    if (newBoard[j][i] != 'Z' + 1) {
                        if (isBlank) {
                            newBoard[blankRow][i] = newBoard[j][i];
                            newBoard[j][i] = 'Z' + 1;
                            i--;
                            break;
                        } else {
                            blankRow--;
                        }
                    } else {
                        isBlank = true;
                    }
                }
            }
        }
}
