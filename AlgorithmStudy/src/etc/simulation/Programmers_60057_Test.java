package etc.simulation;

public class Programmers_60057_Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        Programmers_60057 p = new Programmers_60057();

        int i=0;
        while (i<100_0000) {

            System.out.println("i = " + i);
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < 25; j++) {
                sb.append((char)((int)(Math.random() * 2) + 97));
            }

            String s = sb.toString();

            int correct = sol.solution(s);
            int testAnswer = p.solution(s);
            if (correct != testAnswer) {
                System.out.println("correct = " + correct);
                System.out.println("testAnswer = " + testAnswer);
                System.out.println("s = " + s);
                break;
            }
            i++;
        }
    }
    static class Solution {
        public int solution(String s) {
            int answer = s.length();
            // 1개 단위(step)부터 압축 단위를 늘려가며 확인
            for (int step = 1; step < s.length() / 2 + 1; step++) {
                String compressed = "";
                String prev = s.substring(0, step); // 앞에서부터 step만큼의 문자열 추출
                int cnt = 1;
                // 단위(step) 크기만큼 증가시키며 이전 문자열과 비교
                for (int j = step; j < s.length(); j += step) {
                    // 이전 상태와 동일하다면 압축 횟수(count) 증가
                    String sub = "";
                    for (int k = j; k < j + step; k++) {
                        if (k < s.length()) sub += s.charAt(k);
                    }
                    if (prev.equals(sub)) cnt += 1;
                        // 다른 문자열이 나왔다면(더 이상 압축하지 못하는 경우라면)
                    else {
                        compressed += (cnt >= 2)? cnt + prev : prev;
                        sub = "";
                        for (int k = j; k < j + step; k++) {
                            if (k < s.length()) sub += s.charAt(k);
                        }
                        prev = sub; // 다시 상태 초기화
                        cnt = 1;
                    }
                }
                // 남아있는 문자열에 대해서 처리
                compressed += (cnt >= 2)? cnt + prev : prev;
                // 만들어지는 압축 문자열이 가장 짧은 것이 정답
                answer = Math.min(answer, compressed.length());
            }
            return answer;
        }
    }

    static class Programmers_60057 {
        static int unit;
        static int answer;
        static int length;
        static StringBuilder pattern;

        public int solution(String s) {
            answer = (int) 1e9;
            unit = 1;

            if (s.length() == 1) return 1;
            if (s.length() == 2) return 2;

            while (unit <= s.length() / 2) {
                length = s.length();
                pattern = null;
                int patternCnt = 1;

                for (int i = 0; i < s.length(); i += unit) {
                    if (pattern == null) {
                        pattern = new StringBuilder();
                        for (int j = 0; j < unit; j++) {
                            pattern.append(s.charAt(j));
                        }
                        continue;
                    }

                    StringBuilder now = new StringBuilder();

                    for (int j = i; j < i + unit && i + unit - 1 < s.length(); j++) {
                        now.append(s.charAt(j));
                    }

                    if (now.length() > 0 && isPatternEquals(now, pattern)) {
                        length -= unit;
                        patternCnt++;
                        if (patternCnt == 2 || patternCnt % 10 == 0) {
                            length++;
                        }
                    } else {
                        pattern = now;
                        patternCnt = 1;
                    }
                }//for i

                answer = Math.min(answer, length);
                unit++;
            }

            return answer;
        }

        boolean isPatternEquals(StringBuilder sb, StringBuilder sb2) {
            for (int i = 0; i < unit; i++) {
                if (sb.charAt(i) != sb2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
