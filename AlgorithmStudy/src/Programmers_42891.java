// 무지의 먹방 라이브
public class Programmers_42891 {
    public static void main(String[] args) {
        int[] arr = {1600000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000,100000000};
        System.out.println(  solution(arr, (long)2e10));
    }
    public static int solution(int[] food_times, long k) {
        int answer = 0;

        long numFood = (long)food_times.length;
        long len = (long)food_times.length;
        long time = 0L;
        long now = 0L;

        while (true) {
            try {
                if (food_times[(int)(now % len)] == 0) {
                    now++;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("now = " + now);
                System.out.println("len = " + len);
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
}
