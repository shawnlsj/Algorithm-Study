import java.util.Arrays;

public class Programmers_60059_Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        Programmers_60059 test = new Programmers_60059();
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
            boolean testAnswer = test.solution(k2, l2);
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


    static class Programmers_60059 {
        static int m;
        static int n;
        static int keyCnt = 0; // 열쇠 돌기 개수
        static int lockCnt = 0; // 자물쇠 돌기 개수

        static int[][] key;
        static int[][] lock;
        static boolean answer = false;

        public boolean solution(int[][] k, int[][] l) {

            key = k;
            lock = l;

            m = key.length; // 열쇠 크기
            n = lock.length; // 자물쇠 크기

            keyCnt = 0; // 열쇠 돌기 개수
            lockCnt = 0; // 자물쇠 돌기 개수

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (key[i][j] == 1) {
                        keyCnt++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (lock[i][j] == 0) {
                        lockCnt++;
                    }
                }
            }

            // key 의 1 개수 < lock 의 1 개수이면 무조건 false
            if (keyCnt < lockCnt){
                return false;
            }

            if (keyCnt == lockCnt) {
                return check(key);
            }
            int[][] copy = new int[key.length][key.length];
            int copyKeyCnt = keyCnt;

            checkUpDown();

            keyCnt = copyKeyCnt;
            moveKeyToArr(key, copy);
            dfs(0, 0);

            keyCnt = copyKeyCnt;
            moveKeyToArr(copy, key);
            dfs(2, 2);

            return answer;
        }

        void dfs(int rootDir, int dir) {
            if (answer) return;

            switch (dir) {
                case 0:
                    key = moveRight(key);
                    break;
                case 1:
                    key = moveDown(key);
                    break;
                case 2:
                    key = moveLeft(key);
                    break;
                case 3:
                    key = moveUp(key);
                    break;
            }

            if (keyCnt == lockCnt) {
                for (int[] a : key) {
                    System.out.println(Arrays.toString(a));
                }
                System.out.println("--");
                if (checkClockWise(key)) {
                    answer = true;
                    return;
                }
            }

            if (keyCnt < lockCnt) {
                return;
            }

            int[][] copy = new int[key.length][key.length];
            int copyKeyCnt = keyCnt;
            int copyLockCnt = lockCnt;

            moveKeyToArr(key, copy);

            for (int i = 0; i <= 3; i++) {
                //반대 방향으로는 호출하지 않는다
                if (i == (rootDir + 2) % 4 || i == (dir + 2) % 4) {
                    continue;
                }

                dfs(rootDir, i);
                moveKeyToArr(copy, key);
                keyCnt = copyKeyCnt;
            }
        }
        void checkUpDown() {
            int[][] keyCopy = new int[key.length][key.length];
            moveKeyToArr(key, keyCopy);
            keyCopy = moveUp(key);
            if (keyCnt == lockCnt) {
                if (checkClockWise(keyCopy)) {
                    answer = true;
                    return;
                }
            }
            keyCopy = moveDown(key);
            if (keyCnt == lockCnt) {
                if (checkClockWise(keyCopy)) {
                    answer = true;
                    return;
                }
            }

        }
        boolean checkClockWise(int[][] key) {
            int[][] arr;
            int[][] keyCopy = new int[key.length][key.length];
            moveKeyToArr(key, keyCopy);


            for (int k = 0; k < 4; k++) {
                arr = new int[lock.length][lock.length];
                moveKeyToArr(keyCopy, arr);
                if (isEqual(arr) == false) {
                    keyCopy = turnClockwise(keyCopy);
                    continue;
                }
                for (int[] a : arr) {
                    System.out.println(Arrays.toString(a));
                }
                return true;
            }
            return false;
        }
        boolean check(int[][] key) {
            int[][] arr = new int[n][n];
            int[][] copy = new int[n][n];

            moveKeyToArr(key, arr);
            moveKeyToArr(arr, copy);

            for (int k = 0; k < 4; k++) {
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - 1; j++) {
                        if (isEqual(arr)) {
                            return true;
                        }
                        arr = moveRight(arr);
                    }
                    if (isEqual(arr)) {
                        return true;
                    }
                    moveKeyToArr(copy, arr);
                    arr = moveDown(arr);
                }
                arr = new int[n][n];
                key = turnClockwise(key);
                moveKeyToArr(key, arr);
                moveKeyToArr(arr, copy);

            }
            return false;
        }

        void moveKeyToArr(int[][] key, int[][] arr) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    arr[i][j] = key[i][j];
                }
            }
        }

        boolean isEqual(int[][] arr) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == lock[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        int[][] turnClockwise(int[][] key) {
            int[][] arr = new int[key.length][key.length];

            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    arr[j][(key.length-1)-i] = key[i][j];
                }
            }
            return arr;
        }

        int[][] moveRight(int[][] key){
            int[][] arr = new int[key.length][key.length];
            keyCnt = 0;
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (j + 1 > key.length - 1) continue;
                    arr[i][j + 1] = key[i][j];
                    if (key[i][j] == 1) {
                        keyCnt++;
                    }
                }
            }
            return arr;
        }

        int[][] moveLeft(int[][] key) {
            keyCnt = 0;
            int[][] arr = new int[key.length][key.length];
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (j - 1 < 0) continue;
                    arr[i][j - 1] = key[i][j];
                    if (key[i][j] == 1) {
                        keyCnt++;
                    }
                }
            }
            return arr;
        }
        int[][] moveUp(int[][] key) {
            keyCnt = 0;
            int[][] arr = new int[key.length][key.length];
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (i - 1 < 0) break;
                    arr[i - 1][j] = key[i][j];
                    if (key[i][j] == 1) {
                        keyCnt++;
                    }
                }
            }
            return arr;
        }
        int[][] moveDown(int[][] key) {
            keyCnt = 0;
            int[][] arr = new int[key.length][key.length];
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (i + 1 > key.length - 1) break;
                    arr[i + 1][j] = key[i][j];
                    if (key[i][j] == 1) {
                        keyCnt++;
                    }
                }
            }
            return arr;
        }
    }

}

