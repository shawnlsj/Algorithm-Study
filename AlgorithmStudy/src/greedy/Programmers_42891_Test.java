package greedy;

import java.util.Arrays;
import java.util.LinkedList;

public class Programmers_42891_Test {

    public static void main(String[] args) {

        for (int i = 1; i<100000 ; i++) {
            int[] food_times = makeArray();
            int[] food_times2 = Arrays.copyOf(food_times, food_times.length);
            int[] food_times3 = Arrays.copyOf(food_times, food_times.length);
            long k = (long)(Math.random() * 1_999_999) + 1;
            
            int test = solution(food_times, k); // 테스트할 솔루션
            int correct = correctSol(food_times2, k); // 정답 판정을 받은 솔루션
            if (test != correct) {
                System.out.println("error");
                System.out.println(Arrays.toString(food_times3));
                System.out.println("k = " + k);
                System.out.println("test = " + test);
                System.out.println("correct = " + correct);
                break;
            }
            System.out.println("i = " + i);
        }
        System.out.println("success");
    }
    static int[] makeArray() {
        int size = (int)(Math.random()*5) + 1;
        int arr[] = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 5) + 1;
        }

        return arr;

    }
    public static int correctSol(int[] food_times, long k) {
        int answer = 0;

        long numFood = (long)food_times.length;
        long len = (long)food_times.length;
        long time = 0L;
        long now = 0L;

        while (true) {
                if (food_times[(int)(now % len)] == 0) {
                    now++;
                    continue;
                }
            food_times[(int)(now % len)] -= 1;
            if (food_times[(int)(now % len)] == 0) {
                numFood--;
                if (numFood == 0){
                    answer = -1;
                    return answer;
                }
            }
            time++;
            now ++;

            if (time == k) {
                break;
            }
        }

        for (long i = now, j = 0; j < len; i++, j++) {
            i = i % food_times.length;
            if (food_times[(int)(i % len)] != 0) {
                answer =(int)(i % len);
                answer++;
                break;
            }
        }
        return answer;
    }

    public static int solution(int[] food_times, long k) {
        int answer = 0;
        long n = 0; // n의 값이 x이면 x초 이하의 음식은 전부 먹을 수 있다

        long length = food_times.length;

        LinkedList<Programmers_42891_v2.Food> q = new LinkedList<>();  // 모든 음식을 저장할 리스트

        for (int i = 0; i < food_times.length; i++) {
            q.add(new Programmers_42891_v2.Food((i + 1), food_times[i]));
        }

        q.sort((o1, o2) -> {
                return (int) (o1.time - o2.time);
        });
        while (true) {
            if (q.isEmpty()) {
                return -1;
            }
            Programmers_42891_v2.Food food = q.poll();

            if (food.time == n) {
                length--;
                continue;
            }

            k -= length * (food.time - n);

            if (k > 0) {
                n = food.time;
                length--;
                continue;
            }

            if (k == 0) {
                n = food.time;
                while (true) {
                    if (q.isEmpty()) {
                        return -1;
                    }
                    Programmers_42891_v2.Food f = q.poll();
                    if (f.time == n) {
                        continue;
                    } else {
                        q.add(f);
                        q.sort((o1, o2) -> o1.num - o2.num);
                        return q.get(0).num;
                    }
                }
            }

            if (k < 0) {
                q.addFirst(food);
                k += length * (food.time - n);
                break;
            }
        }

        q.sort((o1, o2) -> o1.num - o2.num);

        if ((k + 1) % length == 0) {
            return q.get(q.size() - 1).num;
        }

        return q.get((int) ((k + 1) % length) - 1).num;

    }


    static class Food {
        int num; // 음식 번호
        long time; //완식까지 남은 시간

        Food(int num, long time) {
            this.num = num;
            this.time = time;
        }
    }
}
