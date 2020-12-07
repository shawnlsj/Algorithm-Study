package etc.simulation.kakao_2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Programmers_17684 {
    public static void main(String[] args) {
        Programmers_17684 p = new Programmers_17684();
        p.solution("KAKAO");
    }
    public int[] solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();

        Map<String, Integer> wordIndexMap = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            wordIndexMap.put((char)('A' + i) + "", i + 1);
        }

        for (int i = 0; i < msg.length(); i++) {

            StringBuilder sb = new StringBuilder();

            while (true) {
                if (i == msg.length()) {
                    answer.add(wordIndexMap.get(sb.toString()));
                    break;
                }
                sb.append(msg.charAt(i));

                if (wordIndexMap.containsKey(sb.toString())) {
                    i++;
                } else {
                    i--;
                    wordIndexMap.put(sb.toString(), wordIndexMap.size() + 1);
                    sb = sb.deleteCharAt(sb.length() - 1);
                    answer.add(wordIndexMap.get(sb.toString()));
                    break;
                }
            }
        }

        int[] indexArr = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            indexArr[i] = answer.get(i);
        }
        return indexArr;
    }
}
