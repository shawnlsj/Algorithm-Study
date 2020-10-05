package greedy;

import javafx.scene.control.SpinnerValueFactory;

import java.util.LinkedList;
import java.util.PriorityQueue;

// 무지의 먹방 라이브
public class Programmers_42891_v2 {
    public static void main(String[] args) {
        int[] arr = {3,5,4};
        System.out.println(solution(arr, 9));
    }

    public static int solution(int[] food_times, long k) {
        // 문제 해결 아이디어
        // 테이블을 몇 바퀴 완전히 돌릴 수 있는가? 의 관점으로 바라본다
        // k = 19 {5,7,3,3,4} 의 경우, 4바퀴를 완전히 돌릴 수 있다
        // 5바퀴를 돌리고 k를 계산해보면, k가 음수가 되어서 불가능하다
        // 따라서 4번만 돌린다 - 4바퀴를 돌고 나면 (1번, 2번 음식)만 남는다
        // 즉, 1번, 2번 음식은 네트워크 장애가 발생해도(= k가 0이 되어도) 무조건 1이상 남아있다는 것이 키 포인트이다

        // 예시처럼 n바퀴 돌고 k가 음수인 경우에는 
        // [현재 남은 음식들의 번호], [n-1바퀴 돌고, 장애 발생까지 남은 시간]만 구할 수 있다면,
        // 정답을 바로 찾을 수 있다
        // n바퀴 돌고 k가 0일 때에는 [현재 남은 음식들의 번호] 만 구할 수 있으면 된다
        // 그 다음 바퀴에서 처음으로 만나게 될 음식이 정답이 되기 때문이다

        // 남은 시간 몇 초의 음식까지를 먹을 수 있는 가를 저장할 변수
        // n에 3이 저장된다면 남은 시간이 3초 이하의 음식은 전부 먹은 걸로 친다
        long n = 0;

        // 현재 남은 음식의 길이를 저장할 변수
        // 남은 시간이  {3, 2 ,0} 이면 길이는 2
        // 남은 시간이  {1, 0 ,0} 이면 길이는 1
        long length = food_times.length;

        LinkedList<Food> deque = new LinkedList<>();  // 모든 음식을 저장할 덱

        for (int i = 0; i < food_times.length; i++) {
            //음식(번호, 시간)을 저장한다
            //번호는 1부터 순차적으로 부여되므로 i + 1
            deque.add(new Food((i + 1), food_times[i]));
        }
        
        // 덱을 음식의 시간을 기준으로 오름차순으로 정렬한다
         deque.sort((o1, o2) -> {
                return (int) (o1.time - o2.time);
        });
        
        while (true) {
            
            //덱이 비어있다는 것은 남은 음식이 없다는 것이므로 -1 리턴
            if (deque.isEmpty()) {
                return -1;
            }
            
            // 남은 음식중 제일 앞 (시간이 제일 적게 남은) 음식을 빼온다
            Food food = deque.pollFirst();

            // n은 초깃값이 0이므로 처음엔 무조건 false가 나온다
            // 하지만 두번째 부터는, 변수 n에는 X초 이하의 음식은 전부 먹을 수 있을 때의 X값이 들어가 있으므로
            // -> 음식의 남은 시간 == n 이라면 음식을 먹은 걸로 처리하고 (길이를 1 줄이고) 컨티뉴한다
            if (food.time == n) {
                length--;
                continue;
            }

            // 이제 k 에서 현재 음식의 길이 * (음식의 남은시간 - n)을 빼준다
            k -= length * (food.time - n);

            //  k -= length * (food.time - n) 가 나오게 된 이유 설명

            // 현재 음식의 길이가 a라면 한 바퀴 도는데 필요한 시간은 a 초이다 = (현재 음식의 길이 * a)
            // 예) 남은 시간이 {2,1,3} 라면 한 바퀴 도는데 3초 걸리지만
            // {1,0,2} 라면 남은 음식이 2개 뿐이므로 한 바퀴 도는데 2초 걸린다

            // 그렇다면, 시간이 가장 적게 남은 음식을 A라고 했을 때,
            // A를 완식하려면 몇 바퀴를 돌아야 하는가?
            // 예) {2, 1, 3} 이라면 A를 다먹기 위해서는 1바퀴 돌면 된다. 시간은 3초 걸린다
            // {5, 2, 7} 이라면 2바퀴를 돌면 된다. 시간은 6초 걸린다
            // {3, 0, 5} 이라면 3바퀴를 돌아야 할 것이다. 시간은 6초 걸린다.

            // 즉, A를 완식하는데 필요한 시간
            //        = A의 현재 남은 시간 * 현재 남은 음식의 길이(= 남은 음식의 개수)
            // 이렇게 하면 k -= length * food.time 이 된다

            // 하지만, 현재 코드에서는 food.time 의 갱신 처리를 따로 하지 않기 때문에
            // {2, 1, 3} 에서 각각의 food.time 은 2, 1, 3 
            // 1바퀴를 돌고 난 뒤의 {1, 0, 2} 에서도 각각의 food.time 은 2, 1, 3이다
            // 남은 시간을 음식마다 일일이 갱신해 주면은 효율성 테스트에서 실패 처리를 받기 때문이다

            // 변수 n에 완식한 음식의 초깃값을 저장하는 것으로 이를 해결할 수 있다
            // 예) {4, 7, 10} 에서 4바퀴를 돌면 4를 완식 할 수 있다. n에 4를 저장한다
            // 그 다음, {7, 10} 에서 7을 완식하려면 7 * 2가 아닌 3 * 2를 해야한다
            // 앞에서 이미 4바퀴를 돌았기 때문이다. 그 4바퀴를 n에 저장했다.
            // 이렇게 n을 이용해서 갱신되지 않은 시간으로도 완식 시간의 계산이 가능하다

            // 음식을 완식하고도 k가 양수라면, 음식을 더 먹을 수 있다는 뜻이다
            if (k > 0) {
                n = food.time;  //n을 방금 먹은 음식의 시간으로 갱신해준다
                length--; //완식 했으므로 길이를 1 줄인다
                continue;
            }

            // n바퀴 돌렸을 때 k가 0이 된다는 것은, 가장 마지막 음식을 먹고 네트워크 장애가 발생했다는 것이니
            // 테이블의 처음으로 돌아와서 가장 처음에 만나는 음식이 정답이 된다는 의미다
            if (k == 0) {
                n = food.time;
                while (true) {
                    // 네트워크 장애가 발생했을 때 남아있는 음식이 없다는 것이니 -1 리턴
                    if (deque.isEmpty()) {
                        return -1;
                    }
                    // 음식을 빼서 n에 걸리는지 확인한다
                    Food f = deque.poll();

                    if (f.time == n) {
                        //n에 걸렸다면 다 먹었다는 것이니 컨티뉴
                        continue;
                    } else {

                        //n에 안 걸렸다면 아직 남아있다는 것이니 덱에 추가한다
                        deque.addFirst(f);
                        
                        //남은 음식을 번호 오름차순으로 정렬한다
                        deque.sort((o1, o2) -> o1.num - o2.num);
                        
                        //가장 처음에 만나는 음식인 0번 인덱스가 정답이 된다
                        return deque.get(0).num;
                    }
                }
            }


            // k가 음수라는 것은 방금 빼낸 음식을 다 먹을 수가 없다는 것이다
            if (k < 0) {

                // 음식을 다시 집어 넣는다
                deque.addFirst(food);

                // 위에서 뺀 값을 다시 더해 준다
                k += length * (food.time - n);
                break;
            }
        }

        // 남은 음식을 번호 순으로 정렬한다
        deque.sort((o1, o2) -> o1.num - o2.num);

        // 방송 재개후 먹을 음식의 번호 -> (k + 1)초 후 먹을 음식의 번호를 구한다
        if ((k + 1) % length == 0) {
            // k + 1 % length (현재 남은 음식의 길이(=개수))이 0이라는 것은
            // 제일 끝에 있는 음식이 k + 1초 후 먹을 음식이라는 뜻이다
            return deque.get(deque.size() - 1).num;
        }
        
        // 이외에는 k+1 % length 에다가 인덱스 번호를 구하기 위해 -1 해준다
        // 예) k+1 % length 가 3이라면 3번째 음식이 정답이니 인덱스2 를 구해야 한다
        return deque.get((int) ((k + 1) % length) - 1).num;
    }


    static class Food {
        int num; // 음식 번호
        long time; //완식까지 걸리는 시간

        Food(int num, long time) {
            this.num = num;
            this.time = time;
        }
    }
}
