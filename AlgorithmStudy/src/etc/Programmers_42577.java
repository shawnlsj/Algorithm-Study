package etc;

public class Programmers_42577 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        for (int i = 0; i < phone_book.length; i++) {
            String s = phone_book[i];
            for (int j = 0; j < phone_book.length; j++) {
                if (j == i) continue;
                if (s.length() <= phone_book[j].length() &&
                        isPrefix(s, phone_book[j])) {
                    return false;
                }
            }
        }

        return answer;
    }
    boolean isPrefix(String prefix, String phoneNum) {
        for (int i = 0; i < prefix.length(); i++) {
            if (prefix.charAt(0) != phoneNum.charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
