package etc.simulation;

// 문자열 압축
public class Programmers_60057 {
    static int unit;
    static int answer;
    static int length;
    static StringBuilder pattern;

    public static void main(String[] args) {
        Programmers_60057 p = new Programmers_60057();
        System.out.println(p.solution("abaabbbbbbbbbbbbbbbbbbbba"));
    }
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
                    if (patternCnt == 2 || patternCnt == 10 ||
                            (patternCnt % 10 == 0 && ((patternCnt / 10) % 10 == 0))) {

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
