package greedy;

import java.util.ArrayList;

public class Programmers_42860 {
    public int solution(String name) {
        int answer = 0;
        int cursor = 0;

        while (true) {
            if (name.charAt(cursor) != 'A') {
                char c = name.charAt(cursor);
                if (c != 'A') {
                    if (c - 'A' >= 13) {
                        answer += 'Z' + 1 - c;
                    } else {
                        answer += c - 'A';
                    }
                }
            }
            break;
        }





        return answer;
    }
}
