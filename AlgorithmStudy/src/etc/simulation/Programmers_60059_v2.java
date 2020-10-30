package etc.simulation;

public class Programmers_60059_v2 {
    static int[][] bigLock;
    static int[][] originalLock;
    static int[][] originalKey;
    static int keySize;
    static int lockSize;
    static boolean answer;

    public static void main(String[] args) {
        Programmers_60059_v2 p = new Programmers_60059_v2();
        int[][] k = {
                {1, 1, 1},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] l = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 0},
        };
        System.out.println(p.solution(k,l));
    }
    public boolean solution(int[][] key, int[][] lock) {
        answer = false;

        keySize = key.length;
        lockSize = lock.length;

        bigLock = new int[lockSize*3][lockSize*3];
        originalLock = lock;
        originalKey = key;

        resetLock();

        for (int i = 0; i < 4; i++) {
            // 현재 키를 이동만 사용해서 가능한 모든 경우의 수를 대입해본다
            if (completeSearch()) {
                answer = true;
                break;
            }
                turn90Clockwise(); // 키를 시계방향으로 90도 회전시킨다
            }
        return answer;
    }
    void turn90Clockwise() {
            int[][] arr = new int[keySize][keySize];

            for (int i = 0; i < keySize; i++) {
                for (int j = 0; j < keySize; j++) {
                    arr[j][(keySize-1)-i] = originalKey[i][j];
                }
            }
            originalKey = arr;
    }
    boolean completeSearch() {
        int row=0;
        int col=0;
        while(row < keySize + lockSize - 1) {
            for (int i = 0, x = lockSize - (keySize - 1) + row; i < keySize; i++, x++) {
                for (int j = 0, y = lockSize - (keySize - 1) + col; j < keySize; j++, y++) {
                    bigLock[x][y] += originalKey[i][j];
                }
            }
            if (isCorrect()) return true;
            resetLock();
            if (col < lockSize + keySize - 3) {
                col++;
            } else {
                col = 0;
                row++;
            }
        }
        return false;
    }
    boolean isCorrect() {
        for (int i = lockSize; i < 2 * lockSize; i++) {
            for (int j = lockSize; j < 2 * lockSize; j++) {
                if (bigLock[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    void resetLock() {
        for (int i = lockSize, x = 0; i < 2 * lockSize; i++, x++) {
            for (int j = lockSize, y = 0; j < 2 * lockSize; j++, y++) {
                bigLock[i][j] = originalLock[x][y];
            }
        }
    }
}
