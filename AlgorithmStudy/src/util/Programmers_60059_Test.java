package util;

import java.util.Arrays;

public class Programmers_60059_Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        Programmers_60059_v2 test = new Programmers_60059_v2();
        int[][] k = new int[3][3];
        int[][] k2 = new int[3][3];
        int[][] k3 = new int[3][3];
        int[][] l = new int[3][3];
        int[][] l2 = new int[3][3];
        int[][] l3 = new int[3][3];


        while (true) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    k[i][j] = (int) (Math.random() * 2);
                    l[i][j] = (int) (Math.random() * 2);
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    k2[i][j] = k[i][j];
                    k3[i][j] = k[i][j];
                    l2[i][j] = l[i][j];
                    l3[i][j] = l[i][j];
                }
            }

            boolean correct = sol.solution(k, l);
            boolean testAnswer = test.solution(k, l);
            if (correct != testAnswer) {
                System.out.println("--");
                System.out.println("correct = " + correct);
                System.out.println("testAnswer = " + testAnswer);

                System.out.println("key");
                for (int[] a : k3) {
                    System.out.println(Arrays.toString(a));
                }
                System.out.println("lock");
                for (int[] a2 : l3)
                    System.out.println(Arrays.toString(a2));
                return;
            }


        }
    }

    static class Programmers_60059_v2 {
        static int[][] bigLock;
        static int[][] originalLock;
        static int[][] originalKey;
        static int keySize;
        static int lockSize;
        static boolean answer;

        public static void main(String[] args) {
            Programmers_60059_v2 p = new Programmers_60059_v2();
            int[][] k = {
                    {1, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0}
            };
            int[][] l = {
                    {1, 1, 1},
                    {0, 0, 1},
                    {0, 1, 1},
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
                // 현재 키를 이동만 사용해서 모든 경우의 수를 대입해본다
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
            for (int i = lockSize; i < 2 * lockSize - 1; i++) {
                for (int j = lockSize; j < 2 * lockSize - 1; j++) {
                    if (bigLock[i][j] != 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        void resetLock() {
            for (int i = lockSize, x = 0; i < 2 * lockSize - 1; i++, x++) {
                for (int j = lockSize, y = 0; j < 2 * lockSize - 1; j++, y++) {
                    bigLock[i][j] = originalLock[x][y];
                }
            }
        }

    }

    static class Solution {

        // 2차원 리스트 90도 회전하기
        public static int[][] rotateMatrixBy90Degree(int[][] a) {
            int n = a.length;
            int m = a[0].length;
            int[][] result = new int[n][m]; // 결과 리스트
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[j][n - i - 1] = a[i][j];
                }
            }
            return result;
        }

        // 자물쇠의 중간 부분이 모두 1인지 확인
        public static boolean check(int[][] newLock) {
            int lockLength = newLock.length / 3;
            for (int i = lockLength; i < lockLength * 2; i++) {
                for (int j = lockLength; j < lockLength * 2; j++) {
                    if (newLock[i][j] != 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean solution(int[][] key, int[][] lock) {
            int n = lock.length;
            int m = key.length;
            // 자물쇠의 크기를 기존의 3배로 변환
            int[][] newLock = new int[n * 3][n * 3];
            // 새로운 자물쇠의 중앙 부분에 기존의 자물쇠 넣기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newLock[i + n][j + n] = lock[i][j];
                }
            }

            // 4가지 방향에 대해서 확인
            for (int rotation = 0; rotation < 4; rotation++) {
                key = rotateMatrixBy90Degree(key); // 열쇠 회전
                for (int x = 0; x < n * 2; x++) {
                    for (int y = 0; y < n * 2; y++) {
                        // 자물쇠에 열쇠를 끼워 넣기
                        for (int i = 0; i < m; i++) {
                            for (int j = 0; j < m; j++) {
                                newLock[x + i][y + j] += key[i][j];
                            }
                        }
                        // 새로운 자물쇠에 열쇠가 정확히 들어 맞는지 검사
                        if (check(newLock)) return true;
                        // 자물쇠에서 열쇠를 다시 빼기
                        for (int i = 0; i < m; i++) {
                            for (int j = 0; j < m; j++) {
                                newLock[x + i][y + j] -= key[i][j];
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}

