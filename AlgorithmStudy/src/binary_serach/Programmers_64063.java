package binary_serach;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Programmers_64063 {
    public static void main(String[] args) {
        Programmers_64063 p = new Programmers_64063();
        System.out.println(
                Arrays.toString(p.solution(10, new long[]{1, 3, 4, 1, 3, 1}))
        );
    }
    Map<Long, Long> map = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            if (map.get(room_number[i]) == null) {
                map.put(room_number[i], findEmptyRoom(room_number[i] + 1));
                answer[i] = room_number[i];
            } else {
                long emptyRoom = findEmptyRoom(room_number[i]);
                map.put(emptyRoom, findEmptyRoom(emptyRoom + 1));
                answer[i] = emptyRoom;
            }
        }
        return answer;
    }

    long findEmptyRoom(long num) {
        if (map.get(num) == null) {
            return num;
        } else {
            long result = findEmptyRoom(map.get(num));
            map.put(num, result);
            return result;
        }
    }
}
