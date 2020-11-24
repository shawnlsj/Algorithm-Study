package etc.least_recently_used;

import java.util.*;

public class Programmers_17680 {
    public static void main(String[] args) {

    }
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        LinkedList<String> cache = new LinkedList<>();
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();

            if (cache.contains(city)) {
                answer += 1;
                for (int j = 0; j < cache.size(); j++) {
                    if (cache.get(j).equals(city)) {
                        cache.addLast(cache.remove(j));
                        break;
                    }
                }
            } else {
                answer += 5;
                if (cacheSize != 0) {
                    if (cache.size() == cacheSize) {
                        cache.remove();
                    }
                    cache.addLast(city);
                }
            }
        }
        return answer;
    }
}
