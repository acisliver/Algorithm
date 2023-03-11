package kakao.blind2023;

import java.util.*;

// 표 병합
// https://school.programmers.co.kr/learn/courses/30/lessons/150366
public class Solution5 {

    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(Arrays.toString(s.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"})));
    }

    static String[][] matrix = new String[51][51];
    static int[][] merged = new int[51][51];
    static List<String> answer = new LinkedList<>();
    static Map<String, String> updatedValues = new HashMap<>();
    static int mergeNum = 1;

    public String[] solution(String[] commands) {

        for (String command : commands) {
            String[] cArr = command.split(" ");
            switch (cArr[0]) {
                case "UPDATE":

                    break;
                case "MERGE":

                    break;
                case "UNMERGE": {

                    break;
                }
                case "PRINT": {
                    int r = Integer.parseInt(cArr[1]);
                    int c = Integer.parseInt(cArr[2]);
                    print(r, c);
                    break;
                }
            }
        }

        return answer.toArray(String[]::new);
    }

    private void update(int r, int c, String value) {
        matrix[r][c] = value;
    }

    private void update(String value1, String value2) {

    }

    private void merge(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) return;


    }

    private void unmerge(int r, int c) {

    }

    private void print(int r, int c) {
        if (matrix[r][c].length() == 0) {
            answer.add("EMPTY");
        } else {
            answer.add(matrix[r][c]);
        }

    }
}
