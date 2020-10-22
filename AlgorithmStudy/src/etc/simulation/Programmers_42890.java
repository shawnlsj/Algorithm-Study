package etc.simulation;

import dynamic_programming.Programmers_42895;

import java.util.*;

// 후보키
public class Programmers_42890 {
    public static void main(String[] args) {
        System.out.println(new Programmers_42890().solution(
                new String[][]   {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}}

        ));

    }
    public int solution(String[][] relation) {
        int answer = 0;
        // 모든 가능한 조합 구하기
        ArrayList<Integer> combinations = new ArrayList<>();
        ArrayList<Integer> superKeys = new ArrayList<>();
        for (int i = 1; i <= Math.pow(2, relation[0].length) - 1; i++) {
            combinations.add(i);
        }

        // 슈퍼키 구하기
        for (int i = 0; i < combinations.size(); i++) {
            ArrayList<Integer> columns = new ArrayList<>();
            Set<String> set = new HashSet<>();
            int combination = combinations.get(i);

            // 어느 컬럼들을 조회해야 하는지 파악하기
            for (int j = 1; j <= relation[0].length; j++) {
                if ((combination & (1 << (j - 1))) != 0) {
                    columns.add(j);
                }
            }

            // 조회 해야할 컬럼들을 하나의 문자열로 묶어 Set 에 넣기
            for (int j = 0; j < relation.length; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < columns.size(); k++) {
                    sb.append(relation[j][columns.get(k)-1]);
                }
                set.add(sb.toString());
            }
            if (set.size() == relation.length) {
                superKeys.add(combination);
            }
        }

        // 각각의 슈퍼키에 대해 다른 슈퍼키를 포함하고 있는지 검사하기
        for (int i = 0; i < superKeys.size(); i++) {
            int superKey = superKeys.get(i);
            boolean isCandidateKey = true;
            for (int j = 0; j < superKeys.size(); j++) {
                if (superKey <= superKeys.get(j)) continue;
                if (superKeys.get(j) == (superKey & superKeys.get(j))) {
                    isCandidateKey = false;
                    break;
                }
            }
            if (isCandidateKey) {
                answer++;
            }
        }
        return answer;
    }
}
