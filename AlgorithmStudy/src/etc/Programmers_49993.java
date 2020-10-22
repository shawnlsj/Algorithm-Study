package etc;

import java.util.*;

// 스킬트리
public class Programmers_49993 {
    public static void main(String[] args) {
        Programmers_49993 p = new Programmers_49993();
        System.out.println( p.solution2("ABC", new String[]{"ACFGB"})
        );
    }

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < skill.length(); i++) {
            list.add(skill.charAt(i) + "");
        }

        outer:
        for (int i = 0; i < skill_trees.length; i++) {
            String str = skill_trees[i];
            int cursor = 0;
            for (int j = 0; j < str.length(); j++) {
                String s = str.charAt(j) + "";
                if (skill.contains(s)) {
                    if (list.get(cursor).equals(s)) {
                        cursor++;
                    } else {
                        continue outer;
                    }
                }
            }
            answer++;
        }
        return answer;
    }
    public int solution2(String skill, String[] skill_trees) {
        int answer = 0;
        ArrayList<String> skillTrees = new ArrayList<String>(Arrays.asList(skill_trees));
        //ArrayList<String> skillTrees = new ArrayList<String>();
        Iterator<String> it = skillTrees.iterator();

        while (it.hasNext()) {
            String s = it.next().replaceAll("[^" + skill + "]", "");
            if (skill.indexOf(s) != 0) {
                System.out.println("s = " + s);
                it.remove();
            }
        }
        answer = skillTrees.size();
        return answer;
    }
}
