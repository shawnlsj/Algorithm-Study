package etc.simulation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 튜플
public class Programmers_64065 {
    public int[] solution(String s) {
        Pattern p = Pattern.compile("(\\{)((,|\\s|\\d)+)(})");
        Matcher m = p.matcher(s);
        ArrayList<ArrayList<Integer>> outerList = new ArrayList<>();
        while (m.find()) {
            StringTokenizer stk = new StringTokenizer(m.group(2), ",");
            ArrayList<Integer> list = new ArrayList<>();
            while (stk.hasMoreTokens()) {
                String token = stk.nextToken();
                list.add(Integer.parseInt(token.trim()));
            }
            outerList.add(list);
        }
        outerList.sort((o1, o2) -> o1.size() - o2.size());

        Set<Integer> set = new LinkedHashSet<>();
        for (ArrayList<Integer> list : outerList) {
            for (Integer i : list) {
                set.add(i);
            }
        }

        int[] answer = new int[set.size()];
        Iterator it = set.iterator();
        for (int i = 0; i < answer.length; i++) {
            answer[i] = (Integer)it.next();
        }
        return answer;
    }
}
