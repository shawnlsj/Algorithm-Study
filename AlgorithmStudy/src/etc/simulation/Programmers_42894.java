package etc.simulation;

public class Programmers_42894 {
    public static void main(String[] args) {
        Programmers_42894 p = new Programmers_42894();
        System.out.println(p.solution(
                new int[][]{
                        {4, 4, 4, 3, 0},
                        {0, 4, 2, 3, 0},
                        {0, 1, 2, 3, 3},
                        {0, 1, 2, 2, 0},
                        {0, 1, 1, 0, 0}}
        ));
    }

    public int solution(int[][] board) {
        int answer = 0;

        while (true) {
            boolean isDeleted = false;
            // 가능한 만큼 검은 블록을 떨어뜨린다
            for (int col = 0; col < board.length; col++) {
                for (int row = 0; row < board.length; row++) {
                    if (board[row][col] >= 1) {
                        break;
                    }
                    board[row][col] = -1;
                }
            }

            // 2x3 으로 찾는다
            for (int row = 0; row < board.length - 1; row++) {
                for (int col = 0; col < board.length - 2; col++) {
                    int blackBlock = 0;
                    int colorBlock = 0;
                    int nodeNum = -1;

                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 2; j++) {
                            int num = board[row + i][col + j];
                            if (num == -1) {
                                blackBlock++;
                            } else if (num >= 1 && nodeNum == -1) {
                                nodeNum = num;
                                colorBlock++;
                            } else if (num >= 1 && num == nodeNum) {
                                colorBlock++;
                            }
                        }
                    }

                    if (blackBlock == 2 && colorBlock == 4) {
                        answer++;
                        isDeleted = true;
                        for (int i = 0; i <= 1; i++) {
                            for (int j = 0; j <= 2; j++) {
                                board[row + i][col + j] = 0;
                            }
                        }
                    }
                }//for col
            }// for row


            // 3x2 으로 찾는다
            for (int row = 0; row < board.length - 2; row++) {
                for (int col = 0; col < board.length - 1; col++) {
                    int blackBlock = 0;
                    int colorBlock = 0;
                    int nodeNum = -1;

                    for (int i = 0; i <= 2; i++) {
                        for (int j = 0; j <= 1; j++) {
                            int num = board[row + i][col + j];
                            if (num == -1) {
                                blackBlock++;
                            } else if (num >= 1 && nodeNum == -1) {
                                nodeNum = num;
                                colorBlock++;
                            } else if (num >= 1 && num == nodeNum) {
                                colorBlock++;
                            }
                        }
                    }

                    if (blackBlock == 2 && colorBlock == 4) {
                        answer++;
                        isDeleted = true;
                        for (int i = 0; i <= 2; i++) {
                            for (int j = 0; j <= 1; j++) {
                                board[row + i][col + j] = 0;
                            }
                        }
                    }
                }//for col
            }// for row

            if (!isDeleted) {
                break;
            }
        }
        return answer;
    }
}
