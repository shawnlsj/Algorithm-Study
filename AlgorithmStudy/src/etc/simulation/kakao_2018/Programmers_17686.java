package etc.simulation.kakao_2018;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmers_17686 {

    public String[] solution(String[] files) {

        Comparator comparator = new FileComparator();

        Arrays.sort(files, comparator);

        return files;
    }

    class FileComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {

            //head 찾기
            Pattern p = Pattern.compile("[^0-9]*");
            Matcher m1 = p.matcher(o1);
            Matcher m2 = p.matcher(o2);

            String head1 = "";
            String head2 = "";

            if (m1.find() && m2.find()) {
                head1 = m1.group().toUpperCase();
                head2 = m2.group().toUpperCase();
            }

            // head 가 다르다면 사전 순으로 정렬
            if (!head1.equals(head2)) {
                return head1.compareTo(head2);
            }

            //number 찾기
            p = Pattern.compile("[0-9]+");
            m1 = p.matcher(o1);
            m2 = p.matcher(o2);

            int number1 = 0;
            int number2 = 0;

            if (m1.find() && m2.find()) {
                number1 = Integer.parseInt(m1.group());
                number2 = Integer.parseInt(m2.group());
            }

            //number 가 다르다면 오름차순 정렬
            if (number1 != number2) {
                return number1 - number2;
            }

            return 0;
        }
    }
}
