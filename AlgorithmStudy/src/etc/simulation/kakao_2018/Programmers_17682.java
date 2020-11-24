package etc.simulation.kakao_2018;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmers_17682 {
    public static void main(String[] args) {
        Programmers_17682 p = new Programmers_17682();
        p.solution("10D2S3T*");
    }
    public int solution(String dartResult) {
        ArrayList<Integer> list = new ArrayList<>();

        Pattern p = Pattern.compile("[0-9]{1,2}(S|D|T)(\\*|#)?");
        Matcher m = p.matcher(dartResult);

        for (int i = 0; m.find(); i++) {

            String s = m.group();

            int x = 0;

            Matcher m2 = Pattern.compile("[0-9]{1,2}").matcher(s);

            if (m2.find()) {
                x = Integer.parseInt(m2.group());
            }

            m2 = Pattern.compile("(S|D|T)").matcher(s);
            m2.find();
            switch (m2.group()) {
                case "S":
                    x = (int) Math.pow(x, 1);
                    break;
                case "D":
                    x = (int) Math.pow(x, 2);
                    break;
                case "T":
                    x = (int) Math.pow(x, 3);
                    break;
            }

            list.add(x);

            if (s.charAt(s.length()-1) == '*') {
                if (i >= 1) {
                    list.set(i - 1, list.get(i - 1) * 2);
                }
                list.set(i, list.get(i) * 2);
            } else if (s.charAt(s.length()-1) == '#'){
                list.set(i, list.get(i) * -1);
            }
        }
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}

