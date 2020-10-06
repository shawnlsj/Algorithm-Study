package etc.simulation;//자물쇠와 열쇠

import java.util.Arrays;

public class Programmers_60059 {
    static int m;
    static int n;
    static int keyCnt = 0; // 열쇠 돌기 개수
    static int lockCnt = 0; // 자물쇠 돌기 개수

    static int[][] key;
    static int[][] lock;
    static boolean answer = false;

    public static void main(String[] args) {
        Programmers_60059 p = new Programmers_60059();
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
        for (int[] z : key) {
            System.out.println(Arrays.toString(z));
        }
        System.out.println("rootDir = " + rootDir);
        System.out.println("dir = " + dir);
        System.out.println("--");
        if (keyCnt == lockCnt) {
            if (checkClockWise(key)) {
                answer = true;
            }
            return;
        }

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



        if (keyCnt < lockCnt) {
            return;
        }

        int[][] copy = new int[key.length][key.length];
        int copyKeyCnt = keyCnt;

        moveKeyToArr(key, copy);

        for (int i = 0; i <= 3; i++) {
            //반대 방향으로는 호출하지 않는다
            if (i == (rootDir + 2) % 4 || i == (dir + 2) % 4) {
                continue;
            }
            System.out.println("호출부");
            for (int[] z : key) {
                System.out.println(Arrays.toString(z));
            }
            System.out.println("rootDir 호출= " + rootDir);
            System.out.println("i = " + i);
            System.out.println("호출부 끝 ----");
            dfs(rootDir, i);
            moveKeyToArr(copy, key);
            keyCnt = copyKeyCnt;
        }
    }
    boolean checkUpDown() {
        int[][] keyCopy = new int[key.length][key.length];
        moveKeyToArr(key, keyCopy);

        for (int i = 0; i < key.length - 1; i++) {
            keyCopy = moveUp(keyCopy);
            if (keyCnt == lockCnt) {
                if (checkClockWise(keyCopy)) {
                    answer = true;
                    return true;
                }
            }
        }

        moveKeyToArr(key, keyCopy);

        for (int i = 0; i < key.length - 1; i++) {
            keyCopy = moveDown(keyCopy);
            if (keyCnt == lockCnt) {
                if (checkClockWise(keyCopy)) {
                    answer = true;
                    return true;
                }
            }
        }
        return false;
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
            System.out.println("clock--");
            for (int[] a : arr) {
                System.out.println(Arrays.toString(a));
            }
            System.out.println("otherwise--");
            return true;
        }
        return false;
    }
    boolean check(int[][] key) {
        int[][] arr = new int[n][n];
        int[][] copy = new int[n][n];

        moveKeyToArr(key, arr);
        moveKeyToArr(arr, copy);

        //아래로 움직인다
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (isEqual(arr)) return true;
                    arr = moveRight(arr);
                }
                if (isEqual(arr)) return true;

                moveKeyToArr(copy, arr); //arr 원상 복귀

                for (int j = 0; j < n - 1; j++) {
                    if (isEqual(arr)) return true;
                    arr = moveLeft(arr);
                }

                if (isEqual(arr)) return true;
                moveKeyToArr(copy, arr);
                arr = moveDown(arr);
                moveKeyToArr(arr, copy);
            }
            arr = new int[n][n];
            key = turnClockwise(key);
            moveKeyToArr(key, arr);
            moveKeyToArr(arr, copy);
        }


        //위로 움직인다
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (isEqual(arr)) return true;
                    arr = moveRight(arr);
                }
                if (isEqual(arr)) return true;

                moveKeyToArr(copy, arr); //arr 원상 복귀

                for (int j = 0; j < n - 1; j++) {
                    if (isEqual(arr)) return true;
                    arr = moveLeft(arr);
                }

                if (isEqual(arr)) return true;
                moveKeyToArr(copy, arr);
                arr = moveUp(arr);
                moveKeyToArr(arr, copy);
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
