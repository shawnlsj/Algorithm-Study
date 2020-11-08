package etc.simulation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


// 문자 2개씩 추출하기
// 1. 2중 for 문을 직접 작성하여 2글자씩 추출하는 방법
// 2. substring 메서드를 이용하는 방법
public class Programmers_17677 {
    public static void main(String[] args) {
        Programmers_17677 p = new Programmers_17677();

        //	"FRANCE", "french"
        System.out.println(p.solution("FRANCE", "french"));
    }
    public int solution(String str1, String str2) {
        int answer = 0;
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        int intersection = 0;
        int union = 0;

        for (int i = 0; i < str1.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < i + 2; j++) {
                sb.append(str1.charAt(j));
            }
            String s = sb.toString();
            if (s.matches("([a-z]|[A-Z]){2}")) {
                s = s.toUpperCase();
                if (map1.get(s) == null) {
                    map1.put(s, 1);
                } else {
                    map1.put(s, map1.get(s) + 1);
                }
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < i + 2; j++) {
                sb.append(str2.charAt(j));
            }
            String s = sb.toString();
            if (s.matches("([a-z]|[A-Z]){2}")) {
                s = s.toUpperCase();
                if (map2.get(s) == null) {
                    map2.put(s, 1);
                } else {
                    map2.put(s, map2.get(s) + 1);
                }
            }
        }

        Iterator it = map1.entrySet().iterator();
        while (it.hasNext()) {
            String key = (String)((Map.Entry) it.next()).getKey();
            if (map2.get(key) != null) {
                intersection += Math.min(map1.get(key), map2.get(key));
                union += Math.max(map1.get(key), map2.get(key));
            } else {
                union += map1.get(key);
            }
        }

        it = map2.entrySet().iterator();
        while (it.hasNext()) {
            String key = (String)((Map.Entry) it.next()).getKey();
            if (map1.get(key) == null) {
                union += map2.get(key);
            }
        }

        if (intersection == 0 && union == 0) {
            answer = 65536;
        } else {
            answer = (int) (((double) intersection / (double) union) * 65536);
        }
        return answer;
    }
}
