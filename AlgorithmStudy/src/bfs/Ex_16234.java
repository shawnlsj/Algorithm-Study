package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Ex_16234 {
    static int n;
    static int min;
    static int max;
    static Country[][] countryBoard;
    static boolean isOpened;

    static int[] rowDir = {0, 1, 0, -1}; //동쪽 부터 시계방향
    static int[] colDir = {1, 0, -1, 0};

    static int answer = 0;

    static boolean[][] visited;
    static ArrayList<ArrayList<Country>> unions;

    static Queue<Country> q = new LinkedList<>();

    static class Country {
        int row;
        int col;
        int population;

        boolean east;
        boolean west;
        boolean south;
        boolean north;

        public Country(int row, int col, int population) {
            this.row = row;
            this.col = col;
            this.population = population;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        min = Integer.parseInt(stk.nextToken());
        max = Integer.parseInt(stk.nextToken());

        countryBoard = new Country[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(stk2.nextToken());
                countryBoard[i][j] = new Country(i, j, x);
            }
        }

        while (true) {
            openBoundary(); // 국경선을 연다

            //국경이 하나라도 열려있으면 인구 이동 수행 아니면 반복문 탈출
            if (isOpened) {
                unions = new ArrayList<>();
                for (boolean[] arr : visited) {
                    Arrays.fill(arr, false);
                }
                unify();// 연합끼리 묶는다
                move(); // 인구 이동을 한다
                for (Country[] arr : countryBoard) {
                    for (int i = 1; i <= n; i++) {
                        if(arr[i] == null) continue;
                        System.out.print(arr[i].population+" ");
                    }
                    System.out.println();
                }

                closeBoundary();// 국경선을 닫는다
                unions = null; //연합 목록을 리셋한다

                answer++;  // 카운트를 올린다
            } else {
                break;
            }
        }
        System.out.println(answer);

    }

    static void closeBoundary() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                Country country = countryBoard[i][j];
                country.east = false;
                country.west = false;
                country.south = false;
                country.north = false;
            }
        }
        isOpened = false;
    }

    static void unify() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                Country country = countryBoard[i][j];
                if (visited[country.row][country.col]) continue;

                for (int m = 1; m <= n; m++) {
                    for (int z = 1; z <= n; z++) {
                        System.out.print(visited[m][z]+" ");
                    }
                    System.out.println();
                }
                System.out.println("-----");

                ArrayList<Country> union = new ArrayList();
                unions.add(union);

                //방문 처리를 하고 큐에 넣는다
                visited[country.row][country.col] = true;
                System.out.println("country.row = " + country.row);
                System.out.println("country.col = " + country.col);
                q.offer(country);

                bfs(union);
            }
        }

    }

    static void bfs(ArrayList<Country> union) {
        if (q.isEmpty()) return;
        Country country = q.poll();
        union.add(country);
        //큐에서 빼낸 country 에 대하여 동서남북으로 연결된 국가가 있는지 확인
        for (int i = 0; i < 4; i++) {
            int afterRow = country.row + rowDir[i];
            int afterCol = country.col + colDir[i];
            if (afterRow < 1 || afterCol < 1 || afterRow > n || afterCol > n) {
                continue;
            }
            if (visited[afterRow][afterCol]) {
                continue;
            }

            switch (i) {
                // 0부터 동 남 서 북
                case 0:
                    if (country.east) {
                        visited[afterRow][afterCol] = true;
                        q.offer(countryBoard[afterRow][afterCol]);
                    }
                    break;
                case 1:
                    if (country.south) {
                        visited[afterRow][afterCol] = true;
                        q.offer(countryBoard[afterRow][afterCol]);
                    }
                    break;
                case 2:
                    if (country.west) {
                        visited[afterRow][afterCol] = true;
                        q.offer(countryBoard[afterRow][afterCol]);
                    }
                    break;
                case 3:
                    if (country.north) {
                        visited[afterRow][afterCol] = true;
                        q.offer(countryBoard[afterRow][afterCol]);
                    }
                    break;
            }
        }
        bfs(union);
    }

    static void move() {
        System.out.println("unions = " + unions.size());
        for (int i = 0; i < unions.size(); i++) {
            ArrayList<Country> union = unions.get(i);
            int sumOfPopulation = 0;

            //해당 연합의 총 인구수 구하기
            for (int j = 0; j < union.size(); j++) {
                sumOfPopulation += union.get(j).population;
            }

            //연합의 국가 별로 인구 업데이트
            for (int j = 0; j < union.size(); j++) {
                Country country = union.get(j);
                country.population = sumOfPopulation / union.size();
            }
            System.out.println("sumOfPopulation = " + sumOfPopulation);
            System.out.println("union.size() = " + union.size());

        }
    }

    static void openBoundary() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k < 4; k++) {
                    Country c = countryBoard[i][j];
                    int afterRow = c.row + rowDir[k];
                    int afterCol = c.col + colDir[k];

                    if (afterRow < 1 || afterCol < 1 || afterRow > n || afterCol > n) continue;

                    //k가 0일 때 동쪽부터 시계방향
                    if (min <= Math.abs(countryBoard[afterRow][afterCol].population - c.population)
                            && max >= Math.abs(countryBoard[afterRow][afterCol].population - c.population)) {
                        isOpened = true; // 국경이 하나라도 열렸음을 체크한다
                        switch (k) {
                            case 0:
                                c.east = true;
                                break;
                            case 1:
                                c.south = true;
                                break;
                            case 2:
                                c.west = true;
                                break;
                            case 3:
                                c.north = true;
                                break;
                        }
                    }
                }//for k
            }//for j
        }//for i
    }//메소드 끝

}
