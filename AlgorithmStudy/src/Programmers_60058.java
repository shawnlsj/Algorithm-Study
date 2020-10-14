import java.util.Deque;
import java.util.LinkedList;

public class Programmers_60058 {
    public static void main(String[] args) {
        Programmers_60058 p = new Programmers_60058();
        System.out.println(p.solution("()))((()"));
    }
    public String solution(String s) {
        return recursion(s);
    }


    String recursion(String s) {
        if (s.equals("")) return "";
        String[] uv = split(s);
        String u = uv[0];
        String v = uv[1];
        // u가 올바른인지 검사
        if (isRightString(u)) {
            return u +  recursion(v);
        } else {
            return merge(u, v);
        }
    }
    String merge(String u, String v) {
        String result =  "(" + recursion(v) + ")";
        Deque<String> deque = new LinkedList<>();
        for (int i = 0; i < u.length(); i++) {
            deque.add(u.charAt(i) + "");
        }
        deque.pollFirst();
        deque.pollLast();
        StringBuilder builder = new StringBuilder();
        while(!deque.isEmpty()) {
            if (deque.poll().equals("(")) {
                builder.append(")");
            } else {
                builder.append("(");
            }
        }

        u = builder.toString();
        return result + u;
    }
    String[] split(String s) {
        String[] uv = new String[2];
        int left = 0;
        int right = 0;
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                for (int j = 0; j < s.length(); j++) {
                    if (j <= i) {
                        u.append(s.charAt(j));
                    } else {
                        v.append(s.charAt(j));
                    }
                }
                break;
            }
        }
        uv[0] = u.toString();
        uv[1] = v.toString();
        return uv;
    }

    boolean isRightString(String s) {
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                val++;
            } else {
                val--;
            }
            if (val < 0) {
                return false;
            }
        }
        return true;
    }
}

//1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
//2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
//3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
//  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
//4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
//  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
//  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
//  4-3. ')'를 다시 붙입니다.
//  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
//  4-5. 생성된 문자열을 반환합니다.
