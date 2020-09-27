package binary_serach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Ex_2110 {
    static int n; //집의 개수
    static int c; //공유기 개수
    static ArrayList<Integer> homes;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());

        homes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            homes.add(x);
        }

        Collections.sort(homes);


        answerMethod();

        System.out.println(answer);
    }

    static void answerMethod() {
        int cnt = 0; // 설치한 공유기의 개수
        int left; // 왼쪽 공유기의 위치
        int right; // 오른쪽 공유기의 위치

        //가장 왼쪽과 오른쪽의 집에 공유기를 1대씩 미리 설치한다
        left = homes.get(0);
        right = homes.get(homes.size() - 1);

        // 설치해야할 수가 2개인 경우에는 최댓값을 바로 구할 수 있어 탐색할 필요가 없다
        if (c == 2) {
            answer = right - left;
            return;
        }

        int start = 1; // 가장 인접한 공유기 사이의 최솟값
        int end = (left + right) / (c-1); // 가장 인접한 공유기 사이의 최댓값
        int middle = (start + end) / 2; // 설치 가능한 최소 거리과 최대 거리의 중간값


        while (true) {
            cnt = 2; //미리 2개를 설치하였으니 2로 초기화한다
            boolean possible = false;
            left = homes.get(0);
            //첫번째와 마지막집은 공유기를 설치하였으니,
            //두번째 집부터 마지막-1번째 집까지 공유기를 설치해나가 본다
            for (int i = 1; i < homes.size() - 1; i++) {
                int d = 0;

                //i번째 집에 대하여 가장 인접한 공유기까지의 거리를 구해본다
                //만약 그 거리가 middle 이상이라면 공유기를 설치한다
                d = Math.min(homes.get(i) - left, right - homes.get(i));
                if (d >= middle) {
                    cnt++;
                    left = homes.get(i);
                }
                if (c == cnt) {
                    possible = true;
                    break;
                }
            }

            // 해당 거리로 모두 설치가 가능하면 범위를 중앙 ~ 끝으로 조정한다
            // 불가능하면 시작 ~ 중앙으로 조정한다
            if (possible) {
                answer = middle;
                start = middle + 1;
                middle = (start + end) / 2;
            } else {
                end = middle -1;
                middle = (start + end) / 2;
            }

            if (start > end) {
                break;
            }
        }// while
    }
}