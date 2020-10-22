package etc.simulation;

import java.util.ArrayList;
import java.util.List;

// 수식 최대화
public class Programmers_67257 {
    public static void main(String[] args) {
        Programmers_67257 p = new Programmers_67257();
        System.out.println(p.solution("100-200*300-500+20"));
    }
    public long solution(String expression) {
        long answer = 0;
        ArrayList<String> newExpression = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (    expression.charAt(i) == '*'||
                    expression.charAt(i) == '-'||
                    expression.charAt(i) == '+'){
                newExpression.add(sb.toString());
                newExpression.add(expression.charAt(i) + "");
                sb = new StringBuilder();
                continue;
            }
            sb.append(expression.charAt(i)+"");
        }
        newExpression.add(sb.toString());
        System.out.println(newExpression);
        ArrayList<String> priorities = new ArrayList<>();
        // * 1순위
        priorities.add("*+-");
        priorities.add("*-+");
        // + 1순위
        priorities.add("+-*");
        priorities.add("+*-");
        // - 1순위
        priorities.add("-*+");
        priorities.add("-+*");

        for (int i = 0; i < priorities.size(); i++) {
            long result = Math.abs(solve(newExpression, priorities.get(i)));
            answer = Math.max(answer, result);
        }
        return answer;
    }
    long solve(List<String> expression, String priority) {
        if (expression.size() == 1) {
            return Integer.parseInt(expression.get(0));
        }
        for (int i = priority.length() - 1; i >= 0; i--) {
            String operation = priority.charAt(i) + "";
            for (int j = expression.size() - 1; j >= 0; j--) {
                if (expression.get(j).equals(operation)) {
                    ArrayList<String> ex1 = new ArrayList<>();
                    ArrayList<String> ex2 = new ArrayList<>();
                    for (int k = 0; k < j; k++) {
                        ex1.add(expression.get(k));
                    }
                    for (int k = j + 1; k < expression.size(); k++) {
                        ex2.add(expression.get(k));
                    }
                    long a = solve(ex1, priority);
                    long b = solve(ex2, priority);
                    System.out.println("a = " + a);
                    System.out.println("b = " + b);
                    System.out.println("operation = " + operation);
                    System.out.println("---");
                    switch (operation) {
                        case "+":
                            return a + b;
                        case "-":
                            return a - b;
                        case "*":
                            return a * b;
                    }
                }
            }
        }
        return 0;
    }
}
