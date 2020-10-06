package etc.stack;

import java.util.Stack;

// 크레인 인형뽑기 게임
public class Programmers_64061 {
    static Stack<Integer> stack = new Stack<>();

    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        for (int i = 0; i < moves.length; i++) {
            int pick = 0;
            for (int j = 0; j < board.length; j++) {
                int target = board[j][moves[i] - 1];
                if (target != 0) {
                    pick = target;
                    board[j][moves[i] - 1] = 0;
                }
                if (pick > 0) {
                    stack.push(pick);
                    if (stack.size() >= 2 && isDouble()) {
                        answer += 2;
                        stack.pop();
                        stack.pop();
                    }
                    break;
                }
            }
        }

        return answer;

    }
    boolean isDouble() {
        if (stack.get(stack.size() - 2) == stack.get(stack.size() - 1)) {
            return true;
        }
        return false;
    }
}
