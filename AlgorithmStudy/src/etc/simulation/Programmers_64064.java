package etc.simulation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmers_64064 {
    ArrayList<ArrayList<String>> outerList;
    HashSet<String> banList = new HashSet<>();
    Map<String, Boolean> visitedMap;
    int maxDepth;
    public static void main(String[] args) {
        Programmers_64064 p = new Programmers_64064();
        String[] user_id ={"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
        System.out.println(p.solution(user_id, banned_id));
    }
    public int solution(String[] user_id, String[] banned_id) {
        visitedMap = new HashMap<>();
        maxDepth = banned_id.length;

        StringBuilder sb = new StringBuilder();
        for (String s : user_id) {
            visitedMap.put(s, false);
            sb.append(" "+s+" ");
        }
        String users = sb.toString();

       outerList = new ArrayList<>();
        for (String s : banned_id) {
            ArrayList<String> list = new ArrayList<>();

            String regex = s.replace("*", "([a-z]|[0-9])");
            regex = " " + regex + " ";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(users);
            while (matcher.find()) {
                list.add(matcher.group().trim());
            }
            outerList.add(list);
        }

        for (int i = 0; i < outerList.get(0).size(); i++) {
            dfs(0, outerList.get(0).get(i), new ArrayList<>());
        }


        return banList.size();
    }

    void dfs(int depth, String banId, ArrayList<String> idList) {
        if (depth == maxDepth) {
            ArrayList<String> list = new ArrayList<>();
            for (String s : idList) {
                list.add(s);
            }
            list.sort((o1, o2) -> o1.compareTo(o2));
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s);
            }
            banList.add(sb.toString());
            return;
        }
        ArrayList<String> list = outerList.get(depth);
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (visitedMap.get(id)) continue;
            visitedMap.put(id, true);
            idList.add(id);
            dfs(depth + 1, id, idList);
            idList.remove(idList.size() - 1);
            visitedMap.put(id, false);
        }
    }
}
