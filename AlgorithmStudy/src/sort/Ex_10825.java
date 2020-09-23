package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Ex_10825 {
    static int n;
    static ArrayList<Student> students;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            String name = stk.nextToken();
            int korean = Integer.parseInt(stk.nextToken());
            int english = Integer.parseInt(stk.nextToken());
            int math = Integer.parseInt(stk.nextToken());
            students.add(new Student(name, korean, english, math));
        }

        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.korean != o2.korean) {
                    return o2.korean - o1.korean;
                }
                if (o1.english != o2.english) {
                    return o1.english - o2.english;
                }
                if (o1.math != o2.math) {
                    return o2.math - o1.math;
                }

                return o1.name.compareTo(o2.name);
            }
        });

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).name);
        }
    }

    static class Student {
        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }
    }
}
