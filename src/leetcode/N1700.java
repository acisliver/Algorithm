package leetcode;

// Number of Students Unable to Eat Lunch
public class N1700 {

    public static void main(String[] args) {
        new N1700().countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1});
    }

    public int countStudents(int[] students, int[] sandwiches) {
        int top = 0;
        int n = students.length;
        int[] backs = new int[n];

        while (true) {
            int front = 0;
            int back = 0;
            while (front < n) {
                int sandwich = sandwiches[top];
                int student = students[front];
                if (sandwich == student) {
                    top += 1;
                    front += 1;
                } else {
                    front += 1;
                    backs[back++] = student;
                }
            }
            if (back == students.length) {
                return back;
            }
            n = back;
            students = backs;
            backs = new int[n];
        }

    }
}
