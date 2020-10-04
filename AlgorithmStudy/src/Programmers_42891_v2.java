import javafx.scene.control.SpinnerValueFactory;

import java.util.LinkedList;
import java.util.PriorityQueue;

// 무지의 먹방 라이브
public class Programmers_42891_v2 {
    public static void main(String[] args) {
        int[] arr = {5,5};
        System.out.println(solution(arr, 11));
    }

    public static int solution(int[] food_times, long k) {
        int answer = 0;
        long n = 0; // n의 값이 x이면 x초 이하의 음식은 전부 먹을 수 있다

        long length = food_times.length;

        LinkedList<Food> q = new LinkedList<>();  // 모든 음식들

        for (int i = 0; i < food_times.length; i++) {
            q.add(new Food((i + 1), food_times[i]));
        }

        q.sort((o1, o2) -> {
            if (o1.time != o2.time) {
                return (int) (o1.time - o2.time);
            } else {
                return (o1.num - o2.num);
            }
        });
        while (true) {
            if (q.isEmpty()) {
                return -1;
            }
            Food food = q.poll();

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
                    Food f = q.poll();
                    if (f.time == n) {
                        continue;
                    } else {
                        return f.num;
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

        System.out.println("q.size() = " + q.size());
        System.out.println("k = " + k);
        System.out.println("length = " + length);

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
