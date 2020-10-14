package etc.simulation;

import java.util.*;

public class Programmers_60062 {
    public static void main(String[] args) {
        Programmers_60062 p = new Programmers_60062();
        System.out.println(p.solution(12, new int[]{1,5,6,10}, new int[]{1,2,3,4})
        );
    }

    public int solution(int n, int[] weak, int[] dist) {
        int[][] weaks = new int[weak.length][weak.length];

        // 2차원 배열에 취약지점을 일자로 펼 수 있는 모든 경우를 저장한다
        for (int i = 0; i < weak.length; i++) {
            for (int j = 0; j < weak.length; j++) {
                if (j + i < weak.length) {
                    weaks[i][j] = weak[(j + i)];
                } else {
                    weaks[i][j] = weak[(j + i) % weak.length] + n;
                }
            }
        }

        // 기본 타입 배열을 래퍼 클래스 배열로 만들어 내림차순 정렬하기
        Integer[] distanceOfFriends = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        Arrays.sort(distanceOfFriends, Collections.reverseOrder());

        //친구를 1명 투입시키는 경우 부터 모두 투입 시키는 경우까지 전부 탐색한다
        // 친구를 i명 투입하는 경우 k개의 순열을 만들 수 있는데
        for (int i = 1; i <= distanceOfFriends.length; i++) {
            LinkedHashMap permutations = Permutation.permutation(i, i);
            // 취약지점을 일자로 필 수 있는 j번째 경우에 대해서
            for (int j = 0; j < weaks.length; j++) {
                int[] weakArr = weaks[j];

                // k번째 순열대로 친구를 투입시킨다
                for (int k = 0; k < permutations.size(); k++) {
                    int pointer = 0; // 작업 시작 위치를 기억할 포인터

                    // k번째 순열을 얻어온다
                    ArrayList<Integer> permutation = (ArrayList<Integer>) permutations.get(k);

                    // 순열대로 친구 투입
                    for (int l = 0; l < permutation.size(); l++) {
                        int friendDist = distanceOfFriends[permutation.get(l)];
                        int start = weakArr[pointer]; // pointer 지점에 작업 투입
                        pointer++; // 투입된 지점은 무조건 작업 완료이니 pointer 를 다음 지점으로 옮긴다
                        int end = start + friendDist;

                        // 작업 시작
                        while (pointer < weakArr.length) {
                            if (weakArr[pointer] <= end) {
                                pointer++;
                            } else {
                                break;
                            }
                        }
                    }
                    //결산
                    if (i == 2) {
                        System.out.println(Arrays.toString(weakArr));
                        System.out.println("permutation = " + permutation);
                        System.out.println("pointer = " + pointer);
                        System.out.println("--");
                    }
                    if (pointer >= weaks.length) {
                        return i; //
                    }
                }
            }
        }

        return -1;
    }
    static class Permutation {
        private static int n;
        private static int r;
        private static int cnt;
        private static boolean[] visited;
        private static LinkedList<Integer> deque;
        private static LinkedHashMap<Integer, ArrayList<Integer>> permutations;

        public static LinkedHashMap<Integer, ArrayList<Integer>> permutation(int n, int r) {
            permutations = new LinkedHashMap<>();
            visited = new boolean[n + 1];
            deque = new LinkedList<>();
            Permutation.n = n;
            Permutation.r = r;
            cnt = 0;

            for (int i = 1; i <= n; i++) {
                dfs(i);
            }
            return permutations;
        }

        static void dfs(int x) {
            deque.addLast(x);
            visited[x] = true;

            if (deque.size() == r) {
                permutations.put(cnt, new ArrayList<>());
                for (int i : deque) {
                    permutations.get(cnt).add(i);
                }
                cnt++;
                visited[x] = false;
                deque.removeLast();
                return;
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                dfs(i);
            }
            visited[x] = false;
            deque.removeLast();
            return;
        }
    }
}
