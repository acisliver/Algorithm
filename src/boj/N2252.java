package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// 줄 세우기
// https://www.acmicpc.net/problem/2252
public class N2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        Students students = new Students(N);

        for (int i = 0; i < M; i++) {

            int[] numbers = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int previousNumber = numbers[0];
            int nextNumber = numbers[1];

            students.setPreviousStudent(previousNumber, nextNumber);
        }

        Queue<Student> queue = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        Student firstStudent = students.getFirstStudent();
        queue.offer(firstStudent);
        answer.add(firstStudent.number);

        while (!queue.isEmpty()) {
            Student curStudent = queue.poll();

            for (Student nextStudent : curStudent.previousStudents) {
                queue.offer(nextStudent);
                answer.add(curStudent.number);
            }
        }

        System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    static class Students {

        List<Student> students = new ArrayList<>();

        Students(int count) {
            for (int number = 1; number <= count; number++) {
                students.add(new Student(number));
            }
        }

        public void setPreviousStudent(int previousNumber, int nextNumber) {
            Student previousStudent = students.get(previousNumber - 1);
            Student nextStudent = students.get(nextNumber - 1);
            nextStudent.setPreviousStudent(previousStudent);
        }

        public Student getFirstStudent() {
            return students.stream()
                    .filter(student -> student.previousStudents.size() == 0)
                    .findFirst()
                    .orElse(null);
        }
    }

    static class Student {

        int number;
        Set<Student> previousStudents = new HashSet<>();

        Student(int number) {
            this.number = number;
        }

        void setPreviousStudent(Student previousStudent) {
            previousStudents.add(previousStudent);
        }
    }


}
